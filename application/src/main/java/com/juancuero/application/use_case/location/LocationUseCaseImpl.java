package com.juancuero.application.use_case.location;

import com.juancuero.domain.driven_port.driven_jpa.*;
import com.juancuero.domain.driven_port.feign_client.*;
import com.juancuero.domain.model.Location;
import com.juancuero.domain.use_case.location.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class LocationUseCaseImpl implements LocationUseCase {

    private final LocationRepository locationRepository;
    private final RickAndMortyClientPort rickAndMortyClientPort;

    public LocationUseCaseImpl(LocationRepository locationRepository, RickAndMortyClientPort rickAndMortyClientPort) {
        this.locationRepository = locationRepository;
        this.rickAndMortyClientPort = rickAndMortyClientPort;
    }

    @Override
    public Page<Location> getAllLocations(Pageable pageable) {
        return locationRepository.findAll(pageable);
    }


    @Override
    public Optional<Location> getLocationById(Long id) {
        return locationRepository.findById(id);
    }

    @Override
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location updateLocation(Long id, Location location) {
        return locationRepository.findById(id)
                .map(existingLocation -> {
                    existingLocation.setName(location.getName());
                    existingLocation.setType(location.getType());
                    existingLocation.setDimension(location.getDimension());
                    return locationRepository.save(existingLocation);
                })
                .orElse(null);
    }

    @Override
    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

    @Override
    public String syncLocation() {
           rickAndMortyClientPort.syncLocations();
           return "Se ha enviado la sincronización para locations";
    }
}