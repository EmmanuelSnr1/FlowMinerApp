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
public class ProcessDiscoveryResult implements Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long processFileId;
    private String method; // e.g., "inductive", "alpha", "heuristics"

    @Lob
    private String net;
    @Lob
    private String initialMarking;
    @Lob
    private String finalMarking;

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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getInitialMarking() {
        return initialMarking;
    }

    public void setInitialMarking(String initialMarking) {
        this.initialMarking = initialMarking;
    }

    public String getFinalMarking() {
        return finalMarking;
    }

    public void setFinalMarking(String finalMarking) {
        this.finalMarking = finalMarking;
    }

    public String getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }
    @Lob
    private String dfgEdgesJson;

    // Getter and setter

    public String getDfgEdgesJson() {
        return dfgEdgesJson;
    }

    public void setDfgEdgesJson(String dfgEdgesJson) {
        this.dfgEdgesJson = dfgEdgesJson;
    }
}


