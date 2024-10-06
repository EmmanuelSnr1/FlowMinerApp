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
import java.util.HashMap;
import java.util.Map;
import retrofit2.Retrofit;

/**
 *
 * @author admin
 */
import com.netnimblelabs.flowminer.nlp.NLPProcessor;
import com.netnimblelabs.flowminer.nlp.QueryInterpreter;
import com.netnimblelabs.flowminer.services.PerformanceAnalysisService;
import com.netnimblelabs.flowminer.services.ProcessDiscoveryService;
import edu.stanford.nlp.ling.CoreLabel;
import java.util.*;

public class NLPService {

    private NLPProcessor nlpProcessor;
    private QueryInterpreter queryInterpreter;
    private DatabaseService databaseService;
    private Map<String, IntentDefinition> intentDefinitions;

    public NLPService(ProcessDiscoveryService discoveryService, PerformanceAnalysisService performanceService, DatabaseService databaseService) {
        this.nlpProcessor = new NLPProcessor();
        this.queryInterpreter = new QueryInterpreter(discoveryService, performanceService);
        this.databaseService = databaseService;
        this.initializeIntentDefinitions();
    }

    /**
     * Initializes the intent definitions with required and optional keywords.
     */
    private void initializeIntentDefinitions() {
        intentDefinitions = new LinkedHashMap<>();

        // Process Discovery Intents (Most Specific to General)
        intentDefinitions.put("process discovery - alpha", new IntentDefinition(
                new HashSet<>(Arrays.asList("discover")),
                new HashSet<>(Arrays.asList("alpha"))
        ));
        intentDefinitions.put("process discovery - inductive", new IntentDefinition(
                new HashSet<>(Arrays.asList("discover")),
                new HashSet<>(Arrays.asList("inductive"))
        ));
        intentDefinitions.put("process discovery - heuristics", new IntentDefinition(
                new HashSet<>(Arrays.asList("discover")),
                new HashSet<>(Arrays.asList("heuristics"))
        ));
        intentDefinitions.put("process discovery - bpmn inductive", new IntentDefinition(
                new HashSet<>(Arrays.asList("discover", "bpmn")),
                new HashSet<>(Arrays.asList("inductive"))
        ));
        intentDefinitions.put("process discovery - dfg", new IntentDefinition(
                new HashSet<>(Arrays.asList("discover")),
                new HashSet<>(Arrays.asList("dfg"))
        ));
        // General intent should be last
        intentDefinitions.put("process discovery", new IntentDefinition(
                new HashSet<>(Arrays.asList("discover")),
                new HashSet<>()
        ));

        // Performance Analysis Intents (Most Specific to General)
        intentDefinitions.put("performance analysis - bottleneck", new IntentDefinition(
                new HashSet<>(Arrays.asList("analyze")),
                new HashSet<>(Arrays.asList("bottleneck"))
        ));
        intentDefinitions.put("performance analysis - case duration", new IntentDefinition(
                new HashSet<>(Arrays.asList("analyze")),
                new HashSet<>(Arrays.asList("case", "duration"))
        ));
        intentDefinitions.put("performance analysis - frequency", new IntentDefinition(
                new HashSet<>(Arrays.asList("analyze")),
                new HashSet<>(Arrays.asList("frequency"))
        ));
        intentDefinitions.put("performance analysis - resource utilization", new IntentDefinition(
                new HashSet<>(Arrays.asList("analyze", "resource")),
                new HashSet<>(Arrays.asList("utilization"))
        ));
        // General intent should be last
        intentDefinitions.put("performance analysis", new IntentDefinition(
                new HashSet<>(Arrays.asList("analyze")),
                new HashSet<>()
        ));

        // Process Overview Intent
        intentDefinitions.put("process overview", new IntentDefinition(
                new HashSet<>(Arrays.asList("overview")),
                new HashSet<>()
        ));

        // Add more intents and their keywords as needed
    }

    /**
     * Handles an input query string, processes it using NLP, and interprets it
     * as a process mining action.
     *
     * @param query The natural language query input.
     * @param processFileId The ID of the process file to operate on.
     * @return A map containing status, query type, result, and details URL.
     * @throws Exception if an error occurs during processing or if the query is
     * unrecognized.
     */
    public Map<String, Object> handleQuery(String query, Long processFileId) throws Exception {
        // Step 1: Classify the query type
        String queryType = getQueryType(query);

        // Step 2: Check the database for existing results based on query type
        Object result = fetchResultFromDatabase(queryType, processFileId);

        if (result != null) {
            // Log the successful query
            databaseService.saveQueryHistory(queryType, processFileId, "success");

            // Prepare response
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("queryType", queryType);
            response.put("result", result);
            response.put("detailsUrl", generateDetailsUrl(queryType, processFileId));

            return response;
        }

        // Step 3: If no result in the database, interpret and execute the query
        queryInterpreter.interpretAndExecute(queryType, processFileId);

        // Step 4: Fetch the newly saved result from the database
        result = fetchResultFromDatabase(queryType, processFileId);

        // Step 5: Log the successful query
        databaseService.saveQueryHistory(queryType, processFileId, "success");

        // Prepare response
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("queryType", queryType);
        response.put("result", result);
        response.put("detailsUrl", generateDetailsUrl(queryType, processFileId));

        return response;
    }

