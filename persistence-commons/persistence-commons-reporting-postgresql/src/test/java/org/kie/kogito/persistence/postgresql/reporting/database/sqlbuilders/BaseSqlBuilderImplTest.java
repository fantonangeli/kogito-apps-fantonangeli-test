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
package org.kie.kogito.persistence.postgresql.reporting.database.sqlbuilders;

import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kie.kogito.persistence.postgresql.model.CacheEntityRepository;
import org.kie.kogito.persistence.postgresql.reporting.database.GenericPostgresDatabaseManagerImpl;
import org.kie.kogito.persistence.postgresql.reporting.model.JsonType;
import org.kie.kogito.persistence.postgresql.reporting.model.PostgresField;
import org.kie.kogito.persistence.postgresql.reporting.model.PostgresMapping;
import org.kie.kogito.persistence.postgresql.reporting.model.PostgresMappingDefinition;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
abstract class BaseSqlBuilderImplTest {

    protected static final PostgresMappingDefinition DEFINITION = new PostgresMappingDefinition("mappingId",
            "sourceTableName",
            "sourceTableJsonFieldName",
            List.of(new PostgresField("id", JsonType.STRING)),
            "targetTableName",
            List.of(new PostgresMapping("root",
                    new PostgresField("field1",
                            JsonType.STRING)),
                    new PostgresMapping("root.child",
                            new PostgresField("field2",
                                    JsonType.STRING)),
                    new PostgresMapping("root.child.collection[].child",
                            new PostgresField("field3",
                                    JsonType.STRING)),
                    new PostgresMapping("root.child.sibling",
                            new PostgresField("field4",
                                    JsonType.STRING))));

    @Mock
    private CacheEntityRepository repository;

    @Mock
    private IndexesSqlBuilderImpl mockIndexesSqlBuilder;

    @Mock
    private TableSqlBuilderImpl mockTableSqlBuilder;

    @Mock
    private TriggerDeleteSqlBuilderImpl mockTriggerDeleteSqlBuilder;

    @Mock
    private TriggerInsertSqlBuilderImpl mockTriggerInsertSqlBuilder;

    protected GenericPostgresDatabaseManagerImpl manager;

    @BeforeEach
    void setup() {
        this.manager = new GenericPostgresDatabaseManagerImpl(repository,
                getIndexesBuilder(),
                getTableBuilder(),
                getTriggerDeleteBuilder(),
                getTriggerInsertBuilder());
    }

    protected IndexesSqlBuilderImpl getIndexesBuilder() {
        return mockIndexesSqlBuilder;
    }

    protected TableSqlBuilderImpl getTableBuilder() {
        return mockTableSqlBuilder;
    }

    protected TriggerDeleteSqlBuilderImpl getTriggerDeleteBuilder() {
        return mockTriggerDeleteSqlBuilder;
    }

    protected TriggerInsertSqlBuilderImpl getTriggerInsertBuilder() {
        return mockTriggerInsertSqlBuilder;
    }

    protected abstract String getCreateSql(final PostgresContext context);

    protected abstract String getDestroySql(final PostgresContext context);

    protected abstract void assertCreateSql(final String sql);

    protected abstract void assertDestroySql(final String sql);

    @Test
    void testCreate() {
        final PostgresContext context = manager.createContext(DEFINITION);

        final String sql = getCreateSql(context);

        assertCreateSql(sql);
    }

    @Test
    void testDestroy() {
        final PostgresContext context = manager.createContext(DEFINITION);

        final String sql = getDestroySql(context);

        assertDestroySql(sql);
    }

    protected void assertSequentialContent(final String actual,
            final String... expected) {
        if (Objects.isNull(expected)) {
            return;
        }
        if (expected.length == 0) {
            return;
        }
        int idx = 0;
        for (String line : expected) {
            idx = actual.indexOf(line, idx);
            assertTrue(idx > -1, String.format("Line '%s' not found in '%s'", line, actual));
        }
    }
}
