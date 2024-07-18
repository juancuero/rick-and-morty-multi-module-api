package com.juancuero.domain.driven_port.driven_jpa;

import com.juancuero.domain.model.Location;
import org.springframework.data.domain.*;

import java.util.*;


public interface LocationRepository {
    Page<Location> findAll(Pageable pageable);
    Optional<Location> findById(Long id);
    Location save(Location location);
    void deleteById(Long id);
    void saveAll(List<Location> locations);
}
