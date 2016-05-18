package com.gl.roadaccidents.mybatis.repository;

import com.gl.roadaccidents.model.RoadSurface;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by gavin on 16-5-18.
 */
public interface RoadSurfaceRepository {
    @Insert("INSERT INTO road_surface " +
            "(oid,create_at,update_at,code,label)" +
            " VALUES " +
            "(" +
            "#{oid},#{createAt},#{updateAt},#{code},#{label}" +
            ")")
    void insertOne(RoadSurface as);

    @Results(id="RoadSurfaceResults",
            value={
                    @Result(column="create_at", property = "createAt"),
                    @Result(column="update_at", property = "updateAt")
            }
    )
    @Select("SELECT * FROM road_surface")
    List<RoadSurface> findAll();

    @Select("select * from road_surface where oid = #{id}")
    RoadSurface findById(@Param("id") Long id);

    @Select("select * from road_surface where code = #{code}")
    RoadSurface findByCode(@Param("code") Integer code);
}
