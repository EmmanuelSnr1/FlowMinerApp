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
//import org.deckfour.xes.out.XesXmlSerializer;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.List;
//
//public class EventLogReaderUtil {
//
//    public static XLog parseXESLog(String filePath) throws Exception {
//        File file = new File(filePath);
//        if (!file.exists()) {
//            throw new IOException("File not found: " + filePath);
//        }
//        XesXmlParser parser = new XesXmlParser();
//        List<XLog> logs = parser.parse(new FileInputStream(file));
//        if (logs.isEmpty()) {
//            throw new IOException("No logs found in the file: " + filePath);
//        }
//        return logs.get(0);
//    }
//
//    public static void writeXESLog(XLog log, String filePath) throws Exception {
//        XesXmlSerializer serializer = new XesXmlSerializer();
//        serializer.serialize(log, new FileOutputStream(filePath));
//    }
//
//    public static void printLogDetails(XLog log) {
//        System.out.println("Log contains " + log.size() + " traces.");
//        log.forEach(trace -> {
//            System.out.println("Trace: " + trace.getAttributes().get("concept:name"));
//            trace.forEach(event -> {
//                System.out.println("  Event: " + event.getAttributes().get("concept:name"));
//            });
//        });
//    }
//}
//
//
