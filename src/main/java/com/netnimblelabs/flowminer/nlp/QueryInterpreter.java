/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.flowminer.nlp;

import com.netnimblelabs.flowminer.services.PerformanceAnalysisService;
import com.netnimblelabs.flowminer.services.ProcessDiscoveryService;
import edu.stanford.nlp.pipeline.CoreDocument;

/**
 *
 * @author admin
 */

public class QueryInterpreter {
    private ProcessDiscoveryService discoveryService;
    private PerformanceAnalysisService performanceService;

    public QueryInterpreter(ProcessDiscoveryService discoveryService, PerformanceAnalysisService performanceService) {
        this.discoveryService = discoveryService;
        this.performanceService = performanceService;
    }

    /**
     * Executes actions based on the query type.
     *
     * @param queryType The classified query type.
     * @param processFileId The ID of the process file.
     * @throws Exception if an error occurs during execution or if the query type is unsupported.
     */
    public void interpretAndExecute(String queryType, Long processFileId) throws Exception {
        switch (queryType) {
            // Process Discovery Actions
            case "process discovery - alpha":
                discoveryService.discoverProcessAndSaveAlpha(processFileId);
                break;
            case "process discovery - inductive":
                discoveryService.discoverProcessAndSaveInductive(processFileId);
                break;
            case "process discovery - heuristics":
                discoveryService.discoverProcessAndSaveHeuristics(processFileId);
                break;
            case "process discovery - bpmn inductive":
                discoveryService.discoverProcessAndSaveBpmnInductive(processFileId);
                break;
            case "process discovery - dfg":
                discoveryService.discoverProcessAndSaveDfg(processFileId);
                break;
//            case "process discovery":
//                discoveryService.discoverProcessAndSaveDefault(processFileId); // Implement default discovery
//                break;

            // Performance Analysis Actions
            case "performance analysis - bottleneck":
                performanceService.analyzeBottlenecksAndSave(processFileId);
                break;
            case "performance analysis - case duration":
                performanceService.analyzeCaseDurationAndSave(processFileId);
                break;
            case "performance analysis - frequency":
                performanceService.analyzeFrequencyAndSave(processFileId);
                break;
            case "performance analysis - resource utilization":
                performanceService.analyzeResourceUtilizationAndSave(processFileId);
                break;
//            case "performance analysis":
//                performanceService.analyzeDefaultPerformance(processFileId); // Implement default analysis
//                break;

            // Process Overview Action
            case "process overview":
                discoveryService.getProcessOverviewAndSave(processFileId);
                break;

            // Add more cases as needed for other query types

            default:
                throw new UnsupportedOperationException("Unsupported query type: " + queryType);
        }
    }
}
