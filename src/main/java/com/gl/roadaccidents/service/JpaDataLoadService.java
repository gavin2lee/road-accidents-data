package com.gl.roadaccidents.service;

import com.gl.roadaccidents.model.*;
import com.gl.roadaccidents.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by gavin on 16-5-14.
 */
@Service("dataLoadService")
@Transactional
public class JpaDataLoadService {
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

    public Map<String,List<?>> retrieveStaticData(){
        List<WeatherCondition> weatherConditions = new ArrayList<WeatherCondition>();
        List<DistrictAuthority> districtAuthorities = new ArrayList<DistrictAuthority>();
        List<LightCondition> lightConditions = new ArrayList<LightCondition>();
        List<PoliceForce> policeForces = new ArrayList<PoliceForce>();
        List<AccidentSeverity> accidentSeverities = new ArrayList<AccidentSeverity>();
        List<RoadSurface> roadSurfaces = new ArrayList<RoadSurface>();

        Iterator<WeatherCondition> wcIter = weatherConditionRepository.findAll().iterator();
        while(wcIter.hasNext()){
            weatherConditions.add(wcIter.next());
        }

        Iterator<DistrictAuthority> daIter = districtAuthorityRepository.findAll().iterator();
        while(daIter.hasNext()){
            districtAuthorities.add(daIter.next());
        }

        Iterator<LightCondition> lcIter = lightConditionRepository.findAll().iterator();
        while(lcIter.hasNext()){
            lightConditions.add(lcIter.next());
        }

        Iterator<PoliceForce> pfIter = policeForceRepository.findAll().iterator();
        while(pfIter.hasNext()){
            policeForces.add(pfIter.next());
        }

        Iterator<AccidentSeverity> asIter = accidentSeverityRepository.findAll().iterator();
        while(asIter.hasNext()){
            accidentSeverities.add(asIter.next());
        }

        Iterator<RoadSurface> rsIter = roadSurfaceRepository.findAll().iterator();
        while(rsIter.hasNext()){
            roadSurfaces.add(rsIter.next());
        }

        Map<String, List<?>> staticData = new HashMap<String, List<?>>();
        staticData.put(WeatherCondition.class.getName(), weatherConditions);
        staticData.put(RoadSurface.class.getName(),roadSurfaces);
        staticData.put(AccidentSeverity.class.getName(),accidentSeverities);
        staticData.put(PoliceForce.class.getName(),policeForces);
        staticData.put(LightCondition.class.getName(),lightConditions);
        staticData.put(DistrictAuthority.class.getName(), districtAuthorities);


        return staticData;
    }

    public void addRoadAccident(RoadAccident e){
        e.setCreateAt(now());
        roadAccidentRepository.save(e);
    }

    public void clearRoadAccident(){
        roadAccidentRepository.deleteAll();
    }

    private Date now(){
        return new Date();
    }
}
