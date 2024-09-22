/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.flowminer.services.responses;

/**
 *
 * @author admin
 */

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class PerformanceMapAnalysisResponse {

    @SerializedName("performance_map")
    private Map<String, Double> performanceMap;

    public Map<String, Double> getPerformanceMap() {
        return performanceMap;
    }

    public void setPerformanceMap(Map<String, Double> performanceMap) {
        this.performanceMap = performanceMap;
    }
}

