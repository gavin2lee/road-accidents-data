package com.gl.roadaccidents.mybatis.repository;

import com.gl.roadaccidents.model.LightCondition;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by gavin on 16-5-18.
 */
public interface LightConditionRepository {
    @Insert("INSERT INTO light_condition " +
            "(oid,create_at,update_at,code,label)" +
            " VALUES " +
            "(" +
            "#{oid},#{createAt},#{updateAt},#{code},#{label}" +
            ")")
    void insertOne(LightCondition as);

    @Results(id="LightConditionResults",
            value={
                    @Result(column="create_at", property = "createAt"),
                    @Result(column="update_at", property = "updateAt")
            }
    )
    @Select("SELECT * FROM light_condition")
    List<LightCondition> findAll();

    @Select("select * from light_condition where oid = #{id}")
    LightCondition findById(@Param("id") Long id);

    @Select("select * from light_condition where code = #{code}")
    LightCondition findByCode(@Param("code") Integer code);
}
