package com.company.jmixpm.search;

import com.company.jmixpm.entity.ProjectTask;
import io.jmix.search.index.annotation.AutoMappedField;
import io.jmix.search.index.annotation.JmixEntitySearchIndex;

@JmixEntitySearchIndex(entity = ProjectTask.class)
public interface TaskIndexDefinition {

    @AutoMappedField(
            includeProperties = {"name", "assignee.name", "timeEntries.description"}
    )
    @AutoMappedField (
            includeProperties = {"attachement"},
            analyzer = "russian"
    )
    void mapping();
}