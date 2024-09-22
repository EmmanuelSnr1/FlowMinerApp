/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.flowminer.models;

/**
 *
 * @author admin
 */

public class NLPQueryStatus {
    
    private String statusId;
    private String status;
    private String message;

    public NLPQueryStatus(String statusId, String status, String message) {
        this.statusId = statusId;
        this.status = status;
        this.message = message;
    }

    // Getters and setters
    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

