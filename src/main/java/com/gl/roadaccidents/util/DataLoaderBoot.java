package com.gl.roadaccidents.util;

import com.gl.roadaccidents.config.DataLoadConfiguration;
import com.gl.roadaccidents.util.loader.RoadAccidentDataLoader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by gavin on 16-5-15.
 */
public class DataLoaderBoot {
    public static void main(String...args){
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(DataLoadConfiguration.class);
        RoadAccidentDataLoader loader = ctx.getBean(RoadAccidentDataLoader.class);

        loader.load();

        ctx.close();
    }
}
