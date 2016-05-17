package com.gl.roadaccidents.config;

import com.gl.roadaccidents.service.ConfigurableRoadAccidentDataResourceScanner;
import com.gl.roadaccidents.service.RoadAccidentDataResourceScanner;
import com.gl.roadaccidents.util.loader.ExecutorFeaturedRoadAccidentDataLoader;
import com.gl.roadaccidents.util.loader.RoadAccidentDataLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gavin on 16-5-15.
 */
@Configuration
@Import(RepositoryConfiguration.class)
@ComponentScan({"com.gl.roadaccidents.service"})
public class DataLoadConfiguration {

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

        scanner.setRoadAccidents(roadAccidentResources);

        return scanner;
    }

    @Bean
    public RoadAccidentDataLoader getRoadAccidentDataLoader(){
        RoadAccidentDataLoader loader = new ExecutorFeaturedRoadAccidentDataLoader();
        return loader;
    }
}
