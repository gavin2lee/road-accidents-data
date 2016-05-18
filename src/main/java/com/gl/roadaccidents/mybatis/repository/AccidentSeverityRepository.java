package com.gl.roadaccidents.mybatis.repository;

import com.gl.roadaccidents.model.AccidentSeverity;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by gavin on 16-5-18.
 */
public interface AccidentSeverityRepository {


    @Insert("INSERT INTO accident_severity " +
            "(oid,create_at,update_at,code,label)" +
            " VALUES " +
            "(" +
            "#{oid},#{createAt},#{updateAt},#{code},#{label}" +
            ")")
    void insertOne(AccidentSeverity as);

    @Results(id="accidentSeverityResults",
         value={
                 @Result(column="create_at", property = "createAt"),
                 @Result(column="update_at", property = "updateAt")
         }
    )
    @Select("SELECT * FROM accident_severity")
    List<AccidentSeverity> findAll();

    @Select("select * from accident_severity where oid = #{id}")
    AccidentSeverity findById(@Param("id") Long id);

    @Select("select * from accident_severity where code = #{code}")
    AccidentSeverity findByCode(@Param("code") Integer code);
}
