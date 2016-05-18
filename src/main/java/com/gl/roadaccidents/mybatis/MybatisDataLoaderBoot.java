package com.gl.roadaccidents.mybatis;

import com.gl.roadaccidents.load.DataLoader;
import com.gl.roadaccidents.mybatis.appconfig.MybatisDataLoadConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by gavin on 16-5-15.
 */
public class MybatisDataLoaderBoot {
    private static final Logger log = LoggerFactory.getLogger(MybatisDataLoaderBoot.class);

    public static final String KEY_CLEAR_DATA="clearData";
    public static final String KEY_MID_STOP = "midStop";

    public static final String CLEAR_DATA="true";
    public static final String MID_STOP = "true";

    public static final String DATA_LOADER_BEAN_NAME = "dataLoader";


    public static void main(String... args) {
        System.setProperty(KEY_CLEAR_DATA, CLEAR_DATA);
        System.setProperty(KEY_MID_STOP, MID_STOP);
        try {
            AnnotationConfigApplicationContext ctx
                    = new AnnotationConfigApplicationContext(MybatisDataLoadConfiguration.class);
            DataLoader loader = ctx.getBean(DATA_LOADER_BEAN_NAME, DataLoader.class);

            loader.load();

        } catch (Throwable e) {
            log.error("ERROR when loading data.", e);
        }

        log.info("END");
    }
}
