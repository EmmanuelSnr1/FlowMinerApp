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
    private Map<String, Integer> utilizationMap;

    public Map<String, Integer> getUtilizationMap() {
        return utilizationMap;
    }

    public void setUtilizationMap(Map<String, Integer> utilizationMap) {
        this.utilizationMap = utilizationMap;
    }
}

