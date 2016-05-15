package com.gl.roadaccidents.repository;

import com.gl.roadaccidents.model.RoadAccident;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.NamedNativeQuery;

/**
 * Created by gavin on 16-5-13.
 */
public interface RoadAccidentRepository extends CrudRepository<RoadAccident,Long>{

}
