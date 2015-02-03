/*!
 * AtlantBH Custom Jmeter Components v1.0.0
 * http://www.atlantbh.com/jmeter-components/
 *
 * Copyright 2011, AtlantBH
 *
 * Licensed under the under the Apache License, Version 2.0.
 */

package com.atlantbh.jmeter.plugins.jsonutils.jsonpathextractor;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.apache.jmeter.processor.PostProcessor;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.testelement.AbstractTestElement;
import org.apache.jmeter.threads.JMeterContext;
import org.apache.jmeter.threads.JMeterVariables;
import org.apache.jorphan.logging.LoggingManager;
import org.apache.log.Logger;

/**
 * This is main class for JSONPath extractor which works on previous sample
 * result and extracts value from JSON output using JSONPath
 */
public class JSONPathExtractor extends AbstractTestElement implements PostProcessor {
    private static final Logger log = LoggingManager.getLoggerForClass();

    private static final long serialVersionUID = 1L;

    private static final String JSONPATH = "JSONPATH";
    private static final String VAR = "VAR";
    private static final String DEFAULT = "DEFAULT";

    public JSONPathExtractor() {
        super();
    }

    public String getJsonPath() {
        return getPropertyAsString(JSONPATH);
    }

    public void setJsonPath(String jsonPath) {
        setProperty(JSONPATH, jsonPath);
    }

    public String getVar() {
        return getPropertyAsString(VAR);
    }

    public void setVar(String var) {
        setProperty(VAR, var);
    }

    public void setDefaultValue(String defaultValue) {
        setProperty(DEFAULT, defaultValue);
    }

    public String getDefaultValue() {
        return getPropertyAsString(DEFAULT);
    }

    @Override
    public void process() {
        // NOTE: using String.format impacts performance
        // http://stackoverflow.com/questions/513600/should-i-use-javas-string-format-if-performance-is-important
        JMeterContext context = getThreadContext();
        JMeterVariables vars = context.getVariables();
        SampleResult previousResult = context.getPreviousResult();
        String responseData = previousResult.getResponseDataAsString();

        try {
            Object jsonPathResult = JsonPath.read(responseData, getJsonPath());
            if (jsonPathResult instanceof JSONArray) {
                vars.put(this.getVar(), jsonPathResult.toString());
                Object[] arr = ((JSONArray) jsonPathResult).toArray();

                int k = 1;
                while (vars.get(this.getVar() + "_" + k) != null) {
                    vars.remove(this.getVar() + "_" + k);
                    k++;
                }

                for (int n = 0; n < arr.length; n++) {
                    vars.put(this.getVar() + "_" + (n + 1), String.format("%s", arr[n]));
                }
            } else {
                vars.put(this.getVar(), String.format("%s", jsonPathResult));
            }
        } catch (Exception e) {
            log.error("Extract failed", e);
            vars.put(this.getVar(), getDefaultValue());
        }
    }
}
