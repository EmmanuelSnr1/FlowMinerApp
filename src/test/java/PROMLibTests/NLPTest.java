///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package PROMLibTests;
//
//import com.netnimblelabs.flowminer.services.NLPService;
//import com.netnimblelabs.flowminer.services.PerformanceAnalysisService;
//import com.netnimblelabs.flowminer.services.ProcessDiscoveryService;
//import com.netnimblelabs.flowminer.services.ProcessMiningService;
//import com.netnimblelabs.flowminer.services.RetrofitClient;
//import retrofit2.Retrofit;
//import org.junit.Ignore;
//import org.junit.Test;
//
///**
// *
// * @author admin
// */
//public class NLPTest {
//    
//    @Ignore
//    @Test
//    public void TestQueries() {
//        // Use RetrofitClient to get the Retrofit instance
//        String baseUrl = "http://127.0.0.1:5000/";
//        Retrofit retrofit = RetrofitClient.getClient(baseUrl);
//
//        // Create the ProcessMiningService instance using Retrofit
//        ProcessMiningService processMiningService = retrofit.create(ProcessMiningService.class);
//
//        // Initialize the services
//        ProcessDiscoveryService discoveryService = new ProcessDiscoveryService(processMiningService);
//        PerformanceAnalysisService performanceService = new PerformanceAnalysisService(processMiningService);
//
//        // Initialize the NLPService with the actual services
//        NLPService nlpService = new NLPService(discoveryService, performanceService);
//        
//        // Test query handling
//        try {
////            String query = "Please discover the process using alpha method.";
//            Long processFileId = 1L; // Example process file ID
////            nlpService.handleQuery(query, processFileId);
////            
////            query = "Can you analyze the bottleneck?";
////            nlpService.handleQuery(query, processFileId);
////          
//            String query;
//            query = "I'd like to analyze the frequencies in this process";
//            nlpService.handleQuery(query, processFileId);
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    
//}
