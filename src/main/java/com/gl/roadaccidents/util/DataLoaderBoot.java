package com.gl.roadaccidents.util;

import com.gl.roadaccidents.config.DataLoadConfiguration;
import com.gl.roadaccidents.util.loader.RoadAccidentDataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by gavin on 16-5-15.
 */
public class DataLoaderBoot {
    private static final Logger log = LoggerFactory.getLogger(DataLoaderBoot.class);


    public static void main(String... args) {
        log.info("START");
        System.setProperty("clearData", "true");
        try {
            AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataLoadConfiguration.class);
            RoadAccidentDataLoader loader = ctx.getBean(RoadAccidentDataLoader.class);

            loader.load();

//            try {
//                Thread.sleep(1 * 1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }

            //ctx.close();

        } catch (Throwable e) {
            log.error("ERROR when loading data.", e);
        }

        log.info("END");
    }
}
