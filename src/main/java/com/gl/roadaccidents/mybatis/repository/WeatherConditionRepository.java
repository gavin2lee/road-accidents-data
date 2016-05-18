package com.gl.roadaccidents.mybatis.repository;

import com.gl.roadaccidents.model.WeatherCondition;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by gavin on 16-5-18.
 */
public interface WeatherConditionRepository {

    @Results(id="WeatherConditionResults",
            value={
                    @Result(column="create_at", property = "createAt"),
                    @Result(column="update_at", property = "updateAt")
            }
    )
    @Select("select * from weather_condition")
    List<WeatherCondition> findAll();


    WeatherCondition findById(Long id);

    WeatherCondition findByCode(Integer code);

    void insertOne(WeatherCondition w);
}
