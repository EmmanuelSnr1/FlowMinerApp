///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package PROMLibTests;
//
//
//
//import com.netnimblelabs.flowminer.services.NLPService;
//import com.netnimblelabs.flowminer.services.PerformanceAnalysisService;
//import com.netnimblelabs.flowminer.services.ProcessDiscoveryService;
//import com.netnimblelabs.flowminer.services.ProcessMiningService;
//import com.netnimblelabs.flowminer.services.RetrofitClient;
//import org.junit.Ignore;
//import org.junit.Test;
//import retrofit2.Retrofit;
//
//public class ProcessMiningServiceTest {
//    Retrofit retrofit = RetrofitClient.getClient("http://127.0.0.1:5000");
//    ProcessMiningService processMiningService = retrofit.create(ProcessMiningService.class);
//    ProcessDiscoveryService processDiscoveryService = new ProcessDiscoveryService(processMiningService);
//    PerformanceAnalysisService performanceAnalysisService = new PerformanceAnalysisService(processMiningService);
//    
//    @Ignore
//    @Test
//    public void TestPythonProcessDiscoveryService() throws Exception {
//        // Example usage
////        Long processFileId = 1L; // Assume this is obtained from the database after file upload
////        String xesPath = "/Users/admin/Downloads/BPI_Challenge_2019.xes";
////
////        // Discover process and save
////        processDiscoveryService.discoverProcessAndSaveAlpha( processFileId);
//
//        // Wait for the Python service to process the request
////        Thread.sleep(180000); // Sleep for 3 minutes
//
//        // Analyze bottlenecks and save
////        performanceAnalysisService.analyzeBottlenecksAndSave(xesPath, processFileId, "activity_name");
//
////        Retrofit retrofit = RetrofitClient.getClient("http://127.0.0.1:5000");
////        ProcessMiningService processMiningService = retrofit.create(ProcessMiningService.class);
//
//        // Initialize NLPService
//        NLPService.initialize(processMiningService);
//
//        // Example user query
//        String query = "Please discover the process using the alpha miner.";
//        Long processFileId = 1L; // Example file ID
//
//        // Process the query
//        NLPService.processQuery(query, processFileId);
//    }
//}
//
