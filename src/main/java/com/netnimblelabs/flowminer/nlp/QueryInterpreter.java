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

    public void interpretAndExecute(CoreDocument document, Long processFileId) throws Exception {
        String lemmatizedText = new NLPProcessor().getLemmatizedText(document);

        // Process discovery queries
        if (lemmatizedText.contains("discover") && lemmatizedText.contains("inductive")) {
            discoveryService.discoverProcessAndSaveInductive(processFileId);
        } else if (lemmatizedText.contains("discover") && lemmatizedText.contains("alpha")) {
            discoveryService.discoverProcessAndSaveAlpha(processFileId);
        } else if (lemmatizedText.contains("discover") && lemmatizedText.contains("heuristics")) {
            discoveryService.discoverProcessAndSaveHeuristics(processFileId);
        }
        // Performance analysis queries
        else if (lemmatizedText.contains("analyze") && lemmatizedText.contains("bottleneck")) {
            performanceService.analyzeBottlenecksAndSave(processFileId);
        } else if (lemmatizedText.contains("analyze") && (lemmatizedText.contains("case") || lemmatizedText.contains("duration"))) {
            performanceService.analyzeCaseDurationAndSave(processFileId);
        } else if (lemmatizedText.contains("analyze") && lemmatizedText.contains("frequency")) {
            performanceService.analyzeFrequencyAndSave(processFileId);
        } else if (lemmatizedText.contains("analyze") && lemmatizedText.contains("resource") && lemmatizedText.contains("utilization")) {
            performanceService.analyzeResourceUtilizationAndSave(processFileId);
        }
        // Add more conditions for other types of analysis or queries as needed
        else {
            throw new UnsupportedOperationException("Query not recognized: " + lemmatizedText);
        }
    }
}


