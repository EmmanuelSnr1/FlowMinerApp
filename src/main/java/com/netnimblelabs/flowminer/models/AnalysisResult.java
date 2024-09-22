/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.flowminer.models;

/**
 *
 * @author admin
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class AnalysisResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long processFileId;
    private String analysisType; // e.g., "bottleneck", "performance"
    private String activity; // e.g., specific activity analyzed (if applicable)

    @Lob
    private String responseJson; // Store the full JSON response

    // Getters and setters
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

    public String getAnalysisType() {
        return analysisType;
    }

    public void setAnalysisType(String analysisType) {
        this.analysisType = analysisType;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }
}


