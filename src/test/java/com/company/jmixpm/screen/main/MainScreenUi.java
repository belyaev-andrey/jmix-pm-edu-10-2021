package com.company.jmixpm.screen.main;

import com.company.jmixpm.screen.project.ProjectBrowseUi;
import com.company.jmixpm.screen.projecttask.ProjectTaskBrowseUi;
import io.jmix.masquerade.Wire;
import io.jmix.masquerade.base.Composite;
import io.jmix.masquerade.component.SideMenu;

public class MainScreenUi extends Composite<MainScreenUi> {

    @Wire
    private SideMenu sideMenu;

    public ProjectBrowseUi openProjectBrowse() {
        return sideMenu.openItem(new SideMenu.Menu<>(ProjectBrowseUi.class, "application", "Project.browse"));
    }

    public ProjectTaskBrowseUi openTaskBrowse() {
        return sideMenu.openItem(new SideMenu.Menu<>(ProjectTaskBrowseUi.class, "application", "ProjectTask.browse"));
    }
}