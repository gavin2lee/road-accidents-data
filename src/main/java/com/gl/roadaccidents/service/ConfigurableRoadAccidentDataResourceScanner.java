package com.gl.roadaccidents.service;

import org.springframework.core.io.Resource;

import java.util.List;

/**
 * Created by gavin on 16-5-15.
 */
public class ConfigurableRoadAccidentDataResourceScanner implements RoadAccidentDataResourceScanner{
    private Resource accidentSeverity;
    private Resource weatherCondition;
    private Resource roadSurface;
    private Resource lightCondition;
    private Resource policeForce;
    private Resource districtAuthority;

    private List<Resource> roadAccidents;

    public void setRoadAccidents(List<Resource> roadAccidents) {
        this.roadAccidents = roadAccidents;
    }

    public void setAccidentSeverity(Resource accidentSeverity) {
        this.accidentSeverity = accidentSeverity;
    }

    public void setWeatherCondition(Resource weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public void setRoadSurface(Resource roadSurface) {
        this.roadSurface = roadSurface;
    }

    public void setLightCondition(Resource lightCondition) {
        this.lightCondition = lightCondition;
    }

    public void setPoliceForce(Resource policeForce) {
        this.policeForce = policeForce;
    }

    public void setDistrictAuthority(Resource districtAuthority) {
        this.districtAuthority = districtAuthority;
    }

    @Override
    public List<Resource> findRoadAccidentDataResources() {
        return roadAccidents;
    }

    @Override
    public Resource findAccidentSeverityDataResource() {
        return accidentSeverity;
    }

    @Override
    public Resource findDistrictAuthorityDataResource() {
        return districtAuthority;
    }

    @Override
    public Resource findLightConditionDataResource() {
        return lightCondition;
    }

    @Override
    public Resource findPoliceForceDataResource() {
        return policeForce;
    }

    @Override
    public Resource findRoadSurfaceDataResource() {
        return roadSurface;
    }

    @Override
    public Resource findWeatherConditionDataResource() {
        return weatherCondition;
    }
}
