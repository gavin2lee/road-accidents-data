package com.gl.roadaccidents.service;

import com.gl.roadaccidents.model.*;

import java.util.Date;
import java.util.List;

/**
 * Created by gavin on 16-5-19.
 */
public interface DataLoadService {
    void addRoadAccident(RoadAccident ra);

    void addRoadAccident(List<RoadAccident> roadAccidentList);

    void addWeatherCondition(WeatherCondition entity);

    void addRoadSurface(RoadSurface entity);

    void addAccidentSeverity(AccidentSeverity entity);

    void addPoliceForce(PoliceForce e);

    void addLightCondition(LightCondition e);

    void addDistrictAuthority(DistrictAuthority e);

    WeatherCondition retrieveWeatherConditionWithCode(Integer code);

    AccidentSeverity retrieveAccidentSeverityWithCode(Integer code);

    PoliceForce retrievePoliceForceWithCode(Integer code);

    LightCondition retrieveLightConditionWithCode(Integer code);

    DistrictAuthority retrieveDistrictAuthorityWithCode(Integer code);

    RoadSurface retrieveRoadSurfaceWithCode(Integer code);
}
