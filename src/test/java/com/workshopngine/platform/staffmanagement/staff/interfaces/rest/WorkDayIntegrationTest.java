package com.workshopngine.platform.staffmanagement.staff.interfaces.rest;

import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.CreateMechanicResource;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.CreateWorkDayResource;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.MechanicResource;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.WorkDayResource;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalTime;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WorkDayIntegrationTest {
    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0");

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static final String MECHANIC_RESOURCE = "/mechanics";
    private static final String VALID_WORKSHOP_ID = "0f89d3f2-0d95-4b46-bd9f-c73cc7ef5955";
    private static final String WORK_DAY_RESOURCE = "/mechanics/{mechanicId}/work-days";

    @Test
    void TestCreateWorkDay_ValidWorkDayResource_ShouldPass() {
        // Given
        CreateMechanicResource createMechanicResource = buildCreateMechanicResource();
        ResponseEntity<MechanicResource> createMechanicResponse = createMechanicResponse(createMechanicResource);

        Assertions.assertThat(createMechanicResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(createMechanicResponse.getBody()).isNotNull();
        Assertions.assertThat(createMechanicResponse.getBody().id()).isNotBlank();
        String mechanicId = createMechanicResponse.getBody().id();

        CreateWorkDayResource createWorkDayResource = buildCreateWorkDayResource();

        // When
        ResponseEntity<WorkDayResource> createWorkDayResponse = createWorkDayResponse(createWorkDayResource, mechanicId);

        // Then
        Assertions.assertThat(createWorkDayResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(createWorkDayResponse.getBody()).isNotNull();
        Assertions.assertThat(createWorkDayResponse.getBody().day()).isEqualTo(createWorkDayResource.day());
        Assertions.assertThat(createWorkDayResponse.getBody().startAt()).isEqualTo(createWorkDayResource.startAt());
        Assertions.assertThat(createWorkDayResponse.getBody().endAt()).isEqualTo(createWorkDayResource.endAt());
    }

    private CreateMechanicResource buildCreateMechanicResource() {
        return CreateMechanicResource.builder()
                .workshopId(VALID_WORKSHOP_ID)
                .build();
    }

    private ResponseEntity<MechanicResource> createMechanicResponse(CreateMechanicResource createMechanicResource) {
        return testRestTemplate.exchange(
                MECHANIC_RESOURCE,
                HttpMethod.POST,
                new HttpEntity<>(createMechanicResource),
                MechanicResource.class
        );
    }

    private CreateWorkDayResource buildCreateWorkDayResource() {
        return CreateWorkDayResource.builder()
                .day("MONDAY")
                .startAt(LocalTime.parse("09:00"))
                .endAt(LocalTime.parse("17:00"))
                .build();
    }

    private ResponseEntity<WorkDayResource> createWorkDayResponse(CreateWorkDayResource createWorkDayResource, String mechanicId) {
        return testRestTemplate.exchange(
                WORK_DAY_RESOURCE,
                HttpMethod.POST,
                new HttpEntity<>(createWorkDayResource),
                WorkDayResource.class,
                mechanicId
        );
    }
}