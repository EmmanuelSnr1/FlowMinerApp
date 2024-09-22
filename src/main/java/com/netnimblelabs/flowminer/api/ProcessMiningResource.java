/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.flowminer.api;

/**
 *
 * @author admin
 */
import com.netnimblelabs.flowminer.util.SessionUtil;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/process_mining")
public class ProcessMiningResource {
    
    @GET
    @Path("/hi")
    public String getHiGreeting() {
        return "hi";
    }

//    @POST
//    @Path("/inductive")
//    @Consumes(MediaType.TEXT_PLAIN)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response discoverProcessUsingInductive(String xesPath) {
//        try {
//            ProcessMiningResponse response = ProcessMiningServiceClient.discoverProcessUsingInductive(xesPath);
//
//            // Save response data to database
//            SessionUtil.executeStatelessTransaction(session -> {
//                Processes process = new Processes();
//                process.setName("Inductive Process Discovery");
//                process.setDescription("Discovered using Inductive Miner");
//                session.insert(process);
//
//                return null;
//            });
//
//            return Response.ok(response).build();
//        } catch (Exception ex) {
//            Logger.getLogger(ProcessMiningResource.class.getName()).log(Level.SEVERE, null, ex);
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error discovering process").build();
//        }
//    }

//    @POST
//    @Path("/alpha")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response discoverProcessUsingAlpha(EventLogRequest request) {
//        try {
//            ProcessMiningResponse response = ProcessMiningServiceClient.discoverProcessUsingAlpha(request.getXesPath());
//            
//            // Save response data to database
//            SessionUtil.executeStatelessTransaction(session -> {
//                Processes process = new Processes();
//                process.setName("Alpha Process Discovery");
//                process.setDescription("Discovered using Alpha Miner");
//                session.insert(process);
//                
//                return null;
//            });
//            
//            return Response.ok(response).build();
//        } catch (Exception ex) {
//            Logger.getLogger(ProcessMiningResource.class.getName()).log(Level.SEVERE, null, ex);
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error discovering process").build();
//        }
//    }
//
//    @POST
//    @Path("/heuristics")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response discoverProcessUsingHeuristics(EventLogRequest request) {
//        try {
//            ProcessMiningResponse response = ProcessMiningServiceClient.discoverProcessUsingHeuristics(request.getXesPath());
//            
//            // Save response data to database
//            SessionUtil.executeStatelessTransaction(session -> {
//                Processes process = new Processes();
//                process.setName("Heuristics Process Discovery");
//                process.setDescription("Discovered using Heuristics Miner");
//                session.insert(process);
//                
//                return null;
//            });
//            
//            return Response.ok(response).build();
//        } catch (Exception ex) {
//            Logger.getLogger(ProcessMiningResource.class.getName()).log(Level.SEVERE, null, ex);
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error discovering process").build();
//        }
//    }
}

