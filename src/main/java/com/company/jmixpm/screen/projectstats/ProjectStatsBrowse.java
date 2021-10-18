package com.company.jmixpm.screen.projectstats;

import com.company.jmixpm.app.ProjectStatService;
import io.jmix.core.LoadContext;
import io.jmix.ui.action.Action;
import io.jmix.ui.model.CollectionLoader;
import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.ProjectStats;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@UiController("ProjectStats.browse")
@UiDescriptor("project-stats-browse.xml")
@LookupComponent("projectStatsesTable")
public class ProjectStatsBrowse extends StandardLookup<ProjectStats> {

    @Autowired
    private ProjectStatService projectStatService;
    @Autowired
    private CollectionLoader<ProjectStats> statsDl;

    @Install(to = "statsDl", target = Target.DATA_LOADER)
    private List<ProjectStats> statsDlLoadDelegate(LoadContext<ProjectStats> loadContext) {
        return projectStatService.fetchProjectStatistics();
    }

    @Subscribe("projectStatsesTable.refreshAction")
    public void onProjectStatsesTableRefreshAction(Action.ActionPerformedEvent event) {
        statsDl.load();
    }

}