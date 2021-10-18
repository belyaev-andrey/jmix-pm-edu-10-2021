package com.company.jmixpm.screen.projecttask;

import com.company.jmixpm.app.TaskService;
import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.ProjectTask;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ProjectTask.edit")
@UiDescriptor("project-task-edit.xml")
@EditedEntityContainer("projectTaskDc")
public class ProjectTaskEdit extends StandardEditor<ProjectTask> {

    @Autowired
    private TaskService taskService;

    @Subscribe
    public void onInitEntity(InitEntityEvent<ProjectTask> event) {
        event.getEntity().setAssignee(taskService.findLeastBusyUser());
    }

}