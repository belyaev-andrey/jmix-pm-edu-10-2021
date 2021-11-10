package com.company.jmixpm.screen.project;

import com.company.jmixpm.JmixPmApplication;
import com.company.jmixpm.screen.login.LoginScreenUi;
import com.company.jmixpm.screen.main.MainScreenUi;
import com.company.jmixpm.screen.user.UserBrowseUi;
import io.jmix.masquerade.Selectors;
import io.jmix.masquerade.component.HasActions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codeborne.selenide.Selenide.open;
import static io.jmix.masquerade.Conditions.ENABLED;
import static io.jmix.masquerade.Conditions.VISIBLE;
import static io.jmix.masquerade.Selectors.$j;

//@ExtendWith(ChromeExtension.class)
//Doesn't work with Spock tests, @see com.vaadin.spring.server.SpringUIProvider.detectUIs:113
//@SpringBootTest(classes = JmixPmApplication.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
//        properties = {"jmix.ui.testMode=true"})
public class ProjectScreensTest {

    @Test
    void testProjectCreation() {
        open("/");

        /* Authorization */
        // obtain UI object of LoginScreen
        LoginScreenUi loginScreen = $j(LoginScreenUi.class);

        // setting values
        loginScreen.getUsernameField()
                .shouldBe(VISIBLE, ENABLED)
                .setValue("admin");
        loginScreen.getPasswordField()
                .shouldBe(VISIBLE, ENABLED)
                .setValue("admin");

        // login
        loginScreen.getLoginButton()
                .shouldBe(VISIBLE)
                .click();

        /* Project creation */
        ProjectBrowseUi projectBrowse = $j(MainScreenUi.class).openProjectBrowse();

        // fill form fields
        ProjectEditUi projectEdit = projectBrowse.create();
        projectEdit.getNameField()
                .shouldBe(VISIBLE, ENABLED)
                .setValue("UI testing");
        projectEdit.getStartDateField()
                .shouldBe(VISIBLE, ENABLED)
                .setDateValue("03/11/2021");
        projectEdit.getEndDateField()
                .shouldBe(VISIBLE, ENABLED)
                .setDateValue("04/11/2021");
        UserBrowseUi userBrowse = projectEdit.getManagerField()
                .shouldBe(VISIBLE, ENABLED)
                .triggerAction(UserBrowseUi.class, new HasActions.Action("entityLookup"));
        userBrowse.selectUser("dev1");

        // save project
        projectEdit.commitAndClose();

        // check the display of the new project in the table
        projectBrowse.getProjectsTable()
                .shouldBe(VISIBLE, ENABLED)
                .getRow(Selectors.byText("UI testing"))
                .shouldBe(VISIBLE);
    }

}