/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.flowminer.api;

/**
 *
 * @author admin
 */

import com.netnimblelabs.flowminer.services.NLPService;
import com.netnimblelabs.flowminer.util.SessionUtil;
import com.netnimblelabs.flowminer.services.PerformanceAnalysisService;
import com.netnimblelabs.flowminer.services.ProcessDiscoveryService;
import com.netnimblelabs.flowminer.services.ProcessMiningService;
import com.netnimblelabs.flowminer.services.RetrofitClient;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Resource class to handle NLP-based queries and actions.
 */
@Path("/nlp")
public class NLPResource {

    private NLPService nlpService;

    public NLPResource() {
        // Initialize services (assuming dependencies are injected or otherwise initialized)
        ProcessMiningService processMiningService = RetrofitClient.getClient("http://127.0.0.1:5000/")
                .create(ProcessMiningService.class);
        ProcessDiscoveryService discoveryService = new ProcessDiscoveryService(processMiningService);
        PerformanceAnalysisService performanceService = new PerformanceAnalysisService(processMiningService);
        this.nlpService = new NLPService(discoveryService, performanceService);
    }

    /**
     * Endpoint to handle an NLP query and execute the appropriate action.
     * 
     * @param query The natural language query.
     * @param processFileId The ID of the process file to operate on.
     * @return Response indicating the result of the operation.
     */
    @POST
    @Path("/query")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response handleNLPQuery(@QueryParam("query") String query, @QueryParam("processFileId") Long processFileId) {
        if (query == null || query.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Query is required").build();
        }
        if (processFileId == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Process file ID is required").build();
        }

        try {
            nlpService.handleQuery(query, processFileId);
            Map<String, String> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Query processed successfully.");
            return Response.ok(response).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error processing query: " + e.getMessage())
                    .build();
        }
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

