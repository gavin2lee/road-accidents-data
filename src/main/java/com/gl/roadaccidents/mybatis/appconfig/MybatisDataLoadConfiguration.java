package com.gl.roadaccidents.mybatis.appconfig;

import com.gl.roadaccidents.config.RepositoryConfiguration;
import com.gl.roadaccidents.load.DataLoader;
import com.gl.roadaccidents.mybatis.MybatisBatchRoadAccidentDataLoader;
import com.gl.roadaccidents.mybatis.MybatisRoadAccidentDataLoader;
import com.gl.roadaccidents.service.ConfigurableRoadAccidentDataResourceScanner;
import com.gl.roadaccidents.service.RoadAccidentDataResourceScanner;
import com.gl.roadaccidents.util.loader.ExecutorFeaturedRoadAccidentDataLoader;
import com.gl.roadaccidents.util.loader.RoadAccidentDataLoader;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gavin on 16-5-15.
 */
@Configuration
@ImportResource(locations={"classpath:mybatis/repository-mybatis.xml"})
@ComponentScan({"com.gl.roadaccidents.mybatis"})
public class MybatisDataLoadConfiguration {

    @Bean(name="dataSourceScanner")
    public RoadAccidentDataResourceScanner getScanner(){
        ConfigurableRoadAccidentDataResourceScanner scanner = new ConfigurableRoadAccidentDataResourceScanner();
        scanner.setWeatherCondition(new ClassPathResource("data/weather_conditions.csv"));
        scanner.setAccidentSeverity(new ClassPathResource("data/accident_severity.csv"));
        scanner.setDistrictAuthority(new ClassPathResource("data/district_authority.csv"));
        scanner.setLightCondition(new ClassPathResource("data/light_conditions.csv"));
        scanner.setPoliceForce(new ClassPathResource("data/police_force.csv"));
        scanner.setRoadSurface(new ClassPathResource("data/road_surface.csv"));

        List<Resource> roadAccidentResources = new ArrayList<Resource>();
        roadAccidentResources.add(new ClassPathResource("data/DfTRoadSafety_Accidents_2009.csv"));
        roadAccidentResources.add(new ClassPathResource("data/DfTRoadSafety_Accidents_2010.csv"));
        roadAccidentResources.add(new ClassPathResource("data/DfTRoadSafety_Accidents_2011.csv"));
        roadAccidentResources.add(new ClassPathResource("data/DfTRoadSafety_Accidents_2012.csv"));
        roadAccidentResources.add(new ClassPathResource("data/DfTRoadSafety_Accidents_2013.csv"));
        roadAccidentResources.add(new ClassPathResource("data/DfTRoadSafety_Accidents_2014.csv"));

        scanner.setRoadAccidents(roadAccidentResources);

        return scanner;
    }

    @Bean(name="dataLoader")
    public DataLoader getMybatisRoadAccidentDataLoader(){
        DataLoader loader = new MybatisRoadAccidentDataLoader();
        return loader;
    }

    @Bean(name="batchDataLoader")
    public DataLoader getMybatisBatchRoadAccidentDataLoader(){
        DataLoader loader = new MybatisBatchRoadAccidentDataLoader();
        return loader;
    }
}
