/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.flowminer.config;

import com.netnimblelabs.flowminer.api.api;
import com.netnimblelabs.flowminer.api.ProcessMiningResource;
import org.glassfish.jersey.jackson.JacksonFeature;


import org.glassfish.jersey.server.ResourceConfig;


/**
 *
 * @author admin
 */
public class AppConfig extends ResourceConfig {
    public AppConfig() {
        register(api.class);
        register(JacksonFeature.class);

        register(ProcessMiningResource.class);
        register(ProcessMiningResource.class);
        packages("com.netnimblelabs.flowminer.api");
    } 
}


