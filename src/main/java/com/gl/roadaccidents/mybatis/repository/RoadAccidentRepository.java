package com.gl.roadaccidents.mybatis.repository;

import com.gl.roadaccidents.model.RoadAccident;

import java.util.List;

/**
 * Created by gavin on 16-5-18.
 */
public interface RoadAccidentRepository {
    void insertOne(RoadAccident roadAccident);

    void insertInBatch(List<RoadAccident> roadAccidents);

    RoadAccident findById(Long id);

    List<RoadAccident> findAll();

}
