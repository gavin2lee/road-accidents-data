package com.gl.roadaccidents.mybatis.repository;

import com.gl.roadaccidents.builder.RoadAccidentBuilder;
import com.gl.roadaccidents.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by gavin on 16-5-18.
 */
@ContextConfiguration(locations = {"classpath:mybatis/repository-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback(false)
public class RoadAccidentRepositoryTest {
    @Autowired
    WeatherConditionRepository weatherConditionRepository;
    @Autowired
    RoadAccidentRepository roadAccidentRepository;

    @Autowired
    AccidentSeverityRepository accidentSeverityRepository;

    @Autowired
    DistrictAuthorityRepository districtAuthorityRepository;

    @Autowired
    PoliceForceRepository policeForceRepository;

    @Autowired
    RoadSurfaceRepository roadSurfaceRepository;

    @Autowired
    LightConditionRepository lightConditionRepository;

    @Test
    public void testInsertOne(){
        WeatherCondition w = new WeatherCondition();
        w.setLabel("weather-mybatis");
        w.setCreateAt(new Date());
        w.setCode(100);
        w.setOid(10000L);

        weatherConditionRepository.insertOne(w);

        RoadAccident ra = RoadAccidentBuilder
                            .newBuilder()
                            .setAccidentIndex("aaaa")
                    .setWeatherCondition(w)
                .setOccurOn(new Date())
                .build()
                ;

        ra.setCreateAt(new Date());
        ra.setOid(1000L);
        roadAccidentRepository.insertOne(ra);
    }

    @Test
    public void testFindById(){
        WeatherCondition w = new WeatherCondition();
        w.setLabel("weather-mybatis");
        w.setCreateAt(new Date());
        w.setCode(601);
        w.setOid(6001L);

        weatherConditionRepository.insertOne(w);

        AccidentSeverity as = new AccidentSeverity();
        as.setOid(6002L);
        as.setCode(602);
        accidentSeverityRepository.insertOne(as);


        DistrictAuthority da = new DistrictAuthority();
        da.setOid(6003L);
        da.setCode(603);
        districtAuthorityRepository.insertOne(da);

        LightCondition lc = new LightCondition();
        lc.setOid(6004L);
        lc.setCode(604);
        lightConditionRepository.insertOne(lc);

        PoliceForce pf = new PoliceForce();
        pf.setCode(605);
        pf.setOid(6005L);
        policeForceRepository.insertOne(pf);


        RoadSurface rs = new RoadSurface();
        rs.setCode(606);
        rs.setOid(6006L);
        roadSurfaceRepository.insertOne(rs);



        RoadAccident ra = RoadAccidentBuilder
                .newBuilder()
                .setAccidentIndex("BBBB")
                .setWeatherCondition(w)
                .setDistrictAuthority(da)
                .setAccidentSeverity(as)
                .setRoadSurface(rs)
                .setLightCondition(lc)
                .setPoliceForce(pf)
                .setOccurOn(new Date())
                .build();

        ra.setCreateAt(new Date());
        ra.setOid(100L);
        roadAccidentRepository.insertOne(ra);



        RoadAccident result = roadAccidentRepository.findById(100L);

        assertThat(result, notNullValue());

        assertThat(result.getWeatherCondition(), notNullValue());
        assertThat(result.getWeatherCondition().getCode(), is(601));

        assertThat(result.getAccidentSeverity(), notNullValue());
        assertThat(result.getAccidentSeverity().getCode(), is(602));

        assertThat(result.getDistrictAuthority(), notNullValue());
        assertThat(result.getDistrictAuthority().getCode(), is(603));

        assertThat(result.getLightCondition(), notNullValue());
        assertThat(result.getLightCondition().getCode(), is(604));

        assertThat(result.getRoadSurface(), notNullValue());
        assertThat(result.getRoadSurface().getCode(), is(606));

        assertThat(result.getPoliceForce(), notNullValue());
        assertThat(result.getPoliceForce().getCode(), is(605));
    }

    @Test
    public void testFindAll(){
        WeatherCondition w = new WeatherCondition();
        w.setLabel("weather-mybatis");
        w.setCreateAt(new Date());
        w.setCode(601);
        w.setOid(6001L);

        weatherConditionRepository.insertOne(w);

        AccidentSeverity as = new AccidentSeverity();
        as.setOid(6002L);
        as.setCode(602);
        accidentSeverityRepository.insertOne(as);


        DistrictAuthority da = new DistrictAuthority();
        da.setOid(6003L);
        da.setCode(603);
        districtAuthorityRepository.insertOne(da);

        LightCondition lc = new LightCondition();
        lc.setOid(6004L);
        lc.setCode(604);
        lightConditionRepository.insertOne(lc);

        PoliceForce pf = new PoliceForce();
        pf.setCode(605);
        pf.setOid(6005L);
        policeForceRepository.insertOne(pf);


        RoadSurface rs = new RoadSurface();
        rs.setCode(606);
        rs.setOid(6006L);
        roadSurfaceRepository.insertOne(rs);



        RoadAccident ra = RoadAccidentBuilder
                .newBuilder()
                .setAccidentIndex("BBBB")
                .setWeatherCondition(w)
                .setDistrictAuthority(da)
                .setAccidentSeverity(as)
                .setRoadSurface(rs)
                .setLightCondition(lc)
                .setPoliceForce(pf)
                .setOccurOn(new Date())
                .build();

        ra.setCreateAt(new Date());
        ra.setOid(100L);
        roadAccidentRepository.insertOne(ra);

        RoadAccident ra2 = RoadAccidentBuilder
                .newBuilder()
                .setAccidentIndex("CCCC")
                .setOccurOn(new Date())
                .build();

        ra2.setCreateAt(new Date());
        ra2.setOid(101L);
        roadAccidentRepository.insertOne(ra2);

        List<RoadAccident> roadAccidentList = roadAccidentRepository.findAll();

        assertThat(roadAccidentList, notNullValue());
        assertThat(roadAccidentList.size(), greaterThan(1));
        assertThat(roadAccidentList.get(0).getOid(), is(101L));
    }
}
