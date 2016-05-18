package com.gl.roadaccidents.mybatis.repository;

import com.gl.roadaccidents.model.DistrictAuthority;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by gavin on 16-5-18.
 */
public interface DistrictAuthorityRepository {
    @Insert("INSERT INTO district_authority " +
            "(oid,create_at,update_at,code,label)" +
            " VALUES " +
            "(" +
            "#{oid},#{createAt},#{updateAt},#{code},#{label}" +
            ")")
    void insertOne(DistrictAuthority as);

    @Results(id="districtAuthorityResults",
            value={
                    @Result(column="create_at", property = "createAt"),
                    @Result(column="update_at", property = "updateAt")
            }
    )
    @Select("SELECT * FROM district_authority")
    List<DistrictAuthority> findAll();

    @Select("select * from district_authority where oid = #{id}")
    DistrictAuthority findById(@Param("id") Long id);

    @Select("select * from district_authority where code = #{code}")
    DistrictAuthority findByCode(@Param("code") Integer code);
}
