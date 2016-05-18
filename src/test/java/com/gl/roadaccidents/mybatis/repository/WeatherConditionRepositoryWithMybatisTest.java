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

/**
 * Created by gavin on 16-5-18.com.gl.roadaccidents.mybatis.repository
 */
@ContextConfiguration(locations = {"classpath:mybatis/repository-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback(false)
public class WeatherConditionRepositoryWithMybatisTest {
    @Autowired
    WeatherConditionRepository repo;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSave(){
        WeatherCondition w = new WeatherCondition();
        w.setOid(100L);
        w.setCode(100001);
        w.setLabel("test-mybatis");

        w.setCreateAt(new Date());
        w.setUpdateAt(new Date());

        repo.saveOne(w);

//        WeatherCondition  = repo.findByCode(1);
    }
}
