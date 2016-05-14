package com.gl.roadaccidents.service;

import com.gl.roadaccidents.model.*;
import com.gl.roadaccidents.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by gavin on 16-5-14.
 */
@Service("dataLoadService")
@Transactional
public class DataLoadService {
    @Autowired
    private RoadAccidentRepository roadAccidentRepository;
    @Autowired
    private AccidentSeverityRepository accidentSeverityRepository;
    @Autowired
    private DistrictAuthorityRepository districtAuthorityRepository;
    @Autowired
    private LightConditionRepository lightConditionRepository;
    @Autowired
    private PoliceForceRepository policeForceRepository;
    @Autowired
    private RoadSurfaceRepository roadSurfaceRepository;
    @Autowired
    private WeatherConditionRepository weatherConditionRepository;

    public void clearStaticData(){
        accidentSeverityRepository.deleteAll();
        districtAuthorityRepository.deleteAll();
        lightConditionRepository.deleteAll();
        policeForceRepository.deleteAll();
        roadSurfaceRepository.deleteAll();
        weatherConditionRepository.deleteAll();
    }

    public void addWeatherCondition(WeatherCondition entity){
        entity.setCreateAt(now());
        weatherConditionRepository.save(entity);
    }

    public void addRoadSurface(RoadSurface entity){
        entity.setCreateAt(now());
        roadSurfaceRepository.save(entity);
    }

    public void addAccidentSeverity(AccidentSeverity entity){
        entity.setCreateAt(now());
        accidentSeverityRepository.save(entity);
    }

    public void addPoliceForce(PoliceForce e){
        e.setCreateAt(now());
        policeForceRepository.save(e);
    }

    public void addLightCondition(LightCondition e){
        e.setCreateAt(now());
        lightConditionRepository.save(e);
    }

    public void addDistrictAuthority(DistrictAuthority e){
        e.setCreateAt(now());
        districtAuthorityRepository.save(e);
    }

    public void addRoadAccident(RoadAccident e){
        //TODO
    }

    private Date now(){
        return new Date();
    }
}
