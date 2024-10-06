package com.netnimblelabs.flowminer.services.responses;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class ProcessOverviewResponse {
    @SerializedName("num_events")
    private int numEvents;

    @SerializedName("num_cases")
    private int numCases;

    @SerializedName("num_activities")
    private int numActivities;

    @SerializedName("average_throughput_time")
    private double averageThroughputTime;

    @SerializedName("num_variants")
    private int numVariants;

    @SerializedName("start_activities")
    private Map<String, Integer> startActivities;

    @SerializedName("end_activities")
    private Map<String, Integer> endActivities;

    @SerializedName("top_variants")
    private TopVariant[] topVariants;

    public static class TopVariant {
        @SerializedName("variant")
        private String[] variant;

        @SerializedName("num_cases")
        private int numCases;

        public String[] getVariant() {
            return variant;
        }

        public void setVariant(String[] variant) {
            this.variant = variant;
        }

        public int getNumCases() {
            return numCases;
        }

        public void setNumCases(int numCases) {
            this.numCases = numCases;
        }
    }

    // Getters and setters for all fields

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

    public Map<String, Integer> getStartActivities() {
        return startActivities;
    }

    public void setStartActivities(Map<String, Integer> startActivities) {
        this.startActivities = startActivities;
    }

    public Map<String, Integer> getEndActivities() {
        return endActivities;
    }

    public void setEndActivities(Map<String, Integer> endActivities) {
        this.endActivities = endActivities;
    }

    public TopVariant[] getTopVariants() {
        return topVariants;
    }

    public void setTopVariants(TopVariant[] topVariants) {
        this.topVariants = topVariants;
    }
}
