package com.gl.roadaccidents.mybatis;

import com.gl.roadaccidents.load.DataLoader;
import com.gl.roadaccidents.model.*;
import com.gl.roadaccidents.service.DataLoadService;
import com.gl.roadaccidents.service.RoadAccidentDataResourceScanner;
import com.gl.roadaccidents.vo.RoadAccidentVo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by gavin on 16-5-19.
 */
public class MybatisRoadAccidentDataLoader implements DataLoader {
    private static final Logger log = LoggerFactory.getLogger(MybatisRoadAccidentDataLoader.class);

    public static final String KEY_SIZE_OF_PUTTER = "putterSize";

    public static final String KEY_CAPACITY_OF_TO_PUT_QUEUE = "capacityOfToPutQueue";

    public static final String DEFAULT_SIZE_OF_PUTTER = "6";

    @Autowired
    @Qualifier("mybatisDataLoadService")
    private DataLoadService dataLoadService;

    @Autowired
    @Qualifier("dataSourceScanner")
    private RoadAccidentDataResourceScanner scanner;

    private ExecutorService pool = Executors.newCachedThreadPool();

    private BlockingQueue<RoadAccidentVo> roadAccidentVoesToPut = new ArrayBlockingQueue<RoadAccidentVo>(
            Integer.valueOf(
                    System.getProperty(KEY_CAPACITY_OF_TO_PUT_QUEUE, "2000")
            ));

    protected BlockingQueue<RoadAccidentVo> getRoadAccidentVoesToPut() {
        return roadAccidentVoesToPut;
    }

    protected DataLoadService getDataLoadService() {
        return dataLoadService;
    }

    protected RoadAccidentDataResourceScanner getScanner() {
        return scanner;
    }

    protected ExecutorService getPool() {
        return pool;
    }

    protected void loadStaticData() {
        log.info("Start to load static data.");
        doLoadStaticData();
        log.info("End up loading static data.");
    }

    protected void doLoadStaticData() {
        loadStaticEntity(WeatherCondition.class);
        loadStaticEntity(AccidentSeverity.class);
        loadStaticEntity(LightCondition.class);
        loadStaticEntity(PoliceForce.class);
        loadStaticEntity(RoadSurface.class);
        loadStaticEntity(DistrictAuthority.class);
    }

    protected Resource decideStaticResource(Class type) {
        Resource resource = null;
        if (type == DistrictAuthority.class) {
            resource = scanner.findDistrictAuthorityDataResource();
        } else if (type == WeatherCondition.class) {
            resource = scanner.findWeatherConditionDataResource();
        } else if (type == AccidentSeverity.class) {
            resource = scanner.findAccidentSeverityDataResource();
        } else if (type == LightCondition.class) {
            resource = scanner.findLightConditionDataResource();
        } else if (type == RoadSurface.class) {
            resource = scanner.findRoadSurfaceDataResource();
        } else if (type == PoliceForce.class) {
            resource = scanner.findPoliceForceDataResource();
        } else {
            throw new RuntimeException();
        }

        return resource;
    }

    protected void loadStaticEntity(Class type) {
        Resource resource = decideStaticResource(type);

        Reader reader = null;
        try {
            reader = new InputStreamReader(resource.getInputStream());
            Iterable<CSVRecord> records = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
            for (CSVRecord record : records) {
                processStaticRecord(record, type);
            }

            log.info("{} loaded.", type.getSimpleName());

        } catch (IOException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.error("Error when load {}", type.getSimpleName());
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }

    protected void processStaticRecord(CSVRecord record, Class type) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Integer code = Integer.valueOf(record.get(0));
        String label = record.get(1);

        Object entity = type.newInstance();
        type.getMethod("setCode", Integer.class).invoke(entity, code);
        type.getMethod("setLabel", String.class).invoke(entity, label);

        if (type == DistrictAuthority.class) {
            getDataLoadService().addDistrictAuthority((DistrictAuthority) entity);
        } else if (type == WeatherCondition.class) {
            getDataLoadService().addWeatherCondition((WeatherCondition) entity);
        } else if (type == AccidentSeverity.class) {
            getDataLoadService().addAccidentSeverity((AccidentSeverity) entity);
        } else if (type == LightCondition.class) {
            getDataLoadService().addLightCondition((LightCondition) entity);
        } else if (type == RoadSurface.class) {
            getDataLoadService().addRoadSurface((RoadSurface) entity);
        } else if (type == PoliceForce.class) {
            getDataLoadService().addPoliceForce((PoliceForce) entity);
        } else {
            throw new RuntimeException();
        }

        return;
    }


    protected void loadRoadAccidentData() {
        doLoadRoadAccidentData();
    }

    protected void doLoadRoadAccidentData() {

        MybatisRoadAccidentDataReader reader = new MybatisRoadAccidentDataReader(
                this.getRoadAccidentVoesToPut(),
                this.getScanner().findRoadAccidentDataResources()
        );

        pool.submit(reader);

        int sizeOfPutter = Integer.valueOf(System.getProperty(KEY_SIZE_OF_PUTTER, DEFAULT_SIZE_OF_PUTTER));

        Callable[] putters = new MybatisRoadAccidentDataPutter[sizeOfPutter];
        final List<Future<Long>> results = new ArrayList<Future<Long>>();

        log.info("create {} putters", sizeOfPutter);
        Arrays.stream(putters).forEach((putter) -> {
            putter = createPutter();

            Future<Long> result = pool.submit(putter);
            results.add(result);
        });



        Long totalAmount = results.stream().mapToLong((r) -> {
            try {
                return r.get();
            } catch (InterruptedException | ExecutionException e) {
                log.error("ERROR when loading", e);
                return 0L;
            }
        }).sum();

        log.info("=======    Loading finished   =============");
        log.info("Finally put totally {} records by '{}'.", totalAmount, new Date().toString());
        log.info("<<<<<<< loading end at <{}>.", new Date().toString());
    }

    protected Callable createPutter(){
        return new MybatisRoadAccidentDataPutter(
                getRoadAccidentVoesToPut(),
                this.getDataLoadService(),
                pool
        );
    }


    @Override
    public void load() {
        log.info(">>>> loading start at <{}>.", new Date().toString());
        Date startAt = new Date();
        Runnable hook = ()->{
            log.info("HOOK: start at '{}'", startAt.toString());
            Date endAt = new Date();
            log.info("HOOK: end at '{}'", endAt.toString());

            long startTime = startAt.getTime();
            long endTime = endAt.getTime();

            double minutes = (endTime - startTime)*1.0/(1000*60*1.0);
            log.info("HOOK: {} minutes totally spent.", minutes);
        };

        Runtime.getRuntime().addShutdownHook(new Thread(hook));

        loadStaticData();
        loadRoadAccidentData();
    }


}
