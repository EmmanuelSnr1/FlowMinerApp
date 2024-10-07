package com.netnimblelabs.flowminer.models;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

@Entity
public class PerformanceMapAnalysisResult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long processFileId;

    @Lob
    private String performanceMapJson;

    @Lob
    private String responseJson;

    @Transient
    private Map<String, Double> performanceMap;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProcessFileId() {
        return processFileId;
    }

    public void setProcessFileId(Long processFileId) {
        this.processFileId = processFileId;
    }

    public String getPerformanceMapJson() {
        return performanceMapJson;
    }

    public void setPerformanceMapJson(String performanceMapJson) {
        this.performanceMapJson = performanceMapJson;
        this.performanceMap = deserializePerformanceMap(performanceMapJson);  // Deserialize JSON to Map
    }

    public String getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }

    public Map<String, Double> getPerformanceMap() {
        if (performanceMap == null && performanceMapJson != null) {
            this.performanceMap = deserializePerformanceMap(performanceMapJson);  // Deserialize on first access
        }
        return performanceMap;
    }

    public void setPerformanceMap(Map<String, Double> performanceMap) {
        this.performanceMap = performanceMap;
        this.performanceMapJson = serializePerformanceMap(performanceMap);  // Serialize Map to JSON
    }

    // Helper methods for serialization and deserialization using Gson
    private String serializePerformanceMap(Map<String, Double> performanceMap) {
        return new Gson().toJson(performanceMap);  // Convert Map to JSON string
    }

    private Map<String, Double> deserializePerformanceMap(String performanceMapJson) {
        return new Gson().fromJson(performanceMapJson, Map.class);  // Convert JSON string back to Map
    }
}
