package com.juancuero.domain.use_case.location;

import com.juancuero.domain.model.Location;
import org.springframework.data.domain.*;

import java.util.*;

public interface LocationUseCase {
    Page<Location> getAllLocations(Pageable pageable);
    Optional<Location> getLocationById(Long id);
    Location createLocation(Location location);
    Location updateLocation(Long id, Location location);
    void deleteLocation(Long id);
    String syncLocation();
}