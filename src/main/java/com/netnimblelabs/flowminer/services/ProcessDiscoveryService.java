/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author admin
 */
package com.netnimblelabs.flowminer.services;

import com.netnimblelabs.flowminer.services.requests.ProcessDiscoveryRequest;
import com.netnimblelabs.flowminer.services.responses.ProcessDiscoveryResponse;
import com.netnimblelabs.flowminer.models.ProcessDiscoveryResult;
import com.netnimblelabs.flowminer.models.ProcessFile;
import com.netnimblelabs.flowminer.util.SessionUtil;
import com.google.gson.Gson;
import com.netnimblelabs.flowminer.models.ProcessOverviewResult;
import com.netnimblelabs.flowminer.services.requests.ProcessOverviewRequest;
import com.netnimblelabs.flowminer.services.responses.DFGDiscoveryResponse;
import com.netnimblelabs.flowminer.services.responses.ProcessOverviewResponse;
import retrofit2.Call;
import okhttp3.ResponseBody;

public class ProcessDiscoveryService {

    private ProcessMiningService processMiningService;

    public ProcessDiscoveryService(ProcessMiningService processMiningService, DatabaseService databaseService) {
        this.processMiningService = processMiningService;
    }

    public void discoverProcessAndSaveInductive(Long processFileId) throws Exception {
        discoverProcessAndSave(processFileId, "inductive");
    }

    public void discoverProcessAndSaveAlpha(Long processFileId) throws Exception {
        discoverProcessAndSave(processFileId, "alpha");
    }

    public void discoverProcessAndSaveHeuristics(Long processFileId) throws Exception {
        discoverProcessAndSave(processFileId, "heuristics");
    }

    // New method for Directly Follows Graph (DFG)
    public void discoverProcessAndSaveDfg(Long processFileId) throws Exception {
        discoverProcessAndSave(processFileId, "dfg");
    }

    // New method for BPMN Inductive Discovery
    public void discoverProcessAndSaveBpmnInductive(Long processFileId) throws Exception {
        discoverProcessAndSave(processFileId, "bpmn_inductive");
    }

    public void getProcessOverviewAndSave(Long processFileId) throws Exception {
        // Fetch the ProcessFile from the database
        ProcessFile processFile = (ProcessFile) SessionUtil.executeStatelessTransaction(session -> {
            return session.get(ProcessFile.class, processFileId);
        });

        if (processFile == null) {
            throw new IllegalArgumentException("No ProcessFile found with ID: " + processFileId);
        }

        // Use the file path from the ProcessFile entity
        String xesPath = processFile.getFilePath();

        ProcessOverviewRequest request = new ProcessOverviewRequest(xesPath);
        Call<ProcessOverviewResponse> call = processMiningService.getProcessOverview(request);

        ProcessOverviewResponse response = executeWithRetry(call);

        if (response != null) {
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(response); // Serialize the full response

            // Create and populate the ProcessOverviewResult object
            ProcessOverviewResult result = new ProcessOverviewResult();
            result.setProcessFileId(processFileId);
            result.setNumEvents(response.getNumEvents());
            result.setNumCases(response.getNumCases());
            result.setNumActivities(response.getNumActivities());
            result.setAverageThroughputTime(response.getAverageThroughputTime());
            result.setNumVariants(response.getNumVariants());

            // Convert start and end activities to JSON strings before saving
            String startActivitiesJson = gson.toJson(response.getStartActivities());
            String endActivitiesJson = gson.toJson(response.getEndActivities());

            // Set the JSON strings for start and end activities
            result.setStartActivities(startActivitiesJson);
            result.setEndActivities(endActivitiesJson);

            // Convert topVariants to JSON string before saving
            String topVariantsJson = gson.toJson(response.getTopVariants());
            result.setTopVariants(topVariantsJson);

            // Store the full response JSON
            result.setResponseJson(jsonResponse);

            // Save the result to the database
            SessionUtil.executeStatelessTransaction(session -> {
                session.insert(result);
                return null;
            });

            System.out.println("The Response: " + jsonResponse);
        }
    }

    private void discoverProcessAndSave(Long processFileId, String method) throws Exception {
        // Fetch the ProcessFile from the database
        ProcessFile processFile = (ProcessFile) SessionUtil.executeStatelessTransaction(session -> {
            return session.get(ProcessFile.class, processFileId);
        });

        if (processFile == null) {
            throw new IllegalArgumentException("No ProcessFile found with ID: " + processFileId);
        }

        // Use the file path from the ProcessFile entity
        String xesPath = processFile.getFilePath();

        ProcessDiscoveryRequest request = new ProcessDiscoveryRequest(xesPath);

        // Use the appropriate response class based on the method
        if (method.equalsIgnoreCase("dfg")) {
            Call<DFGDiscoveryResponse> call = processMiningService.discoverProcessDfg(request);
            DFGDiscoveryResponse response = executeWithRetry(call);

            if (response != null) {
                Gson gson = new Gson();
                String jsonResponse = gson.toJson(response);

                // Create and populate the ProcessDiscoveryResult object
                ProcessDiscoveryResult result = new ProcessDiscoveryResult();
                result.setProcessFileId(processFileId);
                result.setMethod(method);
                result.setResponseJson(jsonResponse);

                // Serialize the edges list to JSON and store it
                String edgesJson = gson.toJson(response.getDirectlyFollowsGraph().getEdges());
                result.setDfgEdgesJson(edgesJson);

                // Save the result to the database
                SessionUtil.executeStatelessTransaction(session -> {
                    session.insert(result);
                    return null;
                });

                System.out.println("The Response: " + jsonResponse);
            }
        } else {
            // Existing code for other methods
            Call<ProcessDiscoveryResponse> call;

            switch (method.toLowerCase()) {
                case "inductive":
                    call = processMiningService.discoverProcessInductive(request);
                    break;
                case "alpha":
                    call = processMiningService.discoverProcessAlpha(request);
                    break;
                case "heuristics":
                    call = processMiningService.discoverProcessHeuristics(request);
                    break;
                case "bpmn_inductive":
                    call = processMiningService.discoverBpmnInductive(request);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown discovery method: " + method);
            }

            ProcessDiscoveryResponse response = executeWithRetry(call);

            if (response != null) {
                Gson gson = new Gson();
                String jsonResponse = gson.toJson(response);

                // Create and populate the ProcessDiscoveryResult object
                ProcessDiscoveryResult result = new ProcessDiscoveryResult();
                result.setProcessFileId(processFileId);
                result.setMethod(method);
                result.setNet(response.getNet());
                result.setInitialMarking(response.getInitialMarking());
                result.setFinalMarking(response.getFinalMarking());
                result.setResponseJson(jsonResponse);

                // Save the result to the database
                SessionUtil.executeStatelessTransaction(session -> {
                    session.insert(result);
                    return null;
                });

                System.out.println("The Response: " + jsonResponse);
            }
        }
    }

    private <T> T executeWithRetry(Call<T> call) throws Exception {
        int retryCount = 0;
        int maxRetries = 3;
        long waitTime = 60000; // 1 minute

        while (retryCount < maxRetries) {
            try {
                return call.execute().body();
            } catch (Exception e) {
                retryCount++;
                if (retryCount >= maxRetries) {
                    throw e;
                }
                Thread.sleep(waitTime);
            }
        }
        return null;
    }
}

