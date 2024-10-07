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
public class ResourceUtilizationAnalysisResult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long processFileId;

    // This will store the serialized JSON string in the database
    @Lob
    private String utilizationMapJson;

    @Lob
    private String responseJson;

    // This is a transient field to hold the deserialized map for use in business logic
    @Transient
    private Map<String, Double> utilizationMap;

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

    public String getUtilizationMapJson() {
        return utilizationMapJson;
    }

    public void setUtilizationMapJson(String utilizationMapJson) {
        this.utilizationMapJson = utilizationMapJson;
        this.utilizationMap = deserializeUtilizationMap(utilizationMapJson);  // Deserialize JSON to Map
    }

    public String getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }

    public Map<String, Double> getUtilizationMap() {
        if (utilizationMap == null && utilizationMapJson != null) {
            this.utilizationMap = deserializeUtilizationMap(utilizationMapJson);  // Deserialize on first access
        }
        return utilizationMap;
    }

    public void setUtilizationMap(Map<String, Double> utilizationMap) {
        this.utilizationMap = utilizationMap;
        this.utilizationMapJson = serializeUtilizationMap(utilizationMap);  // Serialize Map to JSON
    }

    // Helper methods for serialization and deserialization using Gson
    private String serializeUtilizationMap(Map<String, Double> utilizationMap) {
        return new Gson().toJson(utilizationMap);  // Convert Map to JSON string
    }

    private Map<String, Double> deserializeUtilizationMap(String utilizationMapJson) {
        return new Gson().fromJson(utilizationMapJson, Map.class);  // Convert JSON string back to Map
    }
}
