package com.company.jmixpm.search;

import com.company.jmixpm.entity.Project;
import io.jmix.search.index.annotation.AutoMappedField;
import io.jmix.search.index.annotation.JmixEntitySearchIndex;

@JmixEntitySearchIndex(entity = Project.class)
public interface ProjectIndexDefinition {


    @AutoMappedField(
            includeProperties = {"*"},
            excludeProperties = {"manager", "tasks"}
    )
    void mapping();

}