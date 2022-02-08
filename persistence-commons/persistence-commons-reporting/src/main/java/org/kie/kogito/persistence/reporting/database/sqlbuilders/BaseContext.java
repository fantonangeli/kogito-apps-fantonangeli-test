/*
 * Copyright 2022 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.kogito.persistence.reporting.database.sqlbuilders;

import java.util.List;
import java.util.Objects;

import org.kie.kogito.persistence.reporting.model.BaseMappingDefinition;
import org.kie.kogito.persistence.reporting.model.Field;
import org.kie.kogito.persistence.reporting.model.Mapping;
import org.kie.kogito.persistence.reporting.model.PartitionField;
import org.kie.kogito.persistence.reporting.model.paths.PathSegment;

public abstract class BaseContext<T, F extends Field<T>, P extends PartitionField<T>, M extends Mapping<T, F>> extends BaseMappingDefinition<T, F, P, M> implements Context<T, F, P, M> {

    private final List<PathSegment> mappingPaths;

    protected BaseContext(final String mappingId,
            final String sourceTableName,
            final String sourceTableJsonFieldName,
            final List<F> sourceTableIdentityFields,
            final List<P> sourceTablePartitionFields,
            final String targetTableName,
            final List<M> mappings,
            final List<PathSegment> mappingPaths) {
        super(mappingId,
                sourceTableName,
                sourceTableJsonFieldName,
                sourceTableIdentityFields,
                sourceTablePartitionFields,
                targetTableName,
                mappings);
        this.mappingPaths = Objects.requireNonNull(mappingPaths);
    }

    @Override
    public List<PathSegment> getMappingPaths() {
        return mappingPaths;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseContext)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        BaseContext<?, ?, ?, ?> that = (BaseContext<?, ?, ?, ?>) o;
        return getMappingPaths().equals(that.getMappingPaths());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getMappingPaths());
    }
}
