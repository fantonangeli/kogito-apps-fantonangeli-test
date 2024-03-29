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
package org.kie.kogito.index.postgresql.model;

import java.io.Serializable;
import java.util.Objects;

public class MilestoneEntityId implements Serializable {

    private String id;
    private String processInstance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProcessInstance() {
        return processInstance;
    }

    public void setProcessInstance(String processInstance) {
        this.processInstance = processInstance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MilestoneEntityId that = (MilestoneEntityId) o;
        return id.equals(that.id) && processInstance.equals(that.processInstance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, processInstance);
    }

    @Override
    public String toString() {
        return "MilestoneEntityId{" +
                "id='" + id + '\'' +
                ", processInstance='" + processInstance + '\'' +
                '}';
    }
}
