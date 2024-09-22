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
import retrofit2.Call;

public class ProcessDiscoveryService {

    private ProcessMiningService processMiningService;

    public ProcessDiscoveryService(ProcessMiningService processMiningService) {
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
            default:
                throw new IllegalArgumentException("Unknown discovery method: " + method);
        }

        ProcessDiscoveryResponse response = executeWithRetry(call);

        if (response != null) {
            Gson gson = new Gson();
            String jsonResponse = gson.toJson(response);

            // Parse the JSON response
            ProcessDiscoveryResponse parsedResponse = gson.fromJson(jsonResponse, ProcessDiscoveryResponse.class);

            // Create and populate the ProcessDiscoveryResult object
            ProcessDiscoveryResult result = new ProcessDiscoveryResult();
            result.setProcessFileId(processFileId);
            result.setMethod(method);
            result.setNet(parsedResponse.getNet());
            result.setInitialMarking(parsedResponse.getInitialMarking());
            result.setFinalMarking(parsedResponse.getFinalMarking());
            result.setResponseJson(jsonResponse);

            // Save the result to the database
            SessionUtil.executeStatelessTransaction(session -> {
                session.insert(result);
                return null;
            });

            System.out.println("The Response: " + jsonResponse);
        }
    }

    private ProcessDiscoveryResponse executeWithRetry(Call<ProcessDiscoveryResponse> call) throws Exception {
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





