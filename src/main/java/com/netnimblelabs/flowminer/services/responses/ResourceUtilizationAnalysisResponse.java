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

public class ResourceUtilizationAnalysisResponse {
    
    @SerializedName("utilization_map")
    private Map<String, Double> utilizationMap; // Changed from Integer to Double

    // Getter and Setter

    public Map<String, Double> getUtilizationMap() {
        return utilizationMap;
    }

    public void setUtilizationMap(Map<String, Double> utilizationMap) {
        this.utilizationMap = utilizationMap;
    }
}
