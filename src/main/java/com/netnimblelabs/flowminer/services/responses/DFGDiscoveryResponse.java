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
import java.util.List;

public class DFGDiscoveryResponse {

    @SerializedName("directly_follows_graph")
    private DirectlyFollowsGraph directlyFollowsGraph;

    // Getters and setters

    public DirectlyFollowsGraph getDirectlyFollowsGraph() {
        return directlyFollowsGraph;
    }

    public void setDirectlyFollowsGraph(DirectlyFollowsGraph directlyFollowsGraph) {
        this.directlyFollowsGraph = directlyFollowsGraph;
    }

    // Inner class for DirectlyFollowsGraph
    public static class DirectlyFollowsGraph {
        @SerializedName("edges")
        private List<Edge> edges;

        // Getters and setters

        public List<Edge> getEdges() {
            return edges;
        }

        public void setEdges(List<Edge> edges) {
            this.edges = edges;
        }
    }

    // Inner class for Edge
    public static class Edge {
        @SerializedName("frequency")
        private int frequency;

        @SerializedName("source")
        private String source;

        @SerializedName("target")
        private String target;

        // Getters and setters

        public int getFrequency() {
            return frequency;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }
    }
}

