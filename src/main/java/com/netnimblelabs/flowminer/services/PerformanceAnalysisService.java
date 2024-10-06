package com.netnimblelabs.flowminer.services;


import com.netnimblelabs.flowminer.services.requests.PerformanceAnalysisRequest;
import com.netnimblelabs.flowminer.services.responses.FrequencyAnalysisResponse;
import com.netnimblelabs.flowminer.services.responses.PerformanceMapAnalysisResponse;
import com.netnimblelabs.flowminer.services.responses.ResourceUtilizationAnalysisResponse;
import com.netnimblelabs.flowminer.services.responses.BottleneckAnalysisResponse;
import com.netnimblelabs.flowminer.models.BottleneckAnalysisResult;
import com.netnimblelabs.flowminer.models.PerformanceMapAnalysisResult;
import com.netnimblelabs.flowminer.models.FrequencyAnalysisResult;
import com.netnimblelabs.flowminer.models.ResourceUtilizationAnalysisResult;
import com.netnimblelabs.flowminer.models.ProcessFile;
import com.netnimblelabs.flowminer.util.SessionUtil;
import com.google.gson.Gson;

import retrofit2.Call;

public class PerformanceAnalysisService {

    private ProcessMiningService processMiningService;

    public PerformanceAnalysisService(ProcessMiningService processMiningService, DatabaseService databaseService) {
        this.processMiningService = processMiningService;
    }

    public void analyzeBottlenecksAndSave(Long processFileId) throws Exception {
        analyzePerformanceAndSave(processFileId, "bottlenecks");
    }

    public void analyzeCaseDurationAndSave(Long processFileId) throws Exception {
        analyzePerformanceAndSave(processFileId, "case_duration");
    }

    public void analyzeFrequencyAndSave(Long processFileId) throws Exception {
        analyzePerformanceAndSave(processFileId, "frequency");
    }

    public void analyzeResourceUtilizationAndSave(Long processFileId) throws Exception {
        analyzePerformanceAndSave(processFileId, "resource_utilization");
    }

    private void analyzePerformanceAndSave(Long processFileId, String analysisType) throws Exception {
        // Fetch the ProcessFile from the database
        ProcessFile processFile = (ProcessFile) SessionUtil.executeStatelessTransaction(session -> {
            return session.get(ProcessFile.class, processFileId);
        });

        if (processFile == null) {
            throw new IllegalArgumentException("No ProcessFile found with ID: " + processFileId);
        }

        // Use the file path from the ProcessFile entity
        String xesPath = processFile.getFilePath();
        PerformanceAnalysisRequest request = new PerformanceAnalysisRequest(xesPath);

        switch (analysisType.toLowerCase()) {
            case "bottlenecks":
                analyzeAndSaveBottlenecks(processFileId, request);
                break;
            case "case_duration":
                analyzeAndSaveCaseDuration(processFileId, request);
                break;
            case "frequency":
                analyzeAndSaveFrequency(processFileId, request);
                break;
            case "resource_utilization":
                analyzeAndSaveResourceUtilization(processFileId, request);
                break;
            default:
                throw new IllegalArgumentException("Unknown analysis type: " + analysisType);
        }
    }

    private void analyzeAndSaveBottlenecks(Long processFileId, PerformanceAnalysisRequest request) throws Exception {
        Call<BottleneckAnalysisResponse> call = processMiningService.analyzeBottlenecks(request);
        BottleneckAnalysisResponse response = executeWithRetry(call);

        if (response != null) {
            Gson gson = new Gson();
            BottleneckAnalysisResult result = new BottleneckAnalysisResult();
            result.setProcessFileId(processFileId);

            // Serialize the bottlenecks map into a JSON string
            String bottlenecksJson = gson.toJson(response.getBottlenecks());
            result.setBottlenecks(bottlenecksJson); // Store the serialized JSON string

            result.setResponseJson(gson.toJson(response)); // Store the full response as JSON

            SessionUtil.executeStatelessTransaction(session -> {
                session.insert(result);
                return null;
            });

            System.out.println("Bottleneck Analysis Response: " + result.getResponseJson());
        }
    }

    private void analyzeAndSaveCaseDuration(Long processFileId, PerformanceAnalysisRequest request) throws Exception {
        Call<PerformanceMapAnalysisResponse> call = processMiningService.analyzeCaseDuration(request);
        PerformanceMapAnalysisResponse response = executeWithRetry(call);

        if (response != null) {
            Gson gson = new Gson();
            PerformanceMapAnalysisResult result = new PerformanceMapAnalysisResult();
            result.setProcessFileId(processFileId);
            result.setPerformanceMap(response.getPerformanceMap());
            result.setResponseJson(gson.toJson(response));

            SessionUtil.executeStatelessTransaction(session -> {
                session.insert(result);
                return null;
            });

            System.out.println("Case Duration Analysis Response: " + result.getResponseJson());
        }
    }

    private void analyzeAndSaveFrequency(Long processFileId, PerformanceAnalysisRequest request) throws Exception {
        Call<FrequencyAnalysisResponse> call = processMiningService.analyzeFrequency(request);
        FrequencyAnalysisResponse response = executeWithRetry(call);

        if (response != null) {
            Gson gson = new Gson();
            FrequencyAnalysisResult result = new FrequencyAnalysisResult();
            result.setProcessFileId(processFileId);
            result.setFrequencyMap(response.getFrequencyMap());
            result.setResponseJson(gson.toJson(response));

            SessionUtil.executeStatelessTransaction(session -> {
                session.insert(result);
                return null;
            });

            System.out.println("Frequency Analysis Response: " + result.getResponseJson());
        }
    }

    private void analyzeAndSaveResourceUtilization(Long processFileId, PerformanceAnalysisRequest request) throws Exception {
        Call<ResourceUtilizationAnalysisResponse> call = processMiningService.analyzeResourceUtilization(request);
        ResourceUtilizationAnalysisResponse response = executeWithRetry(call);

        if (response != null) {
            Gson gson = new Gson();
            ResourceUtilizationAnalysisResult result = new ResourceUtilizationAnalysisResult();
            result.setProcessFileId(processFileId);
            result.setUtilizationMap(response.getUtilizationMap());
            result.setResponseJson(gson.toJson(response));

            SessionUtil.executeStatelessTransaction(session -> {
                session.insert(result);
                return null;
            });

            System.out.println("Resource Utilization Analysis Response: " + result.getResponseJson());
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
