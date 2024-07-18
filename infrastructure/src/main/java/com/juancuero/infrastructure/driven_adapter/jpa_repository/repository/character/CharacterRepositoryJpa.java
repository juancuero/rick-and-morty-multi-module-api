package com.juancuero.infrastructure.driven_adapter.jpa_repository.repository.character;

import com.juancuero.infrastructure.driven_adapter.jpa_repository.entity.CharacterEntity;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface CharacterRepositoryJpa extends JpaRepository<CharacterEntity, Long> {
    Page<CharacterEntity> findAll(Pageable pageable);
    boolean existsByApiId(Long apiId);
}
