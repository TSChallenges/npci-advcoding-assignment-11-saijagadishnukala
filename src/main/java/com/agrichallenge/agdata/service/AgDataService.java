package com.agrichallenge.agdata.service;

import com.agrichallenge.agdata.model.AgData;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

// Service class for analyzing agricultural data
// TODO: Implement this service class according to the requirements.

@Service
public class AgDataService {

    private final List<AgData> agDataList;

    public AgDataService() throws IOException {
        this.agDataList = loadAgData();
    }

    public List<AgData> loadAgData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        // Load the data from JSON file from resources
        InputStream inputStream = getClass().getResourceAsStream("/data/agdata.json");
        // Map the JSON to a List of AgData objects
        return objectMapper.readValue(inputStream, new TypeReference<List<AgData>>() {});
    }

    public Long getCropCount(String cropName) {
        // TODO: Implement this method to Count how many times a specific crop appears in the dataset
        Long res = agDataList.stream().filter(crop -> crop.getCrop().equalsIgnoreCase(cropName)).count();
        return res;
    }

    public double getAverageYield(String cropName) {
        // TODO: Implement this method to Calculate the average yield for a specific crop if it exists, else return 0.0

        double totalYeild = agDataList.stream()
                .filter(crop -> crop.getCrop().equalsIgnoreCase(cropName))
                .mapToDouble(AgData::getYield)
                .sum();

        double count =  agDataList.stream()
                .filter(crop -> crop.getCrop().equalsIgnoreCase(cropName))
                .count();

        return totalYeild/count;
    }

    public List<AgData> getRecordsByRegion(String region) {
        // TODO: Implement this method to Get all records from a specific region

        return agDataList.stream().filter(ag -> ag.getRegion().equalsIgnoreCase(region)).toList();
    }

}

