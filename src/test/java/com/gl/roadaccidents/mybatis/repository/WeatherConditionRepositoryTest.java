package com.gl.roadaccidents.mybatis.repository;

import com.gl.roadaccidents.model.WeatherCondition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by gavin on 16-5-18.com.gl.roadaccidents.mybatis.repository
 */
@ContextConfiguration(locations = {"classpath:mybatis/repository-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback(false)
public class WeatherConditionRepositoryTest {
    @Autowired
    WeatherConditionRepository repo;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInsertOne(){
        WeatherCondition w = new WeatherCondition();
        w.setOid(100L);
        w.setCode(100001);
        w.setLabel("test-mybatis");

        w.setCreateAt(new Date());
        w.setUpdateAt(new Date());

        repo.insertOne(w);

    }

    @Test
    public void testFindAll(){
        WeatherCondition w = new WeatherCondition();
        w.setOid(100L);
        w.setCode(100001);
        w.setLabel("test-mybatis");

        w.setCreateAt(new Date());
        w.setUpdateAt(new Date());

        repo.insertOne(w);

        List<WeatherCondition> weatherConditionList = repo.findAll();

        assertThat(weatherConditionList, notNullValue());
        assertThat(weatherConditionList.size(), greaterThan(0));
    }
}
