package com.gl.roadaccidents.mybatis.repository;

import com.gl.roadaccidents.model.AccidentSeverity;
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

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;

/**
 * Created by gavin on 16-5-18.
 */
@ContextConfiguration(locations = {"classpath:mybatis/repository-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Rollback(false)
public class AccidentSeverityRepositoryTest {
    @Autowired
    AccidentSeverityRepository repo;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testInsertOne(){
        AccidentSeverity as = new AccidentSeverity();
        as.setCreateAt(new Date());
        as.setUpdateAt(new Date());
        as.setCode(1000);
        as.setLabel("my-batis");

        repo.insertOne(as);
    }

    @Test
    public void testFindAll(){
        AccidentSeverity as = new AccidentSeverity();
        as.setCreateAt(new Date());
        as.setUpdateAt(new Date());
        as.setCode(1000);
        as.setLabel("my-batis");

        repo.insertOne(as);

        List<AccidentSeverity> accidentSeverities = repo.findAll();
        assertThat(accidentSeverities, notNullValue());
        assertThat(accidentSeverities.size(), greaterThan(0));


    }
}
