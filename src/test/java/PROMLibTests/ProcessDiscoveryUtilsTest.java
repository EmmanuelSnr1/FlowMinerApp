///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package PROMLibTests;
//
///**
// *
// * @author admin
// */
//
//import com.netnimblelabs.flowminer.processmining.ProcessDiscoveryUtils;
//import java.io.FileInputStream;
//import java.util.List;
//import org.deckfour.xes.in.XesXmlParser;
//import org.deckfour.xes.model.XLog;
//import static org.junit.Assert.assertNotNull;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mockito;
//import org.processmining.contexts.uitopia.UIPluginContext;
//import org.processmining.framework.plugin.PluginContext;
//
//
//public class ProcessDiscoveryUtilsTest {
//
//    private PluginContext context;
//    private XLog log;
//
//    @Before
//    public void setUp() throws Exception {
//        
//        context = Mockito.mock(UIPluginContext.class);
//
////        context = mock(UIPluginContext.class);
//        XesXmlParser parser = new XesXmlParser();
//        FileInputStream inputStream = new FileInputStream("/Users/admin/Downloads/BPI Challenge 2017_1_all/BPI Challenge 2017.xes");
//        List<XLog> logs = parser.parse(inputStream);
//        log = logs.get(0);
//    }
//
//    
//    @Test
//    public void testMineHeuristicsNet() throws Exception {
//        Object result = ProcessDiscoveryUtils.mineHeuristicsNet(context, log);
////        assertNotNull(result);
//    }
//
//    @Test
//    public void testMineAlphaModel() throws Exception {
//        Object result = ProcessDiscoveryUtils.mineAlphaModel(context, log);
////        assertNotNull(result);
//    }
//
//    @Test
//    public void testMineFuzzyModel() throws Exception {
//        Object result = ProcessDiscoveryUtils.mineFuzzyModel(context, log);
//        assertNotNull(result);
//    }
//}
//
