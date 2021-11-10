package com.company.jmixpm.screen.projecttask

import com.company.jmixpm.extensions.PostgreSqlExtension
import io.jmix.core.DataManager
import io.jmix.core.FluentValuesLoader
import io.jmix.core.security.SystemAuthenticator
import io.jmix.ui.ScreenBuilders
import io.jmix.ui.builder.ScreenBuilder
import io.jmix.ui.testassist.spec.UiTestAssistSpecification
import org.junit.jupiter.api.extension.ExtendWith
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@SpringBootTest
@ExtendWith([PostgreSqlExtension.class])
@TestPropertySource("/app-test.properties")
class taskEditTest extends UiTestAssistSpecification {

    @Autowired
    ScreenBuilders screenBuilders;

    @SpringBean
    DataManager dataManager = Stub();

    @Autowired
    SystemAuthenticator systemAuthenticator;

    @Override
    protected void setupAuthentication() {
        systemAuthenticator.begin()
    }

    @Override
    protected void cleanupAuthentication() {
        systemAuthenticator.end()
    }


    def "Check that screen sets the least busy user"() {
        given: "Create test data"
        def user1 = metadata.create(User)
        user1.username = "user1"
        def user2 = metadata.create(User)
        user2.username = "user2"

        def entity1 = metadata.create(KeyValueEntity)
        entity1.setValue("user", user1)
        entity1.setValue("tasks", 1L)
        def entity2 = metadata.create(KeyValueEntity)
        entity2.setValue("user", user2)
        entity2.setValue("tasks", 3L)
        def entities = [entity1, entity2]

        and: "Stub for data data manager"
        FluentValuesLoader valueLoader = Stub(FluentValuesLoader);
        valueLoader.properties("user", "tasks") >> valueLoader
        valueLoader.list() >> entities
        dataManager.loadValues(_ as String) >> valueLoader

        and: "Open Task editor"
        def mainScreen = vaadinUi.screens.create(MainScreen, OpenMode.ROOT)
        vaadinUi.screens.show(mainScreen)
        def taskEdit = screenBuilders.editor(ProjectTask, mainScreen)
                .withScreenClass(ProjectTaskEdit)
                .newEntity()
                .show()

        when: "Default assignee is set"
        def assignee = taskEdit.getEditedEntity().assignee

        then: "Check that it is the first user"
        assignee == user1
    }
}