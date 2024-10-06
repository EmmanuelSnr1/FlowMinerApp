/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.flowminer.services;

/**
 *
 * @author admin
 */

import java.util.Set;

public class IntentDefinition {
    private Set<String> requiredKeywords;
    private Set<String> optionalKeywords;

    public IntentDefinition(Set<String> requiredKeywords, Set<String> optionalKeywords) {
        this.requiredKeywords = requiredKeywords;
        this.optionalKeywords = optionalKeywords;
    }

    public Set<String> getRequiredKeywords() {
        return requiredKeywords;
    }

    public Set<String> getOptionalKeywords() {
        return optionalKeywords;
    }
}

