package com.company.jmixpm.app;

import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.ProjectTask;
import com.company.jmixpm.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.security.CurrentAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class TaskServiceProd implements TaskService {

    @Autowired
    private DataManager dataManager;
    @Autowired
    private CurrentAuthentication currentAuthentication;

    @Override
    public User findLeastBusyUser() {

        User user = dataManager.loadValues("select u, count(t.id) from User u left outer join ProjectTask t " +
                        "on u = t.assignee " +
                        "group by u order by count(t.id)")
                .properties("user", "tasks")
                .list().stream()
                .map(e -> e.<User>getValue("user"))
                .findFirst()
                .orElseThrow(IllegalStateException::new);

        return user;
    }

    @Override
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