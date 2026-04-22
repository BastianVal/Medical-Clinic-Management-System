package com.appointments.appointments.coordinator;

import com.appointments.appointments.coordinator.dto.CoordinatorRequest;
import com.appointments.appointments.coordinator.dto.CoordinatorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/coordinator")
public class CoordinatorController {

    private final CoordinatorService coordinatorService;

    public CoordinatorController(CoordinatorService coordinatorService) {
        this.coordinatorService = coordinatorService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('COORDINATOR')")
    public CoordinatorResponse findById(@PathVariable Integer id){
        return coordinatorService.findById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('COORDINATOR')")
    public List<CoordinatorResponse> findAll(){
        return coordinatorService.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasRole('COORDINATOR')")
    public CoordinatorResponse updateCoordinator(@PathVariable Integer id ,@RequestBody CoordinatorRequest dto){
        return coordinatorService.updateCoordinator(id, dto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('COORDINATOR')")
    public void deleteById(@PathVariable Integer id){
        coordinatorService.deleteById(id);
    }
}
