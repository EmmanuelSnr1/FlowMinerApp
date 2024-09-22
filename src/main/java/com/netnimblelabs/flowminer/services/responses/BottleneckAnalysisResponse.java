/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.flowminer.services.responses;

/**
 *
 * @author admin
 */
// BottleneckAnalysisResponse.java

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class BottleneckAnalysisResponse {

    @SerializedName("bottlenecks")
    private Map<String, Double> bottlenecks;

    @SerializedName("response_json")
    private String responseJson;

    // Getters and setters
    public Map<String, Double> getBottlenecks() {
        return bottlenecks;
    }

    public void setBottlenecks(Map<String, Double> bottlenecks) {
        this.bottlenecks = bottlenecks;
    }

    public String getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }
}

