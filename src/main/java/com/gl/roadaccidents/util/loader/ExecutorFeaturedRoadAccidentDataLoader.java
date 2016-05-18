package com.gl.roadaccidents.util.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by gavin on 16-5-17.
 */
public class ExecutorFeaturedRoadAccidentDataLoader extends RoadAccidentDataLoader{
    private static final Logger log = LoggerFactory.getLogger(ExecutorFeaturedRoadAccidentDataLoader.class);
    @Override
    protected void doLoadRoadAccidentData() {
        ExecutorService pool = Executors.newCachedThreadPool();

        RoadAccidentCvsResourceReader reader = new RoadAccidentCvsResourceReader(
                this.getScanner().findRoadAccidentDataResources(),
                this.getRoadAccidentVosToPut()
        );

        RoadAccidentDataPutter putterOne = new RoadAccidentDataPutter(
                getRoadAccidentVosToPut(),
                this.getJpaDataLoadService(),
                pool
        );

        RoadAccidentDataPutter putterTwo = new RoadAccidentDataPutter(
                getRoadAccidentVosToPut(),
                this.getJpaDataLoadService(),
                pool
        );

        RoadAccidentDataPutter putterThree = new RoadAccidentDataPutter(
                getRoadAccidentVosToPut(),
                this.getJpaDataLoadService(),
                pool
        );

        RoadAccidentDataPutter putterFour = new RoadAccidentDataPutter(
                getRoadAccidentVosToPut(),
                this.getJpaDataLoadService(),
                pool
        );

        RoadAccidentDataPutter putterFive = new RoadAccidentDataPutter(
                getRoadAccidentVosToPut(),
                this.getJpaDataLoadService(),
                pool
        );

        pool.submit(reader);
        Future<Long> ret1 =  pool.submit(putterOne);
        Future<Long> ret2 = pool.submit(putterTwo);
        Future<Long> ret3 = pool.submit(putterThree);
        Future<Long> ret4 = pool.submit(putterFour);
        Future<Long> ret5 = pool.submit(putterFive);


        log.info("=======    Loading finished   =============");
        try {
            Long ret = ret1.get() + ret2.get() + ret3.get()+ret4.get()+ret5.get();
            log.info("Finally put {} records into database", ret);
        } catch (InterruptedException | ExecutionException e) {
            log.error("ERROR when loading", e);
        }
    }
}
