package com.company.jmixpm;

import com.company.jmixpm.app.TaskService;
import com.company.jmixpm.entity.Project;
import com.company.jmixpm.entity.ProjectTask;
import com.company.jmixpm.entity.User;
import com.company.jmixpm.extensions.PostgreSqlExtension;
import io.jmix.core.DataManager;
import io.jmix.core.security.SecurityContextHelper;
import io.jmix.core.security.SystemAuthenticator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.TestPropertySource;

import java.io.IOException;
import java.time.LocalDateTime;

@SpringBootTest
@ExtendWith({PostgreSqlExtension.class})
@TestPropertySource("/app-test.properties")
//@ContextConfiguration(initializers = {TestContextInitializer.class})
class JmixPmApplicationTests {

	@Autowired
	private SystemAuthenticator systemAuthenticator;

	@Autowired
	private DataManager dataManager;

	@Autowired
	private TaskService taskService;

	@Autowired
	AuthenticationManager authenticationManager;

	@BeforeEach
	void login() {
		systemAuthenticator.begin();
	}

	@AfterEach
	void logout() {
		systemAuthenticator.end();
	}

	@Test
	@DisplayName("Integration test, checks Task Service least busy user search")
	void testTaskService_findLeastBusyUser() throws IOException {

		// create test data
		User user1 = dataManager.create(User.class);
		user1.setUsername("user1");
		user1.setPassword("{noop}user1");
		dataManager.save(user1);

		User user2 = dataManager.create(User.class);
		user2.setUsername("user2");

		dataManager.save(user2);

		Project project = dataManager.create(Project.class);
		project.setName("DB Integration test");
		project.setManager(user1);

		ProjectTask task1 = dataManager.create(ProjectTask.class);
		task1.setName("Write integration test");
		task1.setAssignee(user1);
		task1.setProject(project);
		task1.setStartDate(LocalDateTime.now());
		task1.setEstimatedEfforts(10);

		ProjectTask task2 = dataManager.create(ProjectTask.class);
		task2.setName("Write documentation");
		task2.setAssignee(user2);
		task2.setProject(project);
		task2.setStartDate(LocalDateTime.now());
		task2.setEstimatedEfforts(20);

		ProjectTask task3 = dataManager.create(ProjectTask.class);
		task3.setName("Create test data");
		task3.setAssignee(user2);
		task3.setProject(project);
		task3.setStartDate(LocalDateTime.now());
		task3.setEstimatedEfforts(20);

		dataManager.save(project, task1, task2, task3);

		Assertions.assertEquals(user1, taskService.findLeastBusyUser());

	}

}