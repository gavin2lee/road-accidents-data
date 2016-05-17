package com.gl.roadaccidents.util.loader;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by gavin on 16-5-17.
 */
public class ExecutorFeaturedRoadAccidentDataLoader extends RoadAccidentDataLoader{
    @Override
    protected void doLoadRoadAccidentData() {
        ExecutorService pool = Executors.newCachedThreadPool();

        RoadAccidentCvsResourceReader reader = new RoadAccidentCvsResourceReader(
                this.getScanner().findRoadAccidentDataResources(),
                this.getRoadAccidentVosToPut()
        );

        RoadAccidentDataPutter putter = new RoadAccidentDataPutter(
                getRoadAccidentVosToPut(),
                this.getDataLoadService(),
                pool
        );

        pool.submit(reader);
        pool.submit(putter);
    }
}
