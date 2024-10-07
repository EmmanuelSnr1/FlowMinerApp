/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.flowminer.api;

/**
 *
 * @author admin
 */

/**
 * Resource class to handle NLP-based queries and actions.
 */

import com.netnimblelabs.flowminer.models.NLPQueryRequest;
import com.netnimblelabs.flowminer.models.ProcessFile;
import com.netnimblelabs.flowminer.services.DatabaseService;
import com.netnimblelabs.flowminer.services.NLPService;
import com.netnimblelabs.flowminer.services.PerformanceAnalysisService;
import com.netnimblelabs.flowminer.services.ProcessDiscoveryService;
import com.netnimblelabs.flowminer.services.ProcessMiningService;
import com.netnimblelabs.flowminer.services.RetrofitClient;
import com.netnimblelabs.flowminer.util.SessionUtil;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/nlp")
public class NLPResource {

    private NLPService nlpService;

    public NLPResource() {
        // Initialize RetrofitClient
        String baseUrl = "http://127.0.0.1:5000/";
        RetrofitClient retrofitClient = new RetrofitClient();
        ProcessMiningService processMiningService = retrofitClient.getClient(baseUrl)
                .create(ProcessMiningService.class);

        // Initialize services
        DatabaseService databaseService = new DatabaseService();
        ProcessDiscoveryService discoveryService = new ProcessDiscoveryService(processMiningService, databaseService);
        PerformanceAnalysisService performanceService = new PerformanceAnalysisService(processMiningService, databaseService);

        // Initialize NLPService with dependencies
        this.nlpService = new NLPService(discoveryService, performanceService, databaseService);
    }

    /**
     * Endpoint to handle an NLP query and execute the appropriate action.
     *
     * @param request The NLP query request containing the query and process file ID.
     * @return Response indicating the result of the operation.
     */
    @POST
    @Path("/query")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleNLPQuery(NLPQueryRequest request) {
        String query = request.getQuery();
        Long processFileId = request.getProcessFileId();

        if (query == null || query.trim().isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Parameter 'query' is required and cannot be empty.")
                    .build();
        }
        if (processFileId == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Parameter 'processFileId' is required.")
                    .build();
        }

        try {
            // Handle the query using NLPService
            Map<String, Object> result = nlpService.handleQuery(query, processFileId);
            return Response.ok(result).build();
        } catch (UnsupportedOperationException e) {
            // Log the exception
            // logger.warn("Unsupported operation: {}", e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Unsupported query type: " + e.getMessage())
                    .build();
        } catch (Exception e) {
            // Log the exception
            // logger.error("Error processing NLP query", e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Internal server error occurred while processing the query.")
                    .build();
        }
    }
    
    @GET
    @Path("/history")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllQueryHistory() throws Exception {
        List<ProcessFile> processFiles = SessionUtil.executeStatelessTransaction(session
                -> session.createQuery("from QueryHistory").list()
        );
        return Response.ok(processFiles).build();
    }

    /**
     * Test endpoint to verify NLPService is working.
     *
     * @return A simple success message.
     */
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public Response testNLPService() {
        return Response.ok("NLP Service is up and running!").build();
    }
}




//http://localhost:8080/flowminer/api/nlp