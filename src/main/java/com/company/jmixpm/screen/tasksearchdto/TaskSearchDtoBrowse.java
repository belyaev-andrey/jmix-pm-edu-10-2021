package com.company.jmixpm.screen.tasksearchdto;

import io.jmix.core.DataManager;
import io.jmix.search.searching.EntitySearcher;
import io.jmix.search.searching.SearchContext;
import io.jmix.search.searching.SearchResult;
import io.jmix.ui.component.Button;
import io.jmix.ui.model.CollectionContainer;
import io.jmix.ui.screen.*;
import com.company.jmixpm.search.TaskSearchDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@UiController("TaskSearchDto.browse")
@UiDescriptor("task-search-dto-browse.xml")
@LookupComponent("taskSearchDtoesTable")
public class TaskSearchDtoBrowse extends StandardLookup<TaskSearchDto> {

    @Autowired
    private EntitySearcher entitySearcher;

    @Autowired
    private CollectionContainer<TaskSearchDto> taskSearchDtoesDc;

    @Autowired
    private DataManager dataManager;

    @Subscribe("searchBtn")
    public void onSearchBtnClick(Button.ClickEvent event) {
        SearchContext ctx = new SearchContext("task").setSize(20);
        SearchResult searchResult = entitySearcher.search(ctx);

        List<TaskSearchDto> dtos = searchResult.getAllEntries().stream()
                .map(entry -> {
                    TaskSearchDto dto = dataManager.create(TaskSearchDto.class);
                    dto.setId(UUID.randomUUID());
                    dto.setEntityName(entry.getEntityName());
                    dto.setInstanceName(entry.getInstanceName());
                    return dto;
                }).collect(Collectors.toList());

        taskSearchDtoesDc.setItems(dtos);

    }
}