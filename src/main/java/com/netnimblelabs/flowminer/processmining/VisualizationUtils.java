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
//import org.processmining.plugins.inductiveVisualMiner.InductiveVisualMiner;
//import org.processmining.plugins.inductivevisualminer.InductiveVisualMinerPlugin;
//import org.processmining.plugins.interactivevisualization.InteractiveVisualization;
//
//import java.util.logging.Logger;
//
//public class VisualizationUtils {
//
//    private static final Logger logger = Logger.getLogger(VisualizationUtils.class.getName());
//
//    public static void visualizeInductiveModel(XLog log) {
//        try {
//            InductiveVisualMinerPlugin plugin = new InductiveVisualMinerPlugin();
//            plugin.visualize(log);
//            logger.info("Successfully visualized Inductive model.");
//        } catch (Exception e) {
//            logger.severe("Failed to visualize Inductive model: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void interactiveVisualize(XLog log) {
//        try {
//            InteractiveVisualization plugin = new InteractiveVisualization();
//            plugin.visualize(log);
//            logger.info("Successfully created interactive visualization.");
//        } catch (Exception e) {
//            logger.severe("Failed to create interactive visualization: " + e.getMessage());
//            throw e;
//        }
//    }
//}
//
