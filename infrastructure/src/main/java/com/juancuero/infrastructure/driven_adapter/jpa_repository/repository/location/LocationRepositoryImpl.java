package com.juancuero.infrastructure.driven_adapter.jpa_repository.repository.location;

import com.juancuero.domain.driven_port.driven_jpa.*;
import com.juancuero.domain.model.Location;
import com.juancuero.infrastructure.driven_adapter.jpa_repository.entity.*;
import com.juancuero.infrastructure.driven_adapter.jpa_repository.repository.location.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Component
public class LocationRepositoryImpl implements LocationRepository {

    private final LocationRepositoryJpa locationRepositoryJpa;

    public LocationRepositoryImpl(LocationRepositoryJpa locationRepositoryJpa) {
        this.locationRepositoryJpa = locationRepositoryJpa;
    }

    @Override
    public Page<Location> findAll(Pageable pageable) {
        return locationRepositoryJpa.findAll(pageable)
                .map(this::mapToLocation);
    }

    private Location mapToLocation(LocationEntity entity) {
        return new Location(entity.getId(), entity.getName(), entity.getType(), entity.getDimension(), entity.getApiId());
    }

    @Override
    public Optional<Location> findById(Long id) {
        return locationRepositoryJpa.findById(id)
                .map(this::mapToLocation);
    }

    @Override
    public Location save(Location location) {
        LocationEntity entity = new LocationEntity();
        entity.setId(location.getId());
        entity.setName(location.getName());
        entity.setType(location.getType());
        entity.setDimension(location.getDimension());
        entity.setApiId(location.getApiId());

        LocationEntity savedEntity = locationRepositoryJpa.save(entity);
        return new Location(savedEntity.getId(), savedEntity.getName(), savedEntity.getType(), savedEntity.getDimension(), savedEntity.getApiId());
    }

    @Override
    public void deleteById(Long id) {
        locationRepositoryJpa.deleteById(id);
    }

    @Override
    public void saveAll(List<Location> locations) {
        List<LocationEntity> entities = locations.stream()
                .filter(location -> !locationRepositoryJpa.existsByApiId(location.getApiId()))
                .map(location -> {
                    LocationEntity entity = new LocationEntity();
                    entity.setName(location.getName());
                    entity.setType(location.getType());
                    entity.setDimension(location.getDimension());
                    entity.setApiId(location.getApiId());
                    return entity;
                })
                .collect(Collectors.toList());

        locationRepositoryJpa.saveAll(entities);
    }
}
