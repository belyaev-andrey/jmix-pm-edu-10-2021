package com.company.jmixpm.app;

import io.jmix.graphql.service.UserInfo;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@GraphQLApi
public class TaskApiService {

    private final TaskService taskService;

    public TaskApiService(TaskService taskService) {
        this.taskService = taskService;
    }

    @GraphQLQuery(name = "leastBusyUser")
    @Transactional
    public UserInfo findLeastBusyUser() {
        return new UserInfo(taskService.findLeastBusyUser());
    }
}