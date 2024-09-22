/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.flowminer.services.responses;

/**
 *
 * @author admin
 */
// ProcessDiscoveryResponse.java

import com.google.gson.annotations.SerializedName;

public class ProcessDiscoveryResponse {
    @SerializedName("net")
    private String net;

    @SerializedName("initial_marking")
    private String initialMarking;

    @SerializedName("final_marking")
    private String finalMarking;

    // Getters and setters
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
}
