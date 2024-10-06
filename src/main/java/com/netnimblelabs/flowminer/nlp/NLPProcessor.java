/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.flowminer.nlp;

/**
 *
 * @author admin
 */
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.ling.*;

import java.util.*;


public class NLPProcessor {

    private StanfordCoreNLP pipeline;

    public NLPProcessor() {
        // Set up a pipeline with the most common properties
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize,ssplit,pos,lemma");
        this.pipeline = new StanfordCoreNLP(props);
    }

    public CoreDocument processText(String text) {
        CoreDocument document = new CoreDocument(text);
        pipeline.annotate(document);
        return document;
    }

    public List<CoreLabel> getTokens(CoreDocument document) {
        return document.tokens();
    }

    public String getLemmatizedText(CoreDocument document) {
        StringBuilder lemmatizedText = new StringBuilder();
        for (CoreLabel token : document.tokens()) {
            lemmatizedText.append(token.lemma()).append(" ");
        }
        return lemmatizedText.toString().trim();
    }

    public String getLemmatizedTextFromString(String text) {
        CoreDocument document = processText(text);
        return getLemmatizedText(document);
    }

    /**
     * Returns a list of lemmatized tokens from the input string.
     *
     * @param text The input text to process.
     * @return A list of lemmatized tokens.
     */
    public List<String> getLemmatizedTokensFromString(String text) {
        CoreDocument document = processText(text);
        List<String> tokens = new ArrayList<>();
        for (CoreLabel token : document.tokens()) {
            tokens.add(token.lemma().toLowerCase());
        }
        return tokens;
    }
}

