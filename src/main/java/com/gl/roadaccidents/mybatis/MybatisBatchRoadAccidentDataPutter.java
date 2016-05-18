package com.gl.roadaccidents.mybatis;

import com.gl.roadaccidents.service.DataLoadService;
import com.gl.roadaccidents.vo.RoadAccidentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

/**
 * Created by gavin on 16-5-19.
 */
public class MybatisBatchRoadAccidentDataPutter extends MybatisRoadAccidentDataPutter{
    private static final Logger log = LoggerFactory.getLogger(MybatisBatchRoadAccidentDataPutter.class);

    public MybatisBatchRoadAccidentDataPutter(BlockingQueue<RoadAccidentVo> toStore, DataLoadService service, ExecutorService pool) {
        super(toStore, service, pool);
    }

    @Override
    protected Long doPut() throws Exception {
        log.info("do put in batch");
        return super.doPutInBatch();
    }
}
