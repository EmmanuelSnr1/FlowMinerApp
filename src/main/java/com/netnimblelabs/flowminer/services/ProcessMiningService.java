/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.flowminer.services;

/**
 *
 * @author admin
 */
import com.netnimblelabs.flowminer.services.requests.ProcessDiscoveryRequest;
import com.netnimblelabs.flowminer.services.requests.PerformanceAnalysisRequest;
import com.netnimblelabs.flowminer.services.responses.FrequencyAnalysisResponse;
import com.netnimblelabs.flowminer.services.responses.PerformanceMapAnalysisResponse;
import com.netnimblelabs.flowminer.services.responses.ResourceUtilizationAnalysisResponse;
import com.netnimblelabs.flowminer.services.responses.ProcessDiscoveryResponse;
import com.netnimblelabs.flowminer.services.responses.BottleneckAnalysisResponse;

import com.netnimblelabs.flowminer.services.requests.ProcessOverviewRequest;
import com.netnimblelabs.flowminer.services.responses.DFGDiscoveryResponse;
import com.netnimblelabs.flowminer.services.responses.ProcessOverviewResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProcessMiningService {

    @POST("/api/overview/process_overview")
    Call<ProcessOverviewResponse> getProcessOverview(@Body ProcessOverviewRequest request);

    @POST("/api/process_discovery/inductive")
    Call<ProcessDiscoveryResponse> discoverProcessInductive(@Body ProcessDiscoveryRequest request);

    @POST("/api/process_discovery/alpha")
    Call<ProcessDiscoveryResponse> discoverProcessAlpha(@Body ProcessDiscoveryRequest request);

    @POST("/api/process_discovery/heuristics")
    Call<ProcessDiscoveryResponse> discoverProcessHeuristics(@Body ProcessDiscoveryRequest request);
    
    // New Directly Follows Graph (DFG) Discovery endpoint
    @POST("/api/process_discovery/dfg")
    Call<DFGDiscoveryResponse> discoverProcessDfg(@Body ProcessDiscoveryRequest request);
    
    // New BPMN Inductive Discovery endpoint
    @POST("/api/process_discovery/bpmn/inductive")
    Call<ProcessDiscoveryResponse> discoverBpmnInductive(@Body ProcessDiscoveryRequest request);

    @POST("/api/performance_analysis/bottlenecks")
    Call<BottleneckAnalysisResponse> analyzeBottlenecks(@Body PerformanceAnalysisRequest request);

    @POST("/api/performance_analysis/case_duration")
    Call<PerformanceMapAnalysisResponse> analyzeCaseDuration(@Body PerformanceAnalysisRequest request);

    @POST("/api/performance_analysis/frequency")
    Call<FrequencyAnalysisResponse> analyzeFrequency(@Body PerformanceAnalysisRequest request);

    @POST("/api/performance_analysis/resource_utilization")
    Call<ResourceUtilizationAnalysisResponse> analyzeResourceUtilization(@Body PerformanceAnalysisRequest request);

    // Add other endpoints as needed
}
