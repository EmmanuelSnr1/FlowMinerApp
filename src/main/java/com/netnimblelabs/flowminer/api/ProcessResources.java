/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.flowminer.api;

/**
 *
 * @author admin
 */
import com.netnimblelabs.flowminer.models.BottleneckAnalysisResult;
import com.netnimblelabs.flowminer.models.FrequencyAnalysisResult;
import com.netnimblelabs.flowminer.models.ProcessFile;
import com.netnimblelabs.flowminer.models.NLPQueryStatus;
import com.netnimblelabs.flowminer.models.PerformanceMapAnalysisResult;
import com.netnimblelabs.flowminer.models.ProcessDiscoveryResult;
import com.netnimblelabs.flowminer.models.ResourceUtilizationAnalysisResult;
import com.netnimblelabs.flowminer.services.NLPService;
import com.netnimblelabs.flowminer.util.SessionUtil;
import org.deckfour.xes.in.XesXmlParser;
import org.deckfour.xes.model.XLog;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Resource class to manage process files, NLP queries, and process analysis
 * results.
 */
@Path("/resource")
public class ProcessResources {

    private static final String DIRECTORY = "/Users/admin/NetBeansProjects/flowminer/uploads/"; // Local directory where files are stored

    // ----- File Management Endpoints -----
    /**
     * Endpoint to list all files in the predefined directory.
     *
     * @return Response with list of file names.
     */
    @GET
    @Path("/files")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listFiles() {
        File folder = new File(DIRECTORY);
        File[] files = folder.listFiles();

        List<String> fileNames = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileNames.add(file.getName());
                }
            }
        }
        return Response.ok(fileNames).build();
    }

    /**
     * Endpoint to select a file, run an analysis, and store the information in
     * the database.
     *
     * @param fileName The name of the file to process.
     * @param personalTag A personal tag to associate with the file.
     * @return Response with the saved ProcessFile entity.
     */
    @POST
    @Path("/process")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response processFile(@QueryParam("fileName") String fileName, @QueryParam("personalTag") String personalTag) {
        if (fileName == null || fileName.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).entity("File name is required").build();
        }

        File file = new File(DIRECTORY + fileName);
        if (!file.exists()) {
            return Response.status(Response.Status.NOT_FOUND).entity("File not found").build();
        }

        try {
            ProcessFile processFile = new ProcessFile();
            processFile.setFileName(fileName);
            processFile.setFilePath(file.getAbsolutePath());
            processFile.setPersonalTag(personalTag);
            processFile.setUploadDate(LocalDateTime.now());
            processFile.setFileType(fileName.endsWith(".xes") ? "XES" : "CSV");

            if ("XES".equals(processFile.getFileType())) {
                int recordCount = analyzeXesFile(file.getAbsolutePath());
                processFile.setRecordCount(recordCount);
            }

            SessionUtil.executeStatelessTransaction(session -> {
                session.insert(processFile);
                return null;
            });

            return Response.ok(processFile).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error processing file: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/process-files")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllProcessFiles() throws Exception {
        List<ProcessFile> processFiles = SessionUtil.executeStatelessTransaction(session
                -> session.createQuery("from ProcessFile").list()
        );
        return Response.ok(processFiles).build();
    }

    @GET
    @Path("/process-files/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProcessFile(@PathParam("id") Long id) throws Exception {
        ProcessFile processFile = (ProcessFile) SessionUtil.executeStatelessTransaction(session
                -> session.get(ProcessFile.class, id)
        );
        if (processFile == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Process file not found").build();
        }
        return Response.ok(processFile).build();
    }

    @DELETE
    @Path("/process-files/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProcessFile(@PathParam("id") Long id) throws Exception {
        SessionUtil.executeStatelessTransaction(session -> {
            ProcessFile processFile = (ProcessFile) session.get(ProcessFile.class, id);
            if (processFile != null) {
                session.delete(processFile);
            }
            return null;
        });
        return Response.ok("Process file deleted successfully").build();
    }

    @GET
    @Path("/process-files/{fileId}/discovery-results")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscoveryResults(@PathParam("fileId") Long fileId) throws Exception {
        List<ProcessDiscoveryResult> discoveryResults = SessionUtil.executeStatelessTransaction(session
                -> session.createQuery("from ProcessDiscoveryResult where processFileId = :fileId")
                        .setParameter("fileId", fileId)
                        .list()
        );
        return Response.ok(discoveryResults).build();
    }

    @GET
    @Path("/discovery-results/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDiscoveryResult(@PathParam("id") Long id) throws Exception {
        ProcessDiscoveryResult discoveryResult = (ProcessDiscoveryResult) SessionUtil.executeStatelessTransaction(session
                -> session.get(ProcessDiscoveryResult.class, id)
        );
        if (discoveryResult == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Discovery result not found").build();
        }
        return Response.ok(discoveryResult).build();
    }

    @GET
    @Path("/process-files/{fileId}/performance-results")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPerformanceResults(@PathParam("fileId") Long fileId) throws Exception {
        List<PerformanceMapAnalysisResult> performanceResults = SessionUtil.executeStatelessTransaction(session
                -> session.createQuery("from PerformanceMapAnalysisResult where processFileId = :fileId")
                        .setParameter("fileId", fileId)
                        .list()
        );
        return Response.ok(performanceResults).build();
    }

    @GET
    @Path("/process-files/{fileId}/frequency-results")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFrequencyResults(@PathParam("fileId") Long fileId) throws Exception {
        List<FrequencyAnalysisResult> frequencyResults = SessionUtil.executeStatelessTransaction(session
                -> session.createQuery("from FrequencyAnalysisResult where processFileId = :fileId")
                        .setParameter("fileId", fileId)
                        .list()
        );
        return Response.ok(frequencyResults).build();
    }

    @GET
    @Path("/frequency-results/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFrequencyResult(@PathParam("id") Long id) throws Exception {
        FrequencyAnalysisResult frequencyResult = (FrequencyAnalysisResult) SessionUtil.executeStatelessTransaction(session
                -> session.get(FrequencyAnalysisResult.class, id)
        );
        if (frequencyResult == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Frequency result not found").build();
        }
        return Response.ok(frequencyResult).build();
    }

    @GET
    @Path("/process-files/{fileId}/bottleneck-results")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBottleneckResults(@PathParam("fileId") Long fileId) throws Exception {
        List<BottleneckAnalysisResult> results = SessionUtil.executeStatelessTransaction(session -> {
            return session.createQuery("from BottleneckAnalysisResult where processFileId = :fileId")
                    .setParameter("fileId", fileId)
                    .list();
        });
        return Response.ok(results).build();
    }

    @GET
    @Path("/process-files/{fileId}/utilization-results")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUtilizationResults(@PathParam("fileId") Long fileId) throws Exception {
        List<ResourceUtilizationAnalysisResult> results = SessionUtil.executeStatelessTransaction(session -> {
            return session.createQuery("from ResourceUtilizationAnalysisResult where processFileId = :fileId")
                    .setParameter("fileId", fileId)
                    .list();
        });
        return Response.ok(results).build();
    }
    
    @GET
@Path("/utilization-results/{id}")
@Produces(MediaType.APPLICATION_JSON)
public Response getUtilizationResult(@PathParam("id") Long id) throws Exception {
    ResourceUtilizationAnalysisResult result = (ResourceUtilizationAnalysisResult) SessionUtil.executeStatelessTransaction(session -> {
        return session.get(ResourceUtilizationAnalysisResult.class, id);
    });
    if (result == null) {
        return Response.status(Response.Status.NOT_FOUND).entity("Utilization result not found").build();
    }
    return Response.ok(result).build();
}


    // ----- Helper Methods -----
    /**
     * Analyzes an XES file to count the number of records (events).
     *
     * @param filePath The path of the XES file.
     * @return The number of records in the file.
     * @throws Exception If the file cannot be processed.
     */
    private int analyzeXesFile(String filePath) throws Exception {
        XesXmlParser parser = new XesXmlParser();
        File xesFile = new File(filePath);
        if (parser.canParse(xesFile)) {
            XLog log = parser.parse(xesFile).get(0);
            return log.size(); // Assuming each log entry is a record
        }
        return 0;
    }
}
