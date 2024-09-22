///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.netnimblelabs.flowminer.processmining;
//
//import org.deckfour.xes.model.XLog;
//import org.processmining.alphaminer.plugins.AlphaMinerPlugin;
//import org.processmining.contexts.uitopia.UIPluginContext;
//import org.processmining.framework.plugin.PluginContext;
//import org.processmining.plugins.fuzzymodel.miner.FuzzyMinerPlugin;
//import org.processmining.plugins.heuristicsnet.miner.heuristics.miner.HeuristicsMiner;
//import org.processmining.plugins.inductiveminer2.mining.InductiveMiner;
//
///**
// *
// * @author admin
// */
//public class ProcessDiscoveryFailed {
//    /*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.netnimblelabs.flowminer.processmining;
//
///**
// *
// * @author admin
// */
//import org.deckfour.xes.model.XLog;
//import org.processmining.contexts.cli.CLIContext;
//import org.processmining.contexts.cli.CLIPluginContext;
//import org.processmining.contexts.uitopia.UIPluginContext;
//import org.processmining.framework.plugin.PluginContext;
//import org.processmining.plugins.fuzzymodel.miner.FuzzyMinerPlugin;
//
//import org.processmining.plugins.inductiveminer2.mining.InductiveMiner;
//
//import org.processmining.plugins.inductiveminer2.mining.MiningParameters; 
//    
//import org.processmining.plugins.heuristicsnet.miner.heuristics.miner.HeuristicsMiner;
//
//import org.processmining.directlyfollowsmodelminer.mining.DFMMiner;
//
//import org.processmining.alphaminer.plugins.AlphaMinerPlugin;
//
//
//public class ProcessDiscoveryUtils {
//
////    TODO: Learn how to use the Inductive miner
//    public static Object mineInductiveModel(PluginContext context, XLog log) throws Exception {
//        return InductiveMiner.mineProcessTree(context, log);
//    }
//
//    public static Object mineHeuristicsNet(PluginContext context, XLog log) throws Exception {
//        HeuristicsMiner miner = new HeuristicsMiner(context, log);
//        return miner.mine();
//    }
//
//    public static Object mineAlphaModel(PluginContext context, XLog log) throws Exception {
//        AlphaMinerPlugin alphaMiner = new AlphaMinerPlugin();
//        return alphaMiner.apply((UIPluginContext) context, log);
//    }
//    
////    TODO: Learn to use the DFM MINer
////    public static Object mineDirectlyFollowsModel(PluginContext context, XLog log) throws Exception {
////        DFMMiner dfmMiner = new DFMMiner();
////        return dfmMiner.mine(context, log);
////    }
//
//    public static Object mineFuzzyModel(PluginContext context, XLog log) throws Exception {
//        FuzzyMinerPlugin fuzzyMiner = new FuzzyMinerPlugin();
//        return fuzzyMiner.mineDefault(context, log);
//    }
//}
//
//
//
//}
