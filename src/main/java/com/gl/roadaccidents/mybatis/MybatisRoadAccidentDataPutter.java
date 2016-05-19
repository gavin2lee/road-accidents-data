package com.gl.roadaccidents.mybatis;

import com.gl.roadaccidents.builder.RoadAccidentBuilder;
import com.gl.roadaccidents.load.DataPutter;
import com.gl.roadaccidents.model.*;
import com.gl.roadaccidents.service.DataLoadService;
import com.gl.roadaccidents.vo.RoadAccidentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by gavin on 16-5-19.
 */
public class MybatisRoadAccidentDataPutter implements DataPutter, Callable<Long>{

    private static final Logger log = LoggerFactory.getLogger(MybatisRoadAccidentDataPutter.class);

    public static final String KEY_BATCH_SIZE = "batchSize";

    public static final String KEY_SIZE_TO_LOG_PUT = "sizeToLogPut";

    private BlockingQueue<RoadAccidentVo> toStore;
    private DataLoadService service;

    private ExecutorService pool;

    public MybatisRoadAccidentDataPutter(BlockingQueue<RoadAccidentVo> toStore, DataLoadService service, ExecutorService pool) {
        this.toStore = toStore;
        this.service = service;
        this.pool = pool;
    }

    protected BlockingQueue<RoadAccidentVo> getToStore() {
        return toStore;
    }

    protected DataLoadService getService() {
        return service;
    }

    protected ExecutorService getPool() {
        return pool;
    }

    @Override
    public Long put() throws Exception {
        return doPut();
    }

    protected  Long doPut() throws Exception {
        return doSinglePut();
    }

    protected Long doPutInBatch() throws  Exception{
        long count = 0L;

        int batchSize = Integer.valueOf(System.getProperty(KEY_BATCH_SIZE, "100"));
        int sizeToLog = Integer.valueOf(System.getProperty(KEY_SIZE_TO_LOG_PUT, "1000"));
        List<RoadAccident> batch = new ArrayList<>(batchSize);

        while(true){
            RoadAccidentVo vo = toStore.poll(10L, TimeUnit.SECONDS);
            if(vo == null){
                putRoadAccidentList(batch);

                if(pool != null){
                    pool.shutdown();
                }

                log.info("No object got from queue and try to exit at '{}'.", new Date().toString());
                break;
            }else{
                count++;
                RoadAccident ra = processRoadAccidentVo(vo);
                batch.add(ra);

                if(batch.size() >= batchSize){
                    log.debug("batch size is now {} and try to insert.", batch.size());
                    putRoadAccidentList(batch);
                    batch.clear();
                }

                if (count % sizeToLog == 0) {
                    log.debug("Put {}", count);
                }
            }

        }

        log.info("Put {} and EXIT ", count);
        return count;
    }

    protected void putRoadAccidentList(List<RoadAccident> batch){
        if(batch.isEmpty()){
            return;
        }

        List<RoadAccident> toInsertList = new ArrayList<>(batch.size());
        toInsertList.addAll(batch);
        batch.clear();

        service.addRoadAccident(toInsertList);
    }

    protected Long doSinglePut() throws  Exception{
        long count = 0L;
        RoadAccidentVo vo = toStore.poll(10L, TimeUnit.SECONDS);
        int sizeToLog = Integer.valueOf(System.getProperty(KEY_SIZE_TO_LOG_PUT, "1000"));

        while (true) {
            if (vo == null) {
                log.warn("No object polled from queue. Try to exit at '{}'.", new Date().toString());
                if (pool != null) {
                    pool.shutdown();
                }

                break;
            } else {
                count++;
                RoadAccident ra = processRoadAccidentVo(vo);
                service.addRoadAccident(ra);

                if (count % sizeToLog == 0) {
                    log.debug("Put {}", count);
                }

                vo = toStore.poll(10L, TimeUnit.SECONDS);
            }
        }

        log.info("EXIT");

        return count;
    }

    @Override
    public Long call() throws Exception {
        log.info("START at '{}'", new Date().toString());

        Long result = put();

        log.info("END and put {} records at '{}'.", result, new Date().toString());

        return result;
    }

    private RoadAccident processRoadAccidentVo(RoadAccidentVo vo) {
        Date occurOn;
        Date occurAt;

        try {
            occurOn = new SimpleDateFormat("dd/MM/yyyy").parse(vo.getOccurOn());

        } catch (ParseException e) {
            log.warn("Malformed occurOn : " + vo.toString());
            occurOn = null;
        }

        try {
            occurAt = new SimpleDateFormat("HH:mm").parse(vo.getOccurAt());
        } catch (ParseException e) {
            log.warn("Malformed occurAt: " + vo.toString());
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

    private PoliceForce findPoliceForce(String code) {
        return service.retrievePoliceForceWithCode(Integer.valueOf(code));
    }

    private AccidentSeverity findAccidentSeverity(String code) {
        return service.retrieveAccidentSeverityWithCode(Integer.valueOf(code));
    }

    private DistrictAuthority findDistrictAuthority(String code) {
        return service.retrieveDistrictAuthorityWithCode(Integer.valueOf(code));
    }

    private LightCondition findLightCondition(String code) {
        return service.retrieveLightConditionWithCode(Integer.valueOf(code));
    }

    private WeatherCondition findWeatherCondition(String code) {
        return service.retrieveWeatherConditionWithCode(Integer.valueOf(code));
    }

    private RoadSurface findRoadSurface(String code) {
        return service.retrieveRoadSurfaceWithCode(Integer.valueOf(code));
    }


}
