package com.company.jmixpm.listener;

import com.company.jmixpm.entity.ProjectTask;
import com.company.jmixpm.event.TaskChangedEvent;
import io.jmix.core.event.EntityChangedEvent;
import io.jmix.core.event.EntityChangedEvent.Type;
import io.jmix.ui.UiEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class ProjectTaskEventListener {

    @Autowired
    private UiEventPublisher uiEventPublisher;

    @TransactionalEventListener
    public void onProjectTaskChangedAfterCommit(EntityChangedEvent<ProjectTask> event) {
        final Type type = event.getType();
        if (type == Type.CREATED || type == Type.DELETED) {
            uiEventPublisher.publishEvent(new TaskChangedEvent(this));
        }
    }
}