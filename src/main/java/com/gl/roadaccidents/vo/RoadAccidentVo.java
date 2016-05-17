package com.gl.roadaccidents.vo;

import com.gl.roadaccidents.model.*;

import java.util.Date;

/**
 * Created by gavin on 16-5-15.
 */
public class RoadAccidentVo {
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

    public RoadAccidentVo(RoadAccidentVoBuilder b) {
        accidentIndex = b.getAccidentIndex();
        longitude = b.getLongitude();
        latitude = b.getLatitude();
        policeForce = b.getPoliceForce();
        accidentSeverity = b.getAccidentSeverity();
        numberOfVehicles = b.getNumberOfVehicles();
        numberOfCasualties = b.getNumberOfCasualties();
        occurOn = b.getOccurOn();
        dayOfWeek = b.getDayOfWeek();
        occurAt = b.getOccurAt();
        districtAuthority = b.getDistrictAuthority();
        lightCondition = b.getLightCondition();
        weatherCondition = b.getWeatherCondition();
        roadSurface = b.getRoadSurface();
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

    @Override
    public String toString() {
        return "RoadAccidentVo{" +
                "accidentIndex='" + accidentIndex + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
                ", policeForce='" + policeForce + '\'' +
                ", accidentSeverity='" + accidentSeverity + '\'' +
                ", numberOfVehicles='" + numberOfVehicles + '\'' +
                ", numberOfCasualties='" + numberOfCasualties + '\'' +
                ", occurOn='" + occurOn + '\'' +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", occurAt='" + occurAt + '\'' +
                ", districtAuthority='" + districtAuthority + '\'' +
                ", lightCondition='" + lightCondition + '\'' +
                ", weatherCondition='" + weatherCondition + '\'' +
                ", roadSurface='" + roadSurface + '\'' +
                '}';
    }
}
