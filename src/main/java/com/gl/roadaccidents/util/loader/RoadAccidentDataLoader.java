package com.gl.roadaccidents.util.loader;

import com.gl.roadaccidents.builder.RoadAccidentBuilder;
import com.gl.roadaccidents.model.*;
import com.gl.roadaccidents.service.JpaDataLoadService;
import com.gl.roadaccidents.service.RoadAccidentDataResourceScanner;
import com.gl.roadaccidents.vo.RoadAccidentVo;
import com.gl.roadaccidents.vo.RoadAccidentVoBuilder;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by gavin on 16-5-14.
 */
public class RoadAccidentDataLoader {
    private static final Logger log = LoggerFactory.getLogger(RoadAccidentDataLoader.class);

    public static final String KEY_CLEAR_ROAD_ACCIDENTS = "clearData";

    @Autowired
    private JpaDataLoadService jpaDataLoadService;

    @Autowired
    @Qualifier("dataSourceScanner")
    private RoadAccidentDataResourceScanner scanner;

    private BlockingQueue<RoadAccidentVo> roadAccidentVosToPut = new ArrayBlockingQueue<RoadAccidentVo>(1000);

    public BlockingQueue<RoadAccidentVo> getRoadAccidentVosToPut() {
        return roadAccidentVosToPut;
    }


    public void load() {
        loadStaticData();
        loadRoadAccidentData();
    }

    protected JpaDataLoadService getJpaDataLoadService() {
        return jpaDataLoadService;
    }

    protected RoadAccidentDataResourceScanner getScanner() {
        return scanner;
    }

    protected void loadStaticData() {
        log.debug("Start to clear data.");
        clearData();
        log.debug("End for clearing data.");

        log.info("Start to load static data.");
        doLoadStaticData();
        log.info("End up loading static data.");
    }

    protected void clearData() {
        clearRoadAccidents();
        clearStaticData();
    }

    protected void clearStaticData() {
        jpaDataLoadService.clearStaticData();
    }

    protected void doLoadStaticData() {
        loadWeatherCondition();
        loadAccidentSeverity();
        loadLightCondition();
        loadPoliceForce();
        loadRoadSurface();
        loadDistrictAuthority();
    }

