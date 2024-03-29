/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.kie.kogito.explainability.models;

import java.util.Map;

import org.kie.kogito.explainability.api.ModelIdentifier;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PredictOutput {

    @JsonProperty("modelIdentifier")
    private ModelIdentifier modelIdentifier;

    @JsonProperty("result")
    private Map<String, Object> result;

    public PredictOutput() {
    }

    public PredictOutput(ModelIdentifier modelIdentifier, Map<String, Object> result) {
        this.modelIdentifier = modelIdentifier;
        this.result = result;
    }

    public ModelIdentifier getModelIdentifier() {
        return modelIdentifier;
    }

    public void setModelIdentifier(ModelIdentifier modelIdentifier) {
        this.modelIdentifier = modelIdentifier;
    }

    public Map<String, Object> getResult() {
        return result;
    }

    public void setResult(Map<String, Object> result) {
        this.result = result;
    }
}
