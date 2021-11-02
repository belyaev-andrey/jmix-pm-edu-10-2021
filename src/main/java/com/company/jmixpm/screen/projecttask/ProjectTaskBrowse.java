package com.company.jmixpm.screen.projecttask;

import io.jmix.ui.UiComponents;
import io.jmix.ui.action.AbstractAction;
import io.jmix.ui.component.Component;
import io.jmix.ui.component.Label;
import io.jmix.ui.component.LinkButton;
import io.jmix.ui.download.Downloader;
import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.ProjectTask;
import org.springframework.beans.factory.annotation.Autowired;

@UiController("ProjectTask.browse")
@UiDescriptor("project-task-browse.xml")
@LookupComponent("projectTasksTable")
public class ProjectTaskBrowse extends StandardLookup<ProjectTask> {

    @Autowired
    private UiComponents components;
    @Autowired
    private Downloader downloader;

    @Install(to = "projectTasksTable.attachement", subject = "columnGenerator")
    private Component projectTasksTableAttachementColumnGenerator(ProjectTask projectTask) {
        if (projectTask.getAttachement() != null) {
            LinkButton linkButton = components.create(LinkButton.class);
            linkButton.setCaption(projectTask.getAttachement().getFileName());
            linkButton.setAction(new AbstractAction() {
                @Override
                public void actionPerform(Component component) {
                    downloader.download(projectTask.getAttachement());
                }
            });
            return linkButton;
        }
        return components.create(Label.class);
    }

    @Install(to = "projectTasksTable", subject = "styleProvider")
    private String projectTasksTableStyleProvider(ProjectTask entity, String property) {
        // for a cell
        if ("estimatedEfforts".equals(property) && entity.getEstimatedEfforts() == 0) {
            return "no-estimated-efforts";
        }

        // for a row
        if (property == null && entity.getEstimatedEfforts() == 0) {
            return "no-estimated-efforts";
        }

        return null;
    }
}