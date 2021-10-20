package com.company.jmixpm.screen.main;

import com.company.jmixpm.app.TaskService;
import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.ProjectTask;
import io.jmix.core.DataManager;
import io.jmix.ui.Notifications;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.ScreenTools;
import io.jmix.ui.action.Action;
import io.jmix.ui.component.*;
import io.jmix.ui.component.mainwindow.Drawer;
import io.jmix.ui.icon.JmixIcon;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.model.InstanceContainer;
import io.jmix.ui.navigation.Route;
import io.jmix.ui.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@UiController("MainScreen")
@UiDescriptor("main-screen.xml")
@Route(path = "main", root = true)
public class MainScreen extends Screen implements Window.HasWorkArea {

    @Autowired
    private ScreenTools screenTools;

    @Autowired
    private AppWorkArea workArea;
    @Autowired
    private Drawer drawer;

    @Autowired
    private Button collapseDrawerButton;

    @Autowired
    private CollectionLoader<Project> projectsDl;

    @Autowired
    private EntityComboBox<Project> projectSelector;
    @Autowired
    private TextField<String> nameSelector;
    @Autowired
    private CollectionLoader<ProjectTask> projectTasksDl;

    @Autowired
    private DateField<LocalDateTime> dateSelector;
    @Autowired
    private Notifications notifications;
    @Autowired
    private TaskService taskService;

    @Autowired
    private ScreenBuilders screenBuilders;

    @Override
    public AppWorkArea getWorkArea() {
        return workArea;
    }

    @Subscribe("collapseDrawerButton")
    private void onCollapseDrawerButtonClick(Button.ClickEvent event) {
        drawer.toggle();
        if (drawer.isCollapsed()) {
            collapseDrawerButton.setIconFromSet(JmixIcon.CHEVRON_RIGHT);
        } else {
            collapseDrawerButton.setIconFromSet(JmixIcon.CHEVRON_LEFT);
        }
    }

    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        screenTools.openDefaultScreen(
                UiControllerUtils.getScreenContext(this).getScreens());

        screenTools.handleRedirect();
    }

    @Subscribe("refreshAction")
    public void onRefreshAction(Action.ActionPerformedEvent event) {
        projectTasksDl.load();
        projectsDl.load();
    }

    @Subscribe("addTaskAction")
    public void onAddTaskAction(Action.ActionPerformedEvent event) {

        if (projectSelector.getValue() == null || nameSelector.getValue() == null || dateSelector.getValue() == null) {
            notifications.create(Notifications.NotificationType.ERROR).withCaption("Please fill all fields").show();
            projectSelector.focus();
            return;
        }

        taskService.createNewTask(projectSelector.getValue(), nameSelector.getValue(), dateSelector.getValue());
        projectTasksDl.load();
        projectSelector.setValue(null);
        nameSelector.setValue(null);
        dateSelector.setValue(null);
    }

    @Subscribe("tasksCalendar")
    public void onTasksCalendarCalendarEventClick(Calendar.CalendarEventClickEvent<ProjectTask> event) {
        ProjectTask projectTask = (ProjectTask) event.getEntity();
        Screen screen = screenBuilders.editor(ProjectTask.class, this)
                .editEntity(projectTask)
                .withOpenMode(OpenMode.DIALOG)
                .build();
        screen.addAfterCloseListener(afterCloseEvent -> {
            if (afterCloseEvent.closedWith(StandardOutcome.COMMIT)) {
                projectTasksDl.load();
            }
        });
        screen.show();
    }
}