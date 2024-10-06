/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PROMLibTests;

/**
 *
 * @author admin
 */

import com.netnimblelabs.flowminer.processmining.EventLogReaderUtil;
//import com.netnimblelabs.flowminer.processmining.ProcessDiscoveryUtils;



import org.junit.Before;
import org.junit.Test;


import org.deckfour.xes.model.XLog;



import org.junit.Ignore;

public class EventLogReaderUtilTest {

    private String testFilePath;

    @Before
    public void setUp() {
        testFilePath = "/Users/admin/Desktop/My projects /ProcessMining/BPMining/process_mining_service/event_log_part_1.xes"; // Provide the correct path to the test log file
    }

    @Ignore
    @Test
    public void testParseXESLog() throws Exception {
        XLog log = EventLogReaderUtil.parseXESLog(testFilePath);
//        assertNotNull(log);
//        assertTrue(log.size() > 0);

    }

//    @Ignore
//    @Test
//    public void testWriteXESLog() throws Exception {
//        XLog log = EventLogReaderUtil.parseXESLog(testFilePath);
//        String outputFilePath = "output_test_log.xes";
//        EventLogReaderUtil.writeXESLog(log, outputFilePath);
//        File outputFile = new File(outputFilePath);
//        assertTrue(outputFile.exists());
//    }
//    
//    @Ignore
//    @Test
//    public void testPrintLogDetails() throws Exception {
//        XLog log = EventLogReaderUtil.parseXESLog(testFilePath);
//        EventLogReaderUtil.printLogDetails(log);
//        // Since printLogDetails() method prints to the console, we can't assert its output directly.
//        // You can manually verify the console output or use a library like SystemOutRule to capture and assert console output.
//    }
    
//    @Ignore
//    @Test
//    public void LogReaderUtilClass() throws Exception {
//        LogReaderUtils.loadEventLog(testFilePath);
//        // Since printLogDetails() method prints to the console, we can't assert its output directly.
//        // You can manually verify the console output or use a library like SystemOutRule to capture and assert console output.
//    }
//    
////    @Ignore
//    @Test
//    public void ProcessDiscoveryTest() throws Exception {
////        ProcessDiscoveryUtils.discoverProcessModelUsingAlphaMiner(LogReaderUtils.loadEventLog(testFilePath));        
//        try {
//            // Example usage of the utility methods
//            String xesFilePath = "/Users/admin/Downloads/BPI_Challenge_2019.xes";
//            XLog log = LogReaderUtils.loadEventLog(xesFilePath);
//
//            // Alpha Miner
//            Object[] alphaMinerResults = discoverProcessModelUsingAlphaMiner(log);
//            Petrinet alphaMinerNet = (Petrinet) alphaMinerResults[0];
//            Marking alphaMinerMarking = (Marking) alphaMinerResults[1];
//
//            if (alphaMinerNet != null) {
//                printPetrinetDetails(alphaMinerNet);
//            } else {
//                System.out.println("Alpha Miner did not return a valid Petrinet.");
//            }
//            } catch (Exception e) {
//            e.printStackTrace();
//        }
//        
        
        
        // Since printLogDetails() method prints to the console, we can't assert its output directly.
        // You can manually verify the console output or use a library like SystemOutRule to capture and assert console output.
        
//    }
//    public static void printPetrinetDetails(Petrinet net) {
//        System.out.println("Number of Places: " + net.getPlaces().size());
//        System.out.println("Number of Transitions: " + net.getTransitions().size());
//        System.out.println("Number of Arcs: " + net.getEdges().size());
//    }
}

