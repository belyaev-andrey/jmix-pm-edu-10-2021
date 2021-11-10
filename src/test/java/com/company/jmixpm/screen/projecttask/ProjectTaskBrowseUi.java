package com.company.jmixpm.screen.projecttask;

import com.codeborne.selenide.Condition;
import io.jmix.masquerade.Wire;
import io.jmix.masquerade.base.Composite;
import io.jmix.masquerade.component.Button;
import io.jmix.masquerade.component.Table;

import static io.jmix.masquerade.Components.wire;

public class ProjectTaskBrowseUi extends Composite<ProjectTaskBrowse> {

    @Wire
    private Button createBtn;
    @Wire
    private Table projectTasksTable;

    public Table getTasksTable() {
        return projectTasksTable;
    }

    public ProjectTaskEditUi create() {
        createBtn.should(Condition.visible)
                .click();
        return wire(ProjectTaskEditUi.class);
    }
}