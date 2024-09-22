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

public class FrequencyAnalysisResponse {

    @SerializedName("frequency_map")
    private Map<String, Integer> frequencyMap;

    public Map<String, Integer> getFrequencyMap() {
        return frequencyMap;
    }

    public void setFrequencyMap(Map<String, Integer> frequencyMap) {
        this.frequencyMap = frequencyMap;
    }
}

