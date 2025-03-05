package com.workshopngine.platform.staffmanagement.staff.interfaces.rest;

import com.workshopngine.platform.staffmanagement.staff.domain.model.queries.GetAllSkillAreasByMechanicIdQuery;
import com.workshopngine.platform.staffmanagement.staff.domain.services.MechanicCommandService;
import com.workshopngine.platform.staffmanagement.staff.domain.services.MechanicQueryService;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.CreateSkillAreaResource;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.SkillAreaResource;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.transforms.CreateSkillAreaCommandFromResourceAssembler;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.transforms.SkillAreaResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/mechanics/{mechanicId}/skill-areas", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Mechanics", description = "Mechanic Endpoints")
public class SkillAreaController {
    private final MechanicCommandService mechanicCommandService;
    private final MechanicQueryService mechanicQueryService;

    @GetMapping
    @Operation(summary = "Get all skill areas by mechanic ID", description = "Get all skill areas by mechanic ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Skill areas retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Skill areas not found")
    })
    public ResponseEntity<SkillAreaResource[]> getAllSkillAreasByMechanicId(@PathVariable String mechanicId) {
        var query = new GetAllSkillAreasByMechanicIdQuery(mechanicId);
        var skillAreas = mechanicQueryService.handle(query);
        var skillAreaResources = skillAreas
                .stream()
                .map(SkillAreaResourceFromEntityAssembler::toResourceFromEntity)
                .toArray(SkillAreaResource[]::new);
        return new ResponseEntity<>(skillAreaResources, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create Skill Area", description = "Create a new skill area")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Skill Area created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<SkillAreaResource> createSkillArea(
            @PathVariable String mechanicId,
            @RequestBody CreateSkillAreaResource resource
    ) {
        var command = CreateSkillAreaCommandFromResourceAssembler.toCommandFromResource(mechanicId, resource);
        var skillArea = mechanicCommandService.handle(command);
        if (skillArea.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        var skillAreaResource = SkillAreaResourceFromEntityAssembler.toResourceFromEntity(skillArea.get());
        return new ResponseEntity<>(skillAreaResource, HttpStatus.CREATED);
    }
}
