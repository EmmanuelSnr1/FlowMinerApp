/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.flowminer.services;

/**
 *
 * @author admin
 */


import com.netnimblelabs.flowminer.models.*;
import com.netnimblelabs.flowminer.util.SessionUtil;
import java.time.LocalDateTime;
import java.util.List;

public class DatabaseService {

    // Fetch Process Overview Result
    public ProcessOverviewResult getProcessOverviewResult(Long processFileId) throws Exception {
        return (ProcessOverviewResult) SessionUtil.executeStatelessTransaction(session ->
            session.createQuery("from ProcessOverviewResult where processFileId = :fileId")
                   .setParameter("fileId", processFileId)
                   .uniqueResult()
        );
    }

    // Fetch Bottleneck Analysis Result
    public BottleneckAnalysisResult getBottleneckAnalysisResult(Long processFileId) throws Exception {
        return (BottleneckAnalysisResult) SessionUtil.executeStatelessTransaction(session ->
            session.createQuery("from BottleneckAnalysisResult where processFileId = :fileId")
                   .setParameter("fileId", processFileId)
                   .uniqueResult()
        );
    }

    // Fetch Case Duration Analysis Result
    public PerformanceMapAnalysisResult getCaseDurationAnalysisResult(Long processFileId) throws Exception {
        return (PerformanceMapAnalysisResult) SessionUtil.executeStatelessTransaction(session ->
            session.createQuery("from CaseDurationAnalysisResult where processFileId = :fileId")
                   .setParameter("fileId", processFileId)
                   .uniqueResult()
        );
    }

    // Fetch Frequency Analysis Result
    public FrequencyAnalysisResult getFrequencyAnalysisResult(Long processFileId) throws Exception {
        return (FrequencyAnalysisResult) SessionUtil.executeStatelessTransaction(session ->
            session.createQuery("from FrequencyAnalysisResult where processFileId = :fileId")
                   .setParameter("fileId", processFileId)
                   .uniqueResult()
        );
    }

    // Fetch Resource Utilization Analysis Result
    public ResourceUtilizationAnalysisResult getResourceUtilizationAnalysisResult(Long processFileId) throws Exception {
        return (ResourceUtilizationAnalysisResult) SessionUtil.executeStatelessTransaction(session ->
            session.createQuery("from ResourceUtilizationAnalysisResult where processFileId = :fileId")
                   .setParameter("fileId", processFileId)
                   .uniqueResult()
        );
    }

    // Fetch Default Performance Analysis Result
    public PerformanceAnalysisResult getDefaultPerformanceAnalysisResult(Long processFileId) throws Exception {
        return (PerformanceAnalysisResult) SessionUtil.executeStatelessTransaction(session ->
            session.createQuery("from PerformanceAnalysisResult where processFileId = :fileId and isDefault = true")
                   .setParameter("fileId", processFileId)
                   .uniqueResult()
        );
    }

    // Fetch Process Discovery Result by Method
    public ProcessDiscoveryResult getProcessDiscoveryResultByMethod(Long processFileId, String method) throws Exception {
        return (ProcessDiscoveryResult) SessionUtil.executeStatelessTransaction(session ->
            session.createQuery("from ProcessDiscoveryResult where processFileId = :fileId and method = :method")
                   .setParameter("fileId", processFileId)
                   .setParameter("method", method)
                   .uniqueResult()
        );
    }

    // Save Query History
    public void saveQueryHistory(String queryType, Long processFileId, String status) throws Exception {
        QueryHistory history = new QueryHistory();
        history.setQueryType(queryType);
        history.setProcessFileId(processFileId);
        history.setQueryTime(LocalDateTime.now());
        history.setResponseStatus(status);

        SessionUtil.executeStatelessTransaction(session -> {
            session.insert(history);
            return null;
        });
    }

    // Additional methods for other results if needed

}


