///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.netnimblelabs.flowminer.processmining;
//
///**
// *
// * @author admin
// */
//
//import org.deckfour.xes.model.XLog;
//import org.processmining.plugins.alignment.AlignmentPlugin;
//import org.processmining.plugins.pnetreplayer.PNetReplayerPlugin;
//import org.processmining.plugins.pnetalignmentanalysis.PNetAlignmentAnalysisPlugin;
//import org.processmining.models.graphbased.directed.petrinet.Petrinet;
//import org.processmining.models.semantics.petrinet.Marking;
//
//import java.util.logging.Logger;
//
//public class ConformanceCheckingUtils {
//
//    private static final Logger logger = Logger.getLogger(ConformanceCheckingUtils.class.getName());
//
//    public static void checkAlignment(XLog log, Petrinet net, Marking marking) {
//        try {
//            AlignmentPlugin plugin = new AlignmentPlugin();
//            plugin.align(log, net, marking);
//            logger.info("Successfully checked alignment.");
//        } catch (Exception e) {
//            logger.severe("Failed to check alignment: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void replayPetrinet(XLog log, Petrinet net, Marking marking) {
//        try {
//            PNetReplayerPlugin plugin = new PNetReplayerPlugin();
//            plugin.replay(log, net, marking);
//            logger.info("Successfully replayed Petri net.");
//        } catch (Exception e) {
//            logger.severe("Failed to replay Petri net: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void analyzePNetAlignment(XLog log, Petrinet net, Marking marking) {
//        try {
//            PNetAlignmentAnalysisPlugin plugin = new PNetAlignmentAnalysisPlugin();
//            plugin.analyze(log, net, marking);
//            logger.info("Successfully analyzed Petri net alignment.");
//        } catch (Exception e) {
//            logger.severe("Failed to analyze Petri net alignment: " + e.getMessage());
//            throw e;
//        }
//    }
//}
//
