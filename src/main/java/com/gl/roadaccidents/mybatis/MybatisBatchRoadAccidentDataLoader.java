package com.gl.roadaccidents.mybatis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * Created by gavin on 16-5-19.
 */
public class MybatisBatchRoadAccidentDataLoader extends MybatisRoadAccidentDataLoader{
    private static final Logger log = LoggerFactory.getLogger(MybatisBatchRoadAccidentDataLoader.class);

    @Override
    protected Callable createPutter() {
        log.info("Create putter : {}", MybatisBatchRoadAccidentDataPutter.class.getName());
        return new MybatisBatchRoadAccidentDataPutter(
                getRoadAccidentVoesToPut(),
                this.getDataLoadService(),
                getPool()
        );
    }
}
