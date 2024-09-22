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
//import org.processmining.plugins.petrinets.PetriNetPlugin;
//import org.processmining.plugins.pnanalysis.PNAnalysisPlugin;
//import org.processmining.plugins.acceptingpetrinetminer.AcceptingPetriNetMinerPlugin;
//import org.processmining.plugins.tspetrinet.TSPetriNetPlugin;
//import org.processmining.models.graphbased.directed.petrinet.Petrinet;
//
//import java.util.logging.Logger;
//
//public class PetriNetsUtils {
//
//    private static final Logger logger = Logger.getLogger(PetriNetsUtils.class.getName());
//
//    public static Petrinet createPetriNet(XLog log) {
//        try {
//            PetriNetPlugin plugin = new PetriNetPlugin();
//            Petrinet net = plugin.create(log);
//            logger.info("Successfully created Petri net.");
//            return net;
//        } catch (Exception e) {
//            logger.severe("Failed to create Petri net: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void analyzePetriNet(Petrinet net) {
//        try {
//            PNAnalysisPlugin plugin = new PNAnalysisPlugin();
//            plugin.analyze(net);
//            logger.info("Successfully analyzed Petri net.");
//        } catch (Exception e) {
//            logger.severe("Failed to analyze Petri net: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void mineAcceptingPetriNet(XLog log) {
//        try {
//            AcceptingPetriNetMinerPlugin plugin = new AcceptingPetriNetMinerPlugin();
//            plugin.mine(log);
//            logger.info("Successfully mined Accepting Petri net.");
//        } catch (Exception e) {
//            logger.severe("Failed to mine Accepting Petri net: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void mineTSPetriNet(XLog log) {
//        try {
//            TSPetriNetPlugin plugin = new TSPetriNetPlugin();
//            plugin.mine(log);
//            logger.info("Successfully mined TS Petri net.");
//        } catch (Exception e) {
//            logger.severe("Failed to mine TS Petri net: " + e.getMessage());
//            throw e;
//        }
//    }
//}
//
