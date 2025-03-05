package com.workshopngine.platform.staffmanagement.staff.interfaces.rest;

import com.workshopngine.platform.staffmanagement.staff.domain.model.queries.GetAllMechanicsByWorkshopIdQuery;
import com.workshopngine.platform.staffmanagement.staff.domain.model.queries.GetMechanicByIdQuery;
import com.workshopngine.platform.staffmanagement.staff.domain.model.queries.IsAvailableMechanicByIdAndRequestedTimeQuery;
import com.workshopngine.platform.staffmanagement.staff.domain.services.MechanicCommandService;
import com.workshopngine.platform.staffmanagement.staff.domain.services.MechanicQueryService;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.CreateMechanicResource;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.MechanicAvailabilityResource;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.dto.MechanicResource;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.transforms.CreateMechanicCommandFromResourceAssembler;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.transforms.MechanicAvailabilityResourceFromEntityAssembler;
import com.workshopngine.platform.staffmanagement.staff.interfaces.rest.transforms.MechanicResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/mechanics",produces = APPLICATION_JSON_VALUE)
@Tag(name = "Mechanics", description = "Mechanic Endpoints")
public class MechanicController {
    private final MechanicCommandService mechanicCommandService;
    private final MechanicQueryService mechanicQueryService;

    @GetMapping
    @Operation(summary = "Get all mechanics by workshop ID", description = "Get all mechanics by workshop ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mechanics retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Mechanics not found")
    })
    public ResponseEntity<MechanicResource[]> getAllMechanicsByWorkshopId(@RequestParam String workshopId) {
        var query = new GetAllMechanicsByWorkshopIdQuery(workshopId);
        var mechanics = mechanicQueryService.handle(query);
        var mechanicResources = mechanics.stream()
                .map(MechanicResourceFromEntityAssembler::toResourceFromEntity)
                .toArray(MechanicResource[]::new);
        return new ResponseEntity<>(mechanicResources, HttpStatus.OK);
    }

    @GetMapping("/{mechanicId}")
    @Operation(summary = "Get mechanic by ID", description = "Get a mechanic by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mechanic found"),
            @ApiResponse(responseCode = "404", description = "Mechanic not found")
    })
    public ResponseEntity<MechanicResource> getMechanicById(@PathVariable String mechanicId) {
        var query = new GetMechanicByIdQuery(mechanicId);
        var mechanic = mechanicQueryService.handle(query);
        if (mechanic.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        var mechanicResource = MechanicResourceFromEntityAssembler.toResourceFromEntity(mechanic.get());
        return new ResponseEntity<>(mechanicResource, HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create Mechanic", description = "Create a new mechanic")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mechanic created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<MechanicResource> createMechanic(@RequestBody CreateMechanicResource resource) {
        var command = CreateMechanicCommandFromResourceAssembler.toCommandFromResource(resource);
        var mechanicId = mechanicCommandService.handle(command);
        if (mechanicId.isBlank()) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(getMechanicById(mechanicId).getBody(), HttpStatus.CREATED);
    }

    @GetMapping("/{mechanicId}/availability")
    @Operation(summary = "Get mechanic availability", description = "Get mechanic availability by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Mechanic availability retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Mechanic availability not found")
    })
    public ResponseEntity<MechanicAvailabilityResource> getMechanicAvailability(@PathVariable String mechanicId, @RequestParam LocalDateTime requestedTime) {
        var query = new IsAvailableMechanicByIdAndRequestedTimeQuery(mechanicId, requestedTime);
        var isAvailable = mechanicQueryService.handle(query);
        var mechanicAvailabilityResource = MechanicAvailabilityResourceFromEntityAssembler.toResourceFromEntity(mechanicId, isAvailable);
        return new ResponseEntity<>(mechanicAvailabilityResource, HttpStatus.OK);
    }
}
