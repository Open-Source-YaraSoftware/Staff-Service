package com.workshopngine.platform.staffmanagement.staff.interfaces.rest;

import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.CreateMechanicResource;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.MechanicResource;
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

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MechanicIntegrationTest {
    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0");

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static final String MECHANIC_RESOURCE = "/mechanics";
    private static final String VALID_WORKSHOP_ID = "0f89d3f2-0d95-4b46-bd9f-c73cc7ef5955";

    @Test
    void TestCreateMechanic_ValidMechanicResource_ShouldPass() {
        // Given
        CreateMechanicResource createMechanicResource = buildCreateMechanicResource();

        // When
        ResponseEntity<MechanicResource> createMechanicResponse = createMechanicResponse(createMechanicResource);

        // Then
        Assertions.assertThat(createMechanicResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(createMechanicResponse.getBody()).isNotNull();
        Assertions.assertThat(createMechanicResponse.getBody().id()).isNotBlank();
        Assertions.assertThat(createMechanicResponse.getBody().workshopId()).isEqualTo(createMechanicResource.workshopId());
        Assertions.assertThat(createMechanicResponse.getBody().availabilityStatus()).isEqualTo("AVAILABLE");
        Assertions.assertThat(createMechanicResponse.getBody().operationalStatus()).isEqualTo("IDLE");
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
}