    private void loadDistrictAuthority() {
        Resource resource = scanner.findDistrictAuthorityDataResource();
        Reader reader = null;
        try {
            reader = new InputStreamReader(resource.getInputStream());
            Iterable<CSVRecord> records = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
            for (CSVRecord record : records) {
                Integer code = Integer.valueOf(record.get(0));
                String label = record.get(1);

                DistrictAuthority entity = new DistrictAuthority();
                entity.setCode(code);
                entity.setLabel(label);


                jpaDataLoadService.addDistrictAuthority(entity);

            }
        } catch (IOException e) {
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

    private void loadRoadSurface() {
        Resource resource = scanner.findRoadSurfaceDataResource();
        Reader reader = null;
        try {
            reader = new InputStreamReader(resource.getInputStream());
            Iterable<CSVRecord> records = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
            for (CSVRecord record : records) {
                Integer code = Integer.valueOf(record.get(0));
                String label = record.get(1);

                RoadSurface entity = new RoadSurface();
                entity.setCode(code);
                entity.setLabel(label);


                jpaDataLoadService.addRoadSurface(entity);

            }
        } catch (IOException e) {
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

    private void loadPoliceForce() {
        Resource resource = scanner.findPoliceForceDataResource();
        Reader reader = null;
        try {
            reader = new InputStreamReader(resource.getInputStream());
            Iterable<CSVRecord> records = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
            for (CSVRecord record : records) {
                Integer code = Integer.valueOf(record.get(0));
                String label = record.get(1);

                PoliceForce entity = new PoliceForce();
                entity.setCode(code);
                entity.setLabel(label);


                jpaDataLoadService.addPoliceForce(entity);

            }
        } catch (IOException e) {
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

    private void loadLightCondition() {
        Resource resource = scanner.findLightConditionDataResource();
        Reader reader = null;
        try {
            reader = new InputStreamReader(resource.getInputStream());
            Iterable<CSVRecord> records = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
            for (CSVRecord record : records) {
                Integer code = Integer.valueOf(record.get(0));
                String label = record.get(1);

                LightCondition entity = new LightCondition();
                entity.setCode(code);
                entity.setLabel(label);


                jpaDataLoadService.addLightCondition(entity);

            }
        } catch (IOException e) {
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

    private void loadAccidentSeverity() {
        Resource resource = scanner.findAccidentSeverityDataResource();
        Reader reader = null;
        try {
            reader = new InputStreamReader(resource.getInputStream());
            Iterable<CSVRecord> records = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
            for (CSVRecord record : records) {
                Integer code = Integer.valueOf(record.get(0));
                String label = record.get(1);

                AccidentSeverity entity = new AccidentSeverity();
                entity.setCode(code);
                entity.setLabel(label);


                jpaDataLoadService.addAccidentSeverity(entity);

            }
        } catch (IOException e) {
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

    private void loadWeatherCondition() {
        Resource resource = scanner.findWeatherConditionDataResource();
        Reader reader = null;
        try {
            reader = new InputStreamReader(resource.getInputStream());
            Iterable<CSVRecord> records = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
            for (CSVRecord record : records) {
                Integer code = Integer.valueOf(record.get(0));
                String label = record.get(1);

                WeatherCondition entity = new WeatherCondition();
                entity.setCode(code);
                entity.setLabel(label);

                entity.setCreateAt(new Date());

                jpaDataLoadService.addWeatherCondition(entity);

            }
        } catch (IOException e) {
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

    protected void loadRoadAccidentData() {
        doLoadRoadAccidentData();
    }

    protected void clearRoadAccidents() {
        String clearData = System.getProperty(KEY_CLEAR_ROAD_ACCIDENTS);
        boolean hasToClearRoadAccidentData = Boolean.valueOf(clearData);
        if (hasToClearRoadAccidentData) {
            jpaDataLoadService.clearRoadAccident();
        }
    }

    protected void doLoadRoadAccidentData() {
        FutureTask<Long> reader = new FutureTask<Long>(
                new RoadAccidentCvsResourceReader(
                        scanner.findRoadAccidentDataResources(),
                        this.getRoadAccidentVosToPut()
                )
        );
        FutureTask<Long> putter = new FutureTask<Long>(
                new RoadAccidentDataPutter(getRoadAccidentVosToPut(), this.jpaDataLoadService, null)
        );

        new Thread(reader).start();
        new Thread(putter).start();
    }

    public static class RoadAccidentCvsResourceReader implements Callable<Long> {
        private BlockingQueue<RoadAccidentVo> toStore;
        private List<Resource> resourcesToRead;

        private static final Logger log = LoggerFactory.getLogger(RoadAccidentCvsResourceReader.class);

        public RoadAccidentCvsResourceReader(List<Resource> resourcesToRead, BlockingQueue<RoadAccidentVo> queue) {
            this.resourcesToRead = resourcesToRead;
            this.toStore = queue;
        }

        @Override
        public Long call() throws Exception {
            log.info(">>>>>>>>>>>>>>>>>> Reading start <<<<<<<<<<<<<<<<<<<<<");
            log.info(new Date().toString());
            long amountOfRecords = 0L;
            log.info("size of resources to read : " + resourcesToRead.size());
            for (Resource r : resourcesToRead) {
                amountOfRecords += processOneResource(r);
            }

            log.info("amount of read records : " + amountOfRecords);
            log.info(" >>>>>>>>>>>>>>>>>   Reading finished  <<<<<<<<<<<<<<<<");
            log.info(new Date().toString());
            return amountOfRecords;
        }

        private Long processOneResource(Resource resource) {
            long result = 0L;
            boolean midStop = "true".equalsIgnoreCase(System.getProperty("midStop"));
            Reader reader = null;
            try {
                reader = new InputStreamReader(resource.getInputStream());
                Iterable<CSVRecord> records = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
                for (CSVRecord record : records) {
                    RoadAccidentVo vo = parseOneRecord(record);
                    this.toStore.offer(vo, 10, TimeUnit.SECONDS);
                    //log.debug("store :" + vo.toString());
                    result++;
                    if (result % 100 == 0) {
                        log.debug(String.format("Have already read %s from %s at %s", "" + result, resource.getURI().toString(), new Date().toString()));
                    }

                    if (midStop && (result >= 3000)) {
                        break;
                    }
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (IOException e) {
                    }
                }
            }
            return result;
        }

        private RoadAccidentVo parseOneRecord(CSVRecord record) {
            String accidentId = record.get("Accident_Index");
            RoadAccidentVo vo = new RoadAccidentVoBuilder(accidentId)
                    .setLongitude(record.get("Longitude"))
                    .setLatitude(record.get("Latitude"))
                    .setPoliceForce(record.get("Police_Force"))
                    .setAccidentSeverity(record.get("Accident_Severity"))
                    .setNumberOfVehicles(record.get("Number_of_Vehicles"))
                    .setNumberOfCasualties(record.get("Number_of_Casualties"))
                    .setOccurOn(record.get("Date"))
                    .setDayOfWeek(record.get("Day_of_Week"))
                    .setOccurAt(record.get("Time"))
                    .setDistrictAuthority(record.get("Local_Authority_(District)"))
                    .setLightCondition(record.get("Light_Conditions"))
                    .setWeatherCondition(record.get("Weather_Conditions"))
                    .setRoadSurface(record.get("Road_Surface_Conditions"))
                    .build();
            return vo;
        }
    }

    public static class RoadAccidentDataPutter implements Callable<Long> {
        private BlockingQueue<RoadAccidentVo> toStore;
        private JpaDataLoadService service;

        private ExecutorService pool;

        private Map<String, List<?>> staticData;

        private ReentrantLock lock = new ReentrantLock();

        private static final Logger log = LoggerFactory.getLogger(RoadAccidentDataPutter.class);

        public RoadAccidentDataPutter(BlockingQueue<RoadAccidentVo> toStore, JpaDataLoadService service, ExecutorService pool) {
            this.toStore = toStore;
            this.service = service;
            this.pool = pool;
        }

        @Override
        public Long call() throws Exception {
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>> Putting  start    <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            log.info(new Date().toString());
            lock.lock();
            try {
                if (staticData == null) {
                    staticData = service.retrieveStaticData();
                }
            } finally {
                lock.unlock();
            }

            long count = 0L;
            RoadAccidentVo vo = toStore.poll(10L, TimeUnit.SECONDS);

            while (true) {
                if (vo == null) {
                    log.warn("No object polled from queue. Try to exit.");
                    if (pool != null) {
                        pool.shutdown();
                    }

                    break;
                } else {
                    count++;
                    RoadAccident ra = processRoadAccidentVo(vo);
                    service.addRoadAccident(ra);

                    if (count % 100 == 0) {
                        log.debug(String.format("Have already put %s to %s at %s", "" + count, "road_accident", new Date().toString()));
                    }

                    vo = toStore.poll(10L, TimeUnit.SECONDS);
                }
            }

//            while ((vo = toStore.poll(10L, TimeUnit.SECONDS)) != null) {
//                count++;
//                RoadAccident ra = processRoadAccidentVo(vo);
//                service.addRoadAccident(ra);
//
//                if(count % 1000 == 0){
//                    log.debug(String.format("Have already put %s to %s at %s", ""+count, "road_accident", new Date().toString()));
//                }
//            }

            System.out.println("*******************  Finished ************************");

            log.info(String.format("Put %s records into database.", count));
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>   Putting finished <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
            log.info(new Date().toString());

//            Thread.currentThread().sleep(1000);

//            if(pool != null){
//                pool.shutdown();
//            }

            return count;
        }

        private PoliceForce findPoliceForce(String code) {
            List<?> entities = staticData.get(PoliceForce.class.getName());
            for (Object o : entities) {
                PoliceForce pf = (PoliceForce) o;
                if (pf.getCode() == Integer.valueOf(code)) {
                    return pf;
                }
            }

            return null;
        }

        private AccidentSeverity findAccidentSeverity(String code) {
            List<?> entities = staticData.get(AccidentSeverity.class.getName());
            for (Object o : entities) {
                AccidentSeverity e = (AccidentSeverity) o;
                if (e.getCode() == Integer.valueOf(code)) {
                    return e;
                }
            }

            return null;
        }

        private DistrictAuthority findDistrictAuthority(String code) {
            List<?> entities = staticData.get(DistrictAuthority.class.getName());
            for (Object o : entities) {
                DistrictAuthority e = (DistrictAuthority) o;
                if (e.getCode() == Integer.valueOf(code)) {
                    return e;
                }
            }

            return null;
        }

        private LightCondition findLightCondition(String code) {
            List<?> entities = staticData.get(LightCondition.class.getName());
            for (Object o : entities) {
                LightCondition e = (LightCondition) o;
                if (e.getCode() == Integer.valueOf(code)) {
                    return e;
                }
            }

            return null;
        }

        private WeatherCondition findWeatherCondition(String code) {
            List<?> entities = staticData.get(WeatherCondition.class.getName());
            for (Object o : entities) {
                WeatherCondition e = (WeatherCondition) o;
                if (e.getCode() == Integer.valueOf(code)) {
                    return e;
                }
            }

            return null;
        }

        private RoadSurface findRoadSurface(String code) {
            List<?> entities = staticData.get(RoadSurface.class.getName());
            for (Object o : entities) {
                RoadSurface e = (RoadSurface) o;
                if (e.getCode() == Integer.valueOf(code)) {
                    return e;
                }
            }

            return null;
        }

        private RoadAccident processRoadAccidentVo(RoadAccidentVo vo) {
            Date occurOn;
            Date occurAt;

            try {
                occurOn = new SimpleDateFormat("dd/MM/yyyy").parse(vo.getOccurOn());

            } catch (ParseException e) {
                log.warn("Malformed date : " + vo.getOccurOn(), e);
                occurOn = null;
            }

            try {
                occurAt = new SimpleDateFormat("HH:mm").parse(vo.getOccurAt());
            } catch (ParseException e) {
                log.warn("Malformed time: " + vo.getOccurAt(), e);
                occurAt = null;
            }
            RoadAccident ra = RoadAccidentBuilder.newBuilder().setAccidentIndex(vo.getAccidentIndex())
                    .setLongitude(Double.valueOf(vo.getLongitude()))
                    .setLatitude(Double.valueOf(vo.getLatitude()))
                    .setDayOfWeek(Integer.valueOf(vo.getDayOfWeek()))
                    .setPoliceForce(findPoliceForce(vo.getPoliceForce()))
                    .setAccidentSeverity(findAccidentSeverity(vo.getAccidentSeverity()))
                    .setNumberOfVehicles(Integer.valueOf(vo.getNumberOfVehicles()))
                    .setNumberOfCasualties(Integer.valueOf(vo.getNumberOfCasualties()))
                    .setOccurOn(occurOn)
                    .setOccurAt(occurAt)
                    .setDistrictAuthority(findDistrictAuthority(vo.getDistrictAuthority()))
                    .setLightCondition(findLightCondition(vo.getLightCondition()))
                    .setWeatherCondition(findWeatherCondition(vo.getWeatherCondition()))
                    .setRoadSurface(findRoadSurface(vo.getRoadSurface()))
                    .build();

            return ra;
        }
    }
}
