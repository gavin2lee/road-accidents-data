package com.gl.roadaccidents.util.loader;

import com.gl.roadaccidents.model.*;
import com.gl.roadaccidents.service.DataLoadService;
import com.gl.roadaccidents.service.RoadAccidentDataResourceScanner;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.concurrent.Callable;

/**
 * Created by gavin on 16-5-14.
 */
public class RoadAccidentDataLoader {
    private static final Logger log = LoggerFactory.getLogger(RoadAccidentDataLoader.class);

    @Autowired
    private DataLoadService dataLoadService;

    @Autowired
    @Qualifier("dataSourceScanner")
    private RoadAccidentDataResourceScanner scanner;

    public void load(){
        loadStaticData();
        loadRoadAccidentData();
    }

    protected void loadStaticData(){
        dataLoadService.clearStaticData();
        doLoadStaticData();
    }

    protected void doLoadStaticData(){
        loadWeatherCondition();
        loadAccidentSeverity();
        loadLightCondition();
        loadPoliceForce();
        loadRoadSurface();
        loadDistrictAuthority();
    }

    private void loadDistrictAuthority(){
        Resource resource = scanner.findDistrictAuthorityDataResource();
        Reader reader = null;
        try {
            reader = new InputStreamReader(resource.getInputStream());
            Iterable<CSVRecord> records = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
            for (CSVRecord record : records) {
                Integer code = Integer.valueOf(record.get(0));
                String label = record.get(1);

                DistrictAuthority entity = new DistrictAuthority();
                entity.setCode(code);
                entity.setLabel(label);


                dataLoadService.addDistrictAuthority(entity);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private void loadRoadSurface(){
        Resource resource = scanner.findRoadSurfaceDataResource();
        Reader reader = null;
        try {
            reader = new InputStreamReader(resource.getInputStream());
            Iterable<CSVRecord> records = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
            for (CSVRecord record : records) {
                Integer code = Integer.valueOf(record.get(0));
                String label = record.get(1);

                RoadSurface entity = new RoadSurface();
                entity.setCode(code);
                entity.setLabel(label);


                dataLoadService.addRoadSurface(entity);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private void loadPoliceForce(){
        Resource resource = scanner.findPoliceForceDataResource();
        Reader reader = null;
        try {
            reader = new InputStreamReader(resource.getInputStream());
            Iterable<CSVRecord> records = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
            for (CSVRecord record : records) {
                Integer code = Integer.valueOf(record.get(0));
                String label = record.get(1);

                PoliceForce entity = new PoliceForce();
                entity.setCode(code);
                entity.setLabel(label);


                dataLoadService.addPoliceForce(entity);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private void loadLightCondition(){
        Resource resource = scanner.findLightConditionDataResource();
        Reader reader = null;
        try {
            reader = new InputStreamReader(resource.getInputStream());
            Iterable<CSVRecord> records = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
            for (CSVRecord record : records) {
                Integer code = Integer.valueOf(record.get(0));
                String label = record.get(1);

                LightCondition entity = new LightCondition();
                entity.setCode(code);
                entity.setLabel(label);


                dataLoadService.addLightCondition(entity);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private void loadAccidentSeverity(){
        Resource resource = scanner.findAccidentSeverityDataResource();
        Reader reader = null;
        try {
            reader = new InputStreamReader(resource.getInputStream());
            Iterable<CSVRecord> records = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
            for (CSVRecord record : records) {
                Integer code = Integer.valueOf(record.get(0));
                String label = record.get(1);

                AccidentSeverity entity = new AccidentSeverity();
                entity.setCode(code);
                entity.setLabel(label);


                dataLoadService.addAccidentSeverity(entity);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }

    private void loadWeatherCondition(){
        Resource resource = scanner.findWeatherConditionDataResource();
        Reader reader = null;
        try {
            reader = new InputStreamReader(resource.getInputStream());
            Iterable<CSVRecord> records = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
            for (CSVRecord record : records) {
                Integer code = Integer.valueOf(record.get(0));
                String label = record.get(1);

                WeatherCondition entity = new WeatherCondition();
                entity.setCode(code);
                entity.setLabel(label);

                entity.setCreateAt(new Date());

                dataLoadService.addWeatherCondition(entity);

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }

    protected void loadRoadAccidentData(){
        //TODO
    }

    public static class RoadAccidentCvsResourceReader implements Callable<Long>{

        @Override
        public Long call() throws Exception {
            return null;
        }
    }

    public static class RoadAccidentDataPutter implements Callable<Long>{

        @Override
        public Long call() throws Exception {
            return null;
        }
    }
}
