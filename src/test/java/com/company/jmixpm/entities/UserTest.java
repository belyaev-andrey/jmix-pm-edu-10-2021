package com.company.jmixpm.entities;

import com.company.jmixpm.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    @DisplayName("Test User's Display name with all properties filled")
    void testCorrectDisplayName_hasFirstName_HasLastName() {
        User user = new User();
        user.setFirstName("Developer");
        user.setLastName("One");
        user.setUsername("dev1");

        assertEquals("Developer One [dev1]", user.getDisplayName());

    }


}