package com.workshopngine.platform.staffmanagement.staff.interfaces.rest;

import com.workshopngine.platform.staffmanagement.staff.domain.model.queries.GetAllSkillCertificationsBySkillAreaIdAndMechanicIdQuery;
import com.workshopngine.platform.staffmanagement.staff.domain.services.MechanicCommandService;
import com.workshopngine.platform.staffmanagement.staff.domain.services.MechanicQueryService;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.CreateSkillCertificationResource;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.SkillCertificationResource;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.transforms.CreateSkillCertificationCommandFromResourceAssembler;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.transforms.SkillCertificationResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/mechanics/{mechanicId}/skill-areas/{skillAreaId}/skill-certifications", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Mechanics", description = "Mechanic Endpoints")
public class SkillCertificationController {
    private final MechanicCommandService mechanicCommandService;
    private final MechanicQueryService mechanicQueryService;

    @GetMapping
    @Operation(summary = "Get all skill certifications by mechanic ID and skill area ID", description = "Get all skill certifications by mechanic ID and skill area ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Skill certifications retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Skill certifications not found")
    })
    public ResponseEntity<SkillCertificationResource[]> getAllSkillCertificationsByMechanicIdAndSkillAreaId(
            @PathVariable Long mechanicId,
            @PathVariable Long skillAreaId
    ) {
        var query = new GetAllSkillCertificationsBySkillAreaIdAndMechanicIdQuery(mechanicId, skillAreaId);
        var skillCertifications = mechanicQueryService.handle(query);
        var skillCertificationResources = skillCertifications
                .stream()
                .map(SkillCertificationResourceFromEntityAssembler::toResourceFromEntity)
                .toArray(SkillCertificationResource[]::new);
        return new ResponseEntity<>(skillCertificationResources, HttpStatus.OK);
    }

    @PostMapping(consumes = MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create Skill Certification", description = "Create a new skill certification")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Skill Certification created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<SkillCertificationResource> createSkillCertification(
            @PathVariable Long mechanicId,
            @PathVariable Long skillAreaId,
            @RequestPart("resource") CreateSkillCertificationResource resource,
            @RequestPart("file") MultipartFile file
    ) {
        var command = CreateSkillCertificationCommandFromResourceAssembler.toCommandFromResource(mechanicId, skillAreaId, file, resource);
        var skillCertification = mechanicCommandService.handle(command);
        if (skillCertification.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        var skillCertificationResource = SkillCertificationResourceFromEntityAssembler.toResourceFromEntity(skillCertification.get());
        return new ResponseEntity<>(skillCertificationResource, HttpStatus.CREATED);
    }
}
