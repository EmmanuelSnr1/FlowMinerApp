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
//import org.processmining.plugins.bpmn.BpmnPlugin;
//import org.processmining.plugins.performanceanalysis.PerformanceAnalysisPlugin;
//import org.processmining.plugins.performancespectrumintegration.PerformanceSpectrumIntegrationPlugin;
//import org.processmining.models.bpmn.BpmnModel;
//
//import java.util.logging.Logger;
//
//public class PerformanceAnalysisUtils {
//
//    private static final Logger logger = Logger.getLogger(PerformanceAnalysisUtils.class.getName());
//
//    public static BpmnModel analyzeBpmnModel(XLog log) {
//        try {
//            BpmnPlugin plugin = new BpmnPlugin();
//            BpmnModel model = plugin.mine(log);
//            logger.info("Successfully analyzed BPMN model.");
//            return model;
//        } catch (Exception e) {
//            logger.severe("Failed to analyze BPMN model: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void analyzePerformance(XLog log) {
//        try {
//            PerformanceAnalysisPlugin plugin = new PerformanceAnalysisPlugin();
//            plugin.analyze(log);
//            logger.info("Successfully analyzed performance.");
//        } catch (Exception e) {
//            logger.severe("Failed to analyze performance: " + e.getMessage());
//            throw e;
//        }
//    }
//
//    public static void integratePerformanceSpectrum(XLog log) {
//        try {
//            PerformanceSpectrumIntegrationPlugin plugin = new PerformanceSpectrumIntegrationPlugin();
//            plugin.integrate(log);
//            logger.info("Successfully integrated performance spectrum.");
//        } catch (Exception e) {
//            logger.severe("Failed to integrate performance spectrum: " + e.getMessage());
//            throw e;
//        }
//    }
//}
//