    /**
     * Classifies the query type based on tokens and intent definitions.
     *
     * @param query The natural language query.
     * @return The classified query type.
     */
    public String getQueryType(String query) {
        // Normalize and tokenize the query
        String normalizedQuery = query.toLowerCase();
        List<String> tokens = nlpProcessor.getLemmatizedTokensFromString(normalizedQuery);
        tokens = removeStopWords(tokens);

        for (Map.Entry<String, IntentDefinition> entry : intentDefinitions.entrySet()) {
            String intent = entry.getKey();
            IntentDefinition intentDef = entry.getValue();

            if (queryMatchesIntent(tokens, intentDef.getRequiredKeywords(), intentDef.getOptionalKeywords())) {
                return intent;
            }
        }

        throw new UnsupportedOperationException("Unknown query type: " + query);
    }

    private boolean queryMatchesIntent(List<String> tokens, Set<String> requiredKeywords, Set<String> optionalKeywords) {
        if (!tokens.containsAll(requiredKeywords)) {
            return false;
        }
        if (optionalKeywords.isEmpty()) {
            return true;
        }
        for (String keyword : optionalKeywords) {
            if (tokens.contains(keyword)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes stop words from the list of tokens.
     *
     * @param tokens The list of tokens.
     * @return A list of tokens with stop words removed.
     */
    private List<String> removeStopWords(List<String> tokens) {
        Set<String> stopWords = new HashSet<>(Arrays.asList(
                "the", "is", "at", "which", "on", "a", "an", "and", "can", "you",
                "please", "i", "we", "to", "of", "in", "it", "this", "that", "for", "with"
        ));
        List<String> filteredTokens = new ArrayList<>();
        for (String token : tokens) {
            if (!stopWords.contains(token)) {
                filteredTokens.add(token);
            }
        }
        return filteredTokens;
    }

    /**
     * Fetches the result from the database based on the query type.
     *
     * @param queryType The classified query type.
     * @param processFileId The ID of the process file.
     * @return The result object if found, else null.
     */
    private Object fetchResultFromDatabase(String queryType, Long processFileId) throws Exception {
        switch (queryType) {
            // Process Overview
            case "process overview":
                return databaseService.getProcessOverviewResult(processFileId);

            // Performance Analysis Results
            case "performance analysis - bottleneck":
                return databaseService.getBottleneckAnalysisResult(processFileId);
            case "performance analysis - case duration":
                return databaseService.getCaseDurationAnalysisResult(processFileId);
            case "performance analysis - frequency":
                return databaseService.getFrequencyAnalysisResult(processFileId);
            case "performance analysis - resource utilization":
                return databaseService.getResourceUtilizationAnalysisResult(processFileId);
            case "performance analysis":
                return databaseService.getDefaultPerformanceAnalysisResult(processFileId);

            // Process Discovery Results
            case "process discovery - alpha":
                return databaseService.getProcessDiscoveryResultByMethod(processFileId, "alpha");
            case "process discovery - inductive":
                return databaseService.getProcessDiscoveryResultByMethod(processFileId, "inductive");
            case "process discovery - heuristics":
                return databaseService.getProcessDiscoveryResultByMethod(processFileId, "heuristics");
            case "process discovery - bpmn inductive":
                return databaseService.getProcessDiscoveryResultByMethod(processFileId, "bpmn inductive");
            case "process discovery - dfg":
                return databaseService.getProcessDiscoveryResultByMethod(processFileId, "dfg");
            case "process discovery":
                return databaseService.getProcessDiscoveryResultByMethod(processFileId, "default");

            // Add more cases as needed
            default:
                return null;
        }
    }

    /**
     * Generates a details URL based on the query type and process file ID.
     *
     * @param queryType The classified query type.
     * @param processFileId The ID of the process file.
     * @return A string representing the details URL.
     */
    private String generateDetailsUrl(String queryType, Long processFileId) {
        switch (queryType) {
            // Process Overview
            case "process overview":
                return "/process-files/" + processFileId + "/overview-results";

            // Performance Analysis URLs
            case "performance analysis - bottleneck":
                return "/process-files/" + processFileId + "/bottleneck-results";
            case "performance analysis - case duration":
                return "/process-files/" + processFileId + "/case-duration-results";
            case "performance analysis - frequency":
                return "/process-files/" + processFileId + "/frequency-results";
            case "performance analysis - resource utilization":
                return "/process-files/" + processFileId + "/utilization-results";
            case "performance analysis":
                return "/process-files/" + processFileId + "/performance-results";

            // Process Discovery URLs
            case "process discovery - alpha":
                return "/process-files/" + processFileId + "/discovery-alpha-results";
            case "process discovery - inductive":
                return "/process-files/" + processFileId + "/discovery-inductive-results";
            case "process discovery - heuristics":
                return "/process-files/" + processFileId + "/discovery-heuristics-results";
            case "process discovery - bpmn inductive":
                return "/process-files/" + processFileId + "/discovery-bpmn-inductive-results";
            case "process discovery - dfg":
                return "/process-files/" + processFileId + "/discovery-dfg-results";
            case "process discovery":
                return "/process-files/" + processFileId + "/discovery-results";

            // Add more cases as needed
            default:
                return "/process-files/" + processFileId;
        }
    }

    /**
     * Main method for testing the NLPService.
     */
    public static void main(String[] args) {
        // Example usage
        // You can implement test cases here to validate the functionality
    }
}
