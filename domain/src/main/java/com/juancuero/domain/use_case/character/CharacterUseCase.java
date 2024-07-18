package com.juancuero.domain.use_case.character;

import java.util.List;
import java.util.Optional;
import com.juancuero.domain.model.Character;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CharacterUseCase {
    Page<Character> getAllCharacters(Pageable pageable);
    Optional<Character> getCharacterById(Long id);
    Character createCharacter(Character character);
    Character updateCharacter(Long id, Character character);
    void deleteCharacter(Long id);
    String syncCharacter();
}