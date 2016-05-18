package com.gl.roadaccidents.mybatis;

import com.gl.roadaccidents.model.*;
import com.gl.roadaccidents.mybatis.repository.*;
import com.gl.roadaccidents.service.DataLoadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by gavin on 16-5-18.
 */
@Service("mybatisDataLoadService")
@Transactional
public class MybatisDataLoadService implements DataLoadService {

    public void addRoadAccident(RoadAccident ra){
        ra.setCreateAt(new Date());
        roadAccidentRepository.insertOne(ra);

    }

    @Override
    public void addRoadAccident(List<RoadAccident> roadAccidentList) {
        roadAccidentList.stream().forEach((ra)->{ra.setCreateAt(now());});

        roadAccidentRepository.insertInBatch(roadAccidentList);
    }

    public void addWeatherCondition(WeatherCondition entity){
        entity.setCreateAt(now());
        weatherConditionRepository.insertOne(entity);
    }

    public void addRoadSurface(RoadSurface entity){
        entity.setCreateAt(now());
        roadSurfaceRepository.insertOne(entity);
    }

    public void addAccidentSeverity(AccidentSeverity entity){
        entity.setCreateAt(now());
        accidentSeverityRepository.insertOne(entity);
    }

    public void addPoliceForce(PoliceForce e){
        e.setCreateAt(now());
        policeForceRepository.insertOne(e);
    }

    public void addLightCondition(LightCondition e){
        e.setCreateAt(now());
        lightConditionRepository.insertOne(e);
    }

    public void addDistrictAuthority(DistrictAuthority e){
        e.setCreateAt(now());
        districtAuthorityRepository.insertOne(e);
    }

    public WeatherCondition retrieveWeatherConditionWithCode(Integer code){
        return weatherConditionRepository.findByCode(code);
    }

    public AccidentSeverity retrieveAccidentSeverityWithCode(Integer code){
        return accidentSeverityRepository.findByCode(code);
    }

    public PoliceForce retrievePoliceForceWithCode(Integer code){
        return policeForceRepository.findByCode(code);
    }

    public LightCondition retrieveLightConditionWithCode(Integer code){
        return lightConditionRepository.findByCode(code);
    }

    public DistrictAuthority retrieveDistrictAuthorityWithCode(Integer code){
        return districtAuthorityRepository.findByCode(code);
    }

    public RoadSurface retrieveRoadSurfaceWithCode(Integer code){
        return roadSurfaceRepository.findByCode(code);
    }


    private Date now(){
        return new Date();
    }

    private static final Logger log = LoggerFactory.getLogger(MybatisDataLoadService.class);
    @Autowired
    private WeatherConditionRepository weatherConditionRepository;
    @Autowired
    private RoadAccidentRepository roadAccidentRepository;

    @Autowired
    private AccidentSeverityRepository accidentSeverityRepository;

    @Autowired
    private DistrictAuthorityRepository districtAuthorityRepository;

    @Autowired
    private PoliceForceRepository policeForceRepository;

    @Autowired
    private RoadSurfaceRepository roadSurfaceRepository;

    @Autowired
    private LightConditionRepository lightConditionRepository;
}
