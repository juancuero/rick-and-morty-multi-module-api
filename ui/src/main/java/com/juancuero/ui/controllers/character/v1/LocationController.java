package com.juancuero.ui.controllers.location.v1;

import com.juancuero.domain.model.Location;
import com.juancuero.domain.use_case.location.*;
import com.juancuero.domain.use_case.location.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
@RequiredArgsConstructor
public class LocationController {
    private final LocationUseCase locationUseCase;

    @GetMapping()
    public ResponseEntity<Page<Location>> getAllLocations(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(locationUseCase.getAllLocations(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
        return locationUseCase.getLocationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        Location createdLocation = locationUseCase.createLocation(location);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLocation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location location) {
        Location updatedLocation = locationUseCase.updateLocation(id, location);
        return updatedLocation != null ? ResponseEntity.ok(updatedLocation) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationUseCase.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("sync")
    public ResponseEntity<String> syncLocation() {
        return ResponseEntity.status(HttpStatus.OK).body(locationUseCase.syncLocation());
    }
}