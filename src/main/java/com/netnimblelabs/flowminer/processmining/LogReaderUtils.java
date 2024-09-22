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
//import org.deckfour.xes.in.XesXmlParser;
//import org.deckfour.xes.model.XLog;
//import org.deckfour.xes.model.XTrace;
//import org.deckfour.xes.model.XEvent;
//import org.deckfour.xes.model.impl.XLogImpl;
//import org.deckfour.xes.out.XesXmlSerializer;
//import org.deckfour.xes.extension.std.XConceptExtension;
//import org.deckfour.xes.factory.XFactory;
//import org.deckfour.xes.factory.XFactoryRegistry;
//import org.deckfour.xes.info.impl.XLogInfoImpl;
//import org.deckfour.xes.model.XAttributeMap;
//import org.deckfour.xes.model.XAttribute;
//import org.deckfour.xes.classification.XEventNameClassifier;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.List;
//
//public class LogReaderUtils {
//
//    // Load an event log from a XES file
//    public static XLog loadEventLog(String filePath) throws Exception {
//        File xesFile = new File(filePath);
//        XesXmlParser parser = new XesXmlParser();
//        List<XLog> logs = parser.parse(new FileInputStream(xesFile));
//        if (logs.isEmpty()) {
//            throw new IOException("No logs found in the specified file.");
//        }
//        return logs.get(0);
//    }
//
//    // Save an event log to a XES file
//    public static void saveEventLog(XLog log, String filePath) throws Exception {
//        XesXmlSerializer serializer = new XesXmlSerializer();
//        serializer.serialize(log, new FileOutputStream(new File(filePath)));
//    }
//
//    // Get the number of traces in the log
//    public static int getNumberOfTraces(XLog log) {
//        return log.size();
//    }
//
//    // Get the number of events in a trace
//    public static int getNumberOfEvents(XTrace trace) {
//        return trace.size();
//    }
//
//    // Get the name of the trace
//    public static String getTraceName(XTrace trace) {
//        return XConceptExtension.instance().extractName(trace);
//    }
//
//    // Print a summary of the event log
//    public static void printLogSummary(XLog log) {
//        System.out.println("Log Summary:");
//        System.out.println("Number of Traces: " + getNumberOfTraces(log));
//        for (XTrace trace : log) {
//            System.out.println("Trace: " + getTraceName(trace) + " has " + getNumberOfEvents(trace) + " events.");
//        }
//    }
//
//    // Convert an event log to CSV format
//    public static void exportLogToCSV(XLog log, String filePath) throws Exception {
//        FileOutputStream fos = new FileOutputStream(new File(filePath));
//        StringBuilder sb = new StringBuilder();
//        sb.append("Case ID,Event Name,Timestamp\n");
//        for (XTrace trace : log) {
//            String caseId = getTraceName(trace);
//            for (XEvent event : trace) {
//                String eventName = XConceptExtension.instance().extractName(event);
//                String timestamp = event.getAttributes().get("time:timestamp").toString();
//                sb.append(caseId).append(",").append(eventName).append(",").append(timestamp).append("\n");
//            }
//        }
//        fos.write(sb.toString().getBytes());
//        fos.close();
//    }
//}
//
