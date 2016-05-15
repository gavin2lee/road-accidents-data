package com.gl.roadaccidents.vo;

/**
 * Created by gavin on 16-5-15.
 */
public class RoadAccidentVoBuilder {
    private String accidentIndex;
    private String longitude;
    private String latitude;
    private String policeForce;
    private String accidentSeverity;
    private String numberOfVehicles;
    private String numberOfCasualties;
    private String occurOn;
    private String dayOfWeek;
    private String occurAt;
    private String districtAuthority;
    private String lightCondition;
    private String weatherCondition;
    private String roadSurface;



    public RoadAccidentVoBuilder(String accidentIndex) {
        this.accidentIndex = accidentIndex;
    }

    public RoadAccidentVoBuilder setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public RoadAccidentVoBuilder setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public RoadAccidentVoBuilder setPoliceForce(String policeForce) {
        this.policeForce = policeForce;
        return this;
    }

    public RoadAccidentVoBuilder setAccidentSeverity(String accidentSeverity) {
        this.accidentSeverity = accidentSeverity;
        return this;
    }

    public RoadAccidentVoBuilder setNumberOfVehicles(String numberOfVehicles) {
        this.numberOfVehicles = numberOfVehicles;
        return this;
    }

    public RoadAccidentVoBuilder setNumberOfCasualties(String numberOfCasualties) {
        this.numberOfCasualties = numberOfCasualties;
        return this;
    }

    public RoadAccidentVoBuilder setOccurOn(String occurOn) {
        this.occurOn = occurOn;
        return this;
    }

    public RoadAccidentVoBuilder setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
        return this;
    }

    public RoadAccidentVoBuilder setOccurAt(String occurAt) {
        this.occurAt = occurAt;
        return this;
    }

    public RoadAccidentVoBuilder setDistrictAuthority(String districtAuthority) {
        this.districtAuthority = districtAuthority;
        return this;
    }

    public RoadAccidentVoBuilder setLightCondition(String lightCondition) {
        this.lightCondition = lightCondition;
        return this;
    }

    public RoadAccidentVoBuilder setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
        return this;
    }

    public RoadAccidentVoBuilder setRoadSurface(String roadSurface) {
        this.roadSurface = roadSurface;
        return this;
    }

    public String getAccidentIndex() {
        return accidentIndex;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getPoliceForce() {
        return policeForce;
    }

    public String getAccidentSeverity() {
        return accidentSeverity;
    }

    public String getNumberOfVehicles() {
        return numberOfVehicles;
    }

    public String getNumberOfCasualties() {
        return numberOfCasualties;
    }

    public String getOccurOn() {
        return occurOn;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getOccurAt() {
        return occurAt;
    }

    public String getDistrictAuthority() {
        return districtAuthority;
    }

    public String getLightCondition() {
        return lightCondition;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public String getRoadSurface() {
        return roadSurface;
    }

    public RoadAccidentVo build(){
        return new RoadAccidentVo(this);
    }
}
