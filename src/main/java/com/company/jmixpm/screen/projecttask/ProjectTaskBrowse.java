package com.company.jmixpm.screen.projecttask;

import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.ProjectTask;

@UiController("ProjectTask.browse")
@UiDescriptor("project-task-browse.xml")
@LookupComponent("projectTasksTable")
public class ProjectTaskBrowse extends StandardLookup<ProjectTask> {
}