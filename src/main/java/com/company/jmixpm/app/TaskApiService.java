package com.company.jmixpm.app;

import com.company.jmixpm.entity.ProjectStats;
import com.company.jmixpm.entity.User;
import io.jmix.graphql.service.UserInfo;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@GraphQLApi
public class TaskApiService {

    private final TaskService taskService;
    private final ProjectStatService projectStatService;

    public TaskApiService(TaskService taskService, ProjectStatService projectStatService) {
        this.taskService = taskService;
        this.projectStatService = projectStatService;
    }

    @GraphQLQuery(name = "leastBusyUser")
    @Transactional
    public UserInfo findLeastBusyUser() {
        return new UserInfo(taskService.findLeastBusyUser());
    }

    @GraphQLQuery(name = "stats")
    public List<ProjectStats> fetchProjectStatistics() {
        return projectStatService.fetchProjectStatistics();
    }
}