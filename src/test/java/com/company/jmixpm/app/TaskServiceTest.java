package com.company.jmixpm.app;

import com.company.jmixpm.entity.User;
import io.jmix.core.DataManager;
import io.jmix.core.FluentValuesLoader;
import io.jmix.core.entity.KeyValueEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {


    @Test
    @DisplayName("Test that we return the first user from the list")
    public void testFindLeastBusyUserTwoUsers(){

        User user1 = new User();
        User user2 = new User();

        KeyValueEntity entity1 = new KeyValueEntity();
        entity1.setValue("user", user1);
        entity1.setValue("tasks", 2L);

        KeyValueEntity entity2 = new KeyValueEntity();
        entity2.setValue("user", user2);
        entity2.setValue("tasks", 10L);

        List<KeyValueEntity> entities = List.of(entity1, entity2);

        FluentValuesLoader valuesLoader = Mockito.mock(FluentValuesLoader.class);
        Mockito.when(valuesLoader.properties("user", "tasks")).thenReturn(valuesLoader);
        Mockito.when(valuesLoader.list()).thenReturn(entities);

        DataManager dataManager = Mockito.mock(DataManager.class);
        Mockito.when(dataManager.loadValues(any(String.class))).thenReturn(valuesLoader);

        TaskService taskService = new TaskServiceProd(dataManager, null);

        assertEquals(user1, taskService.findLeastBusyUser());

    }

}