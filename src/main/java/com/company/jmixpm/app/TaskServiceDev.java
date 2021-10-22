package com.company.jmixpm.app;

import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.ProjectTask;
import com.company.jmixpm.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@Profile("dev")
public class TaskServiceDev {

    @Autowired
    private DataManager dataManager;
    @Autowired
    private CurrentAuthentication currentAuthentication;

    public User findLeastBusyUser() {

        User user = (User)currentAuthentication.getUser();

        return user;
    }

    public ProjectTask createNewTask(Project project, String taskName, LocalDateTime startDate) {
        ProjectTask task = dataManager.create(ProjectTask.class);
        task.setProject(project);
        task.setName(taskName);
        task.setStartDate(startDate);
        task.setEstimatedEfforts(4);
        task.setAssignee((User)currentAuthentication.getUser());
        return dataManager.save(task);
    }

}