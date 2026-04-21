package com.appointments.appointments.coordinator;

import com.appointments.appointments.appUser.AppUser;
import com.appointments.appointments.appUser.AppUserService;
import com.appointments.appointments.coordinator.dto.CoordinatorRequest;
import com.appointments.appointments.coordinator.dto.CoordinatorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CoordinatorService {
    private final CoordinatorMapper  coordinatorMapper;
    private final CoordinatorRepository coordinatorRepository;
    private final AppUserService appUserService;

    public CoordinatorService(CoordinatorMapper coordinatorMapper, CoordinatorRepository coordinatorRepository, AppUserService appUserService) {
        this.coordinatorMapper = coordinatorMapper;
        this.coordinatorRepository = coordinatorRepository;
        this.appUserService = appUserService;
    }

    // =========================================================================
    // METHODS FOR THE CONTROLLERS (Retrieves DTOs)
    // =========================================================================

    public CoordinatorResponse findById(Integer id){
        Coordinator coordinator = coordinatorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Coordinator Not Found"));

        return coordinatorMapper.toCoordinatorResponse(coordinator);
    }

    public List<CoordinatorResponse> findAll(){
        return  coordinatorRepository.findAll()
                .stream()
                .map(coordinatorMapper::toCoordinatorResponse)
                .toList();
    }

    public CoordinatorResponse updateCoordinator(Integer id ,CoordinatorRequest dto){
        AppUser appUser = appUserService.findAppUserByIdEntity(dto.appUserId());

        Coordinator coordinator = coordinatorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Coordinator Not Found"));

        coordinator.setName(dto.name());

        coordinator = coordinatorRepository.save(coordinator);

        return coordinatorMapper.toCoordinatorResponse(coordinator);
    }

    public void deleteById(Integer id){
        if(!coordinatorRepository.existsById(id)) throw new EntityNotFoundException("Coordinator Not Found");

        coordinatorRepository.deleteById(id);
    }

    // =========================================================================
    // METHODS FOR THE SERVICES (Retrieves Entities)
    // =========================================================================

    public Coordinator findByIdEntity(Integer id){
        return coordinatorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Coordinator Not Found"));
    }

    public Coordinator createCoordinatorEntity(Coordinator coordinator){
        return coordinatorRepository.save(coordinator);
    }


}
