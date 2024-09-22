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
//import org.processmining.plugins.modelrepair.ModelRepairPlugin;
//import org.processmining.plugins.logenhancement.LogEnhancementPlugin;
//import org.processmining.models.graphbased.directed.petrinet.Petrinet;
//
//import java.util.logging.Logger;
//
//public class ProcessEnhancementUtils {
//
//    private static final Logger logger = Logger.getLogger(ProcessEnhancementUtils.class.getName());
//
//    public static Petrinet repairModel(XLog log, Petrinet net) {
//        try {
//            ModelRepairPlugin plugin = new ModelRepairPlugin();
//            Petrinet repairedNet = plugin.repair(log, net);
//            logger.info("Successfully repaired model.");
//            return repairedNet;
//        } catch (Exception e) {
//            logger.severe("Failed to repair model: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void enhanceLog(XLog log) {
//        try {
//            LogEnhancementPlugin plugin = new LogEnhancementPlugin();
//            plugin.enhance(log);
//            logger.info("Successfully enhanced log.");
//        } catch (Exception e) {
//            logger.severe("Failed to enhance log: " + e.getMessage());
//            throw e;
//        }
//    }
//}
//
