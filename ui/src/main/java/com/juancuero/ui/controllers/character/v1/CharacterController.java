package com.juancuero.ui.controllers.character.v1;

import com.juancuero.domain.model.Character;
import com.juancuero.domain.use_case.character.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.web.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {
    private final CharacterUseCase characterUseCase;

    @GetMapping()
    public ResponseEntity<Page<Character>> getAllCharacters(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(characterUseCase.getAllCharacters(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Character> getCharacterById(@PathVariable Long id) {
        return characterUseCase.getCharacterById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Character> createCharacter(@RequestBody Character character) {
        Character createdCharacter = characterUseCase.createCharacter(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCharacter);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Character> updateCharacter(@PathVariable Long id, @RequestBody Character character) {
        Character updatedCharacter = characterUseCase.updateCharacter(id, character);
        return updatedCharacter != null ? ResponseEntity.ok(updatedCharacter) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacter(@PathVariable Long id) {
        characterUseCase.deleteCharacter(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("sync")
    public ResponseEntity<String> syncCharacter() {
        return ResponseEntity.status(HttpStatus.OK).body(characterUseCase.syncCharacter());
    }
}