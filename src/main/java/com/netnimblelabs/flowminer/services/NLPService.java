/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.flowminer.services;
/**
 *
 * @author admin
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



import com.netnimblelabs.flowminer.nlp.NLPProcessor;
import com.netnimblelabs.flowminer.nlp.QueryInterpreter;
import com.netnimblelabs.flowminer.services.PerformanceAnalysisService;
import com.netnimblelabs.flowminer.services.ProcessDiscoveryService;
import com.netnimblelabs.flowminer.services.ProcessMiningService;
import com.netnimblelabs.flowminer.services.RetrofitClient;
import edu.stanford.nlp.pipeline.CoreDocument;
import retrofit2.Retrofit;

/**
 *
 * @author admin
 */

public class NLPService {

    private NLPProcessor nlpProcessor;
    private QueryInterpreter queryInterpreter;

    public NLPService(ProcessDiscoveryService discoveryService, PerformanceAnalysisService performanceService) {
        this.nlpProcessor = new NLPProcessor();
        this.queryInterpreter = new QueryInterpreter(discoveryService, performanceService);
    }

    /**
     * Handles an input query string, processes it using NLP, and interprets it
     * as a process mining action.
     *
     * @param query The natural language query input.
     * @param processFileId The ID of the process file to operate on.
     * @throws Exception if an error occurs during processing or if the query is unrecognized.
     */
    public void handleQuery(String query, Long processFileId) throws Exception {
        // Process the text with NLP
        CoreDocument document = nlpProcessor.processText(query);
        
        // Interpret the processed document and execute the corresponding action
        queryInterpreter.interpretAndExecute(document, processFileId);
    }

    /**
     * Main method for testing the NLPService.
     */
    public static void main(String[] args) {
        // Use RetrofitClient to get the Retrofit instance
        String baseUrl = "http://127.0.0.1:5000/";
        Retrofit retrofit = RetrofitClient.getClient(baseUrl);

        // Create the ProcessMiningService instance using Retrofit
        ProcessMiningService processMiningService = retrofit.create(ProcessMiningService.class);

        // Initialize the services
        ProcessDiscoveryService discoveryService = new ProcessDiscoveryService(processMiningService);
        PerformanceAnalysisService performanceService = new PerformanceAnalysisService(processMiningService);

        // Initialize the NLPService with the actual services
        NLPService nlpService = new NLPService(discoveryService, performanceService);

        // Test query handling
        try {
//            String query = "Please discover the process using alpha method.";
            Long processFileId = 1L; // Example process file ID
//            nlpService.handleQuery(query, processFileId);
//            
//            query = "Can you analyze the bottleneck?";
//            nlpService.handleQuery(query, processFileId);
//          
            String query;
            query = "I'd like to analyze the case duration.";
            nlpService.handleQuery(query, processFileId);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


