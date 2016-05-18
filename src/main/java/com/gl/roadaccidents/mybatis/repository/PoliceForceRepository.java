package com.gl.roadaccidents.mybatis.repository;

import com.gl.roadaccidents.model.PoliceForce;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by gavin on 16-5-18.
 */
public interface PoliceForceRepository {
    @Insert("INSERT INTO police_force " +
            "(oid,create_at,update_at,code,label)" +
            " VALUES " +
            "(" +
            "#{oid},#{createAt},#{updateAt},#{code},#{label}" +
            ")")
    void insertOne(PoliceForce as);

    @Results(id="PoliceForceResults",
            value={
                    @Result(column="create_at", property = "createAt"),
                    @Result(column="update_at", property = "updateAt")
            }
    )
    @Select("SELECT * FROM police_force")
    List<PoliceForce> findAll();

    @Select("select * from police_force where oid = #{id}")
    PoliceForce findById(@Param("id") Long id);

    @Select("select * from police_force where code = #{code}")
    PoliceForce findByCode(@Param("code") Integer code);
}
