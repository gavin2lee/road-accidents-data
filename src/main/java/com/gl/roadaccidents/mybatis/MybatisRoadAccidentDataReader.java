package com.gl.roadaccidents.mybatis;

import com.gl.roadaccidents.load.DataReader;
import com.gl.roadaccidents.vo.RoadAccidentVo;
import com.gl.roadaccidents.vo.RoadAccidentVoBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by gavin on 16-5-19.
 */
public class MybatisRoadAccidentDataReader implements DataReader, Callable<Long>{


    @Override
    public Long read() throws Exception {
        long amountOfRecords = 0L;


        for (Resource r : resourcesToRead) {
            amountOfRecords += processOneResource(r);
        }


        return amountOfRecords;
    }

    @Override
    public Long call() throws Exception {
        log.info("START at '{}'", new Date().toString());

        Long numberOfReadRecords =  read();

        log.info("END and read {} in total records at '{}'.", numberOfReadRecords, new Date().toString());

        return numberOfReadRecords;
    }

    private Long processOneResource(Resource resource) {
        long result = 0L;
        int sizeToLog = Integer.valueOf(System.getProperty(KEY_SIZE_TO_LOG_READ, "1000"));
        boolean midStop = "true".equalsIgnoreCase(System.getProperty("midStop"));
        Reader reader = null;
        try {
            reader = new InputStreamReader(resource.getInputStream());
            Iterable<CSVRecord> records = new CSVParser(reader, CSVFormat.EXCEL.withHeader());
            for (CSVRecord record : records) {
                RoadAccidentVo vo = parseOneRecord(record);
                this.toStore.offer(vo, 10, TimeUnit.SECONDS);

                result++;
                if (result % sizeToLog == 0) {
                    log.debug("Read {} from {}", result, resource.getURI().toString());
                }

                if (midStop && (result >= 3000)) {
                    break;
                }
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
        return result;
    }

    private RoadAccidentVo parseOneRecord(CSVRecord record) {
        String accidentId = record.get("Accident_Index");
        RoadAccidentVo vo = new RoadAccidentVoBuilder(accidentId)
                .setLongitude(record.get("Longitude"))
                .setLatitude(record.get("Latitude"))
                .setPoliceForce(record.get("Police_Force"))
                .setAccidentSeverity(record.get("Accident_Severity"))
                .setNumberOfVehicles(record.get("Number_of_Vehicles"))
                .setNumberOfCasualties(record.get("Number_of_Casualties"))
                .setOccurOn(record.get("Date"))
                .setDayOfWeek(record.get("Day_of_Week"))
                .setOccurAt(record.get("Time"))
                .setDistrictAuthority(record.get("Local_Authority_(District)"))
                .setLightCondition(record.get("Light_Conditions"))
                .setWeatherCondition(record.get("Weather_Conditions"))
                .setRoadSurface(record.get("Road_Surface_Conditions"))
                .build();
        return vo;
    }

    public MybatisRoadAccidentDataReader(BlockingQueue<RoadAccidentVo> toStore, List<Resource> resourcesToRead) {
        this.toStore = toStore;
        this.resourcesToRead = resourcesToRead;
    }

    public static final String KEY_SIZE_TO_LOG_READ = "sizeToLogRead";

    private static final Logger log = LoggerFactory.getLogger(MybatisRoadAccidentDataReader.class);
    private BlockingQueue<RoadAccidentVo> toStore;
    private List<Resource> resourcesToRead;
}
