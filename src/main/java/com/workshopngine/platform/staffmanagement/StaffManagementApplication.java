package com.workshopngine.platform.staffmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients
public class StaffManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(StaffManagementApplication.class, args);
	}

}
