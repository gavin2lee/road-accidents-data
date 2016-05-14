package com.gl.roadaccidents.service;

import org.springframework.core.io.Resource;

import java.io.File;
import java.util.List;

/**
 * Created by gavin on 16-5-14.
 */
public interface RoadAccidentDataResourceScanner {
    List<Resource> findRoadAccidentDataResources();
    Resource findAccidentSeverityDataResource();
    Resource findDistrictAuthorityDataResource();
    Resource findLightConditionDataResource();
    Resource findPoliceForceDataResource();
    Resource findRoadSurfaceDataResource();
    Resource findWeatherConditionDataResource();
}
