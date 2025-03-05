package com.workshopngine.platform.staffmanagement;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SpringBootTest
class StaffManagementApplicationTests {
	@Container
	@ServiceConnection
	static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0");

	@Test
	void contextLoads() {
	}

}
