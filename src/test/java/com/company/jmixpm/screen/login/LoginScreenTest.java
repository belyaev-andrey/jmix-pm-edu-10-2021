package com.company.jmixpm.screen.login;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;
import static io.jmix.masquerade.Conditions.*;
import static io.jmix.masquerade.Selectors.$j;

public class LoginScreenTest {

    @Test
    @DisplayName("Check login screen")
    void login() {
        open("/");
        LoginScreenUi loginScreen = $j(LoginScreenUi.class);
        loginScreen.getUsernameField()
                .shouldBe(EDITABLE)
                .shouldBe(ENABLED);

        loginScreen.getWelcomeLabelTest()
                .shouldBe(VISIBLE);

        loginScreen.getUsernameField().setValue("admin");
        loginScreen.getPasswordField().setValue("admin");

        loginScreen.getLoginButton()
                .shouldBe(VISIBLE)
                .shouldBe(ENABLED)
                .shouldHave(caption("Submit"));

        $j("loginForm").shouldBe(VISIBLE);

        loginScreen.getLoginButton().click();
    }

}