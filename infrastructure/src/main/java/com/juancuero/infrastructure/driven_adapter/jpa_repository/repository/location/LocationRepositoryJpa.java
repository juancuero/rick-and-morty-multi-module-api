package com.juancuero.infrastructure.driven_adapter.jpa_repository.repository.location;

import com.juancuero.infrastructure.driven_adapter.jpa_repository.entity.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface LocationRepositoryJpa extends JpaRepository<LocationEntity, Long> {
    Page<LocationEntity> findAll(Pageable pageable);
    boolean existsByApiId(Long apiId);
}
