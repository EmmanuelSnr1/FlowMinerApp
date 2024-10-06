/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.flowminer.services.requests;

/**
 *
 * @author admin
 */


public class ProcessOverviewRequest {
    private String xes_path;  // Update field name to match Flask

    public ProcessOverviewRequest(String xes_path) {
        this.xes_path = xes_path;
    }

    public String getXes_path() {
        return xes_path;
    }

    public void setXes_path(String xes_path) {
        this.xes_path = xes_path;
    }
}
