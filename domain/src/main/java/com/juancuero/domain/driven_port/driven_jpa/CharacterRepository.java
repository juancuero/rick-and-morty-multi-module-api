package com.juancuero.domain.driven_port.driven_jpa;

import java.util.List;
import java.util.Optional;
import com.juancuero.domain.model.Character;
import org.springframework.data.domain.*;


public interface CharacterRepository {
    Page<Character> findAll(Pageable pageable);
    Optional<Character> findById(Long id);
    Character save(Character character);
    void deleteById(Long id);
    void saveAll(List<Character> characters);
}
