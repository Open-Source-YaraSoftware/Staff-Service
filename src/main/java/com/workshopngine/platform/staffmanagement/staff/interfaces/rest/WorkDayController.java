package com.workshopngine.platform.staffmanagement.staff.interfaces.rest;

import com.workshopngine.platform.staffmanagement.staff.domain.model.queries.GetAllWorkDaysByMechanicIdQuery;
import com.workshopngine.platform.staffmanagement.staff.domain.services.MechanicCommandService;
import com.workshopngine.platform.staffmanagement.staff.domain.services.MechanicQueryService;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.CreateWorkDayResource;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.WorkDayResource;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.transforms.CreateWorkDayCommandFromResourceAssembler;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.transforms.WorkDayResourceFromEntityAssembler;
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
@RequestMapping(value = "/mechanics/{mechanicId}/work-days", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Mechanics", description = "Mechanic Endpoints")
public class WorkDayController {
    private final MechanicCommandService mechanicCommandService;
    private final MechanicQueryService mechanicQueryService;

    @GetMapping
    @Operation(summary = "Get all work days by mechanic ID", description = "Get all work days by mechanic ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Work days retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Work days not found")
    })
    public ResponseEntity<WorkDayResource[]> getAllWorkDaysByMechanicId(@PathVariable Long mechanicId) {
        var query = new GetAllWorkDaysByMechanicIdQuery(mechanicId);
        var workDays = mechanicQueryService.handle(query);
        var workDayResources = workDays
                .stream()
                .map(WorkDayResourceFromEntityAssembler::toResourceFromEntity)
                .toArray(WorkDayResource[]::new);
        return new ResponseEntity<>(workDayResources, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create Work Day", description = "Create a new work day")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Work Day created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<WorkDayResource> createWorkDay(
            @PathVariable Long mechanicId,
            @RequestBody CreateWorkDayResource resource
    ) {
        var command = CreateWorkDayCommandFromResourceAssembler.toCommandFromResource(mechanicId, resource);
        var workDay = mechanicCommandService.handle(command);
        if (workDay.isEmpty()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        var workDayResource = WorkDayResourceFromEntityAssembler.toResourceFromEntity(workDay.get());
        return new ResponseEntity<>(workDayResource, HttpStatus.CREATED);
    }
}
