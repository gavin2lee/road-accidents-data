package com.gl.roadaccidents.repository;

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

/**
 * Created by gavin on 16-5-14.
 */
@ContextConfiguration(locations = "classpath:config/repository.xml")
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
    public void testSaveWeatherCondition(){
        WeatherCondition w = new WeatherCondition();
        w.setCode(90001);
        w.setLabel("test");
        w.setCreateAt(new Date());

        repo.save(w);
    }
}
