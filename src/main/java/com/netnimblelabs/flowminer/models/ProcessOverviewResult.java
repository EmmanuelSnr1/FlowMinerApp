package com.netnimblelabs.flowminer.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class ProcessOverviewResult implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long processFileId;
    private int numEvents;
    private int numCases;
    private int numActivities;
    private double averageThroughputTime;
    private int numVariants;
    
    @Lob
    private String startActivities;  // Store JSON string of start activities
    @Lob
    private String endActivities;    // Store JSON string of end activities
    @Lob
    private String topVariants;      // Store JSON string of the top variants
    @Lob
    private String responseJson;     // Store the full JSON response

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

    public int getNumEvents() {
        return numEvents;
    }

    public void setNumEvents(int numEvents) {
        this.numEvents = numEvents;
    }

    public int getNumCases() {
        return numCases;
    }

    public void setNumCases(int numCases) {
        this.numCases = numCases;
    }

    public int getNumActivities() {
        return numActivities;
    }

    public void setNumActivities(int numActivities) {
        this.numActivities = numActivities;
    }

    public double getAverageThroughputTime() {
        return averageThroughputTime;
    }

    public void setAverageThroughputTime(double averageThroughputTime) {
        this.averageThroughputTime = averageThroughputTime;
    }

    public int getNumVariants() {
        return numVariants;
    }

    public void setNumVariants(int numVariants) {
        this.numVariants = numVariants;
    }

    public String getStartActivities() {
        return startActivities;
    }

    public void setStartActivities(String startActivities) {
        this.startActivities = startActivities;
    }

    public String getEndActivities() {
        return endActivities;
    }

    public void setEndActivities(String endActivities) {
        this.endActivities = endActivities;
    }

    public String getTopVariants() {
        return topVariants;
    }

    public void setTopVariants(String topVariants) {
        this.topVariants = topVariants;
    }

    public String getResponseJson() {
        return responseJson;
    }

    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }
}
