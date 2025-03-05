package com.workshopngine.platform.staffmanagement.staff.interfaces.rest;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.CreateMechanicResource;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.CreateSkillAreaResource;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.MechanicResource;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.SkillAreaResource;
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
class SkillAreaIntegrationTest {
    @Container
    @ServiceConnection
    static MySQLContainer<?> mySQLContainer = new MySQLContainer<>("mysql:8.0");

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static final String MECHANIC_RESOURCE = "/mechanics";
    private static final String VALID_WORKSHOP_ID = "0f89d3f2-0d95-4b46-bd9f-c73cc7ef5955";
    private static final String SKILL_AREA_RESOURCE = "/mechanics/{mechanicId}/skill-areas";

    @Test
    void TestCreateSkillArea_ValidSkillAreaResource_ShouldPass() {
        // Given
        CreateMechanicResource createMechanicResource = buildCreateMechanicResource();
        ResponseEntity<MechanicResource> createMechanicResponse = createMechanicResponse(createMechanicResource);

        Assertions.assertThat(createMechanicResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(createMechanicResponse.getBody()).isNotNull();
        Assertions.assertThat(createMechanicResponse.getBody().id()).isNotBlank();
        String mechanicId = createMechanicResponse.getBody().id();

        CreateSkillAreaResource createSkillAreaResource = buildCreateSkillAreaResource();

        // When
        ResponseEntity<SkillAreaResource> createSkillAreaResponse = createSkillAreaResponse(createSkillAreaResource, mechanicId);

        // Then
        Assertions.assertThat(createSkillAreaResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(createSkillAreaResponse.getBody()).isNotNull();
        Assertions.assertThat(createSkillAreaResponse.getBody().area()).isEqualTo(createSkillAreaResource.area());
        Assertions.assertThat(createSkillAreaResponse.getBody().level()).isEqualTo(createSkillAreaResource.level());
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

    private CreateSkillAreaResource buildCreateSkillAreaResource() {
        return CreateSkillAreaResource.builder()
                .area("Change oil")
                .level("JUNIOR")
                .build();
    }

    private ResponseEntity<SkillAreaResource> createSkillAreaResponse(CreateSkillAreaResource createSkillAreaResource, String mechanicId) {
        return testRestTemplate.exchange(
                SKILL_AREA_RESOURCE,
                HttpMethod.POST,
                new HttpEntity<>(createSkillAreaResource),
                SkillAreaResource.class,
                mechanicId
        );
    }
}