package com.gl.roadaccidents.mybatis.repository;

import com.gl.roadaccidents.model.WeatherCondition;

import java.util.List;

/**
 * Created by gavin on 16-5-18.
 */
public interface WeatherConditionRepository {
    List<WeatherCondition> findAll();
    WeatherCondition findById(Long id);
    WeatherCondition findByCode(Integer code);
    void saveOne(WeatherCondition w);
}
