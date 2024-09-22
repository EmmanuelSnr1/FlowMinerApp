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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class BottleneckAnalysisResult implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Long processFileId;
    @Lob
    private String bottlenecks;
    @Lob
    private String responseJson;
    
    // Getters and Setters
    // ... (same as before)

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

    public String getBottlenecks() {
        return bottlenecks;
    }

    public void setBottlenecks(String bottlenecks) {
        this.bottlenecks = bottlenecks;
    }

    public String getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }
}

