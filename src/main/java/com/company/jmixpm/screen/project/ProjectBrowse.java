package com.company.jmixpm.screen.project;

import io.jmix.core.Id;
import io.jmix.search.index.EntityIndexer;
import io.jmix.search.searching.SearchResult;
import io.jmix.searchui.component.SearchField;
import io.jmix.searchui.screen.result.SearchResultsScreen;
import io.jmix.ui.ScreenBuilders;
import io.jmix.ui.component.Button;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import com.company.jmixpm.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@UiController("Project.browse")
@UiDescriptor("project-browse.xml")
@LookupComponent("projectsTable")
public class ProjectBrowse extends StandardLookup<Project> {

    @Autowired
    private EntityIndexer entityIndexer;

    @Autowired
    private CollectionContainer<Project> projectsDc;
    @Autowired
    private ScreenBuilders screenBuilders;

    @Subscribe("reIndexBtn")
    public void onReIndexBtnClick(Button.ClickEvent event) {
        List<Id<?>> ids = projectsDc.getItems().stream().map(Id::of).collect(Collectors.toList());
        entityIndexer.indexCollectionByEntityIds(ids);
    }

    @Install(to = "projectSearchField", subject = "searchCompletedHandler")
    private void projectSearchFieldSearchCompletedHandler(SearchField.SearchCompletedEvent searchCompletedEvent) {
        SearchResult result = searchCompletedEvent.getSearchResult();

        screenBuilders.screen(this)
                .withScreenClass(SearchResultsScreen.class)
                .withOpenMode(OpenMode.DIALOG)
                .build()
                .setSearchResult(result)
                .show();
    }


}