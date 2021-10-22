package com.company.jmixpm.app;

import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.ProjectTask;
import com.company.jmixpm.entity.User;

import java.time.LocalDateTime;

public interface TaskService {
    User findLeastBusyUser();

    ProjectTask createNewTask(Project project, String taskName, LocalDateTime startDate);
}