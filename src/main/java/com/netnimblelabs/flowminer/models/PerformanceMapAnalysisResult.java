/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.flowminer.models;

/**
 *
 * @author admin
 */

import java.io.Serializable;
import java.util.Map;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Column;
import javax.persistence.Lob;

@Entity
public class PerformanceMapAnalysisResult implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long processFileId;

    @Lob
    private Map<String, Double> performanceMap;
    
    @Lob
    private String responseJson;
    
    
    // Getters and Setters
    // ...

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

    public Map<String, Double> getPerformanceMap() {
        return performanceMap;
    }

    public void setPerformanceMap(Map<String, Double> performanceMap) {
        this.performanceMap = performanceMap;
    }

    public String getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }
}

