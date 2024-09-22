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
//import java.io.File;
//import java.util.List;
//
//public class XESLogUtils {
//
//    public static XLog parseXESLog(File file) throws Exception {
//        XesXmlParser parser = new XesXmlParser();
//        List<XLog> logs = parser.parse(file);
//        if (!logs.isEmpty()) {
//            return logs.get(0);
//        }
//        throw new Exception("Failed to parse XES log file.");
//    }
//}
//
