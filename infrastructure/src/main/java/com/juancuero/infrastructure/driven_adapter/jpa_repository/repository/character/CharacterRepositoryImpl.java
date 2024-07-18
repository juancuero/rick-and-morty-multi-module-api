package com.juancuero.infrastructure.driven_adapter.jpa_repository.repository.character;

import com.juancuero.domain.model.Character;
import com.juancuero.domain.driven_port.driven_jpa.CharacterRepository;
import com.juancuero.infrastructure.driven_adapter.jpa_repository.entity.CharacterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CharacterRepositoryImpl implements CharacterRepository {

    private final CharacterRepositoryJpa characterRepositoryJpa;

    public CharacterRepositoryImpl(CharacterRepositoryJpa characterRepositoryJpa) {
        this.characterRepositoryJpa = characterRepositoryJpa;
    }

    @Override
    public Page<Character> findAll(Pageable pageable) {
        return characterRepositoryJpa.findAll(pageable)
                .map(this::mapToCharacter);
    }

    private Character mapToCharacter(CharacterEntity entity) {
        return new Character(entity.getId(), entity.getName(), entity.getSpecies(), entity.getGender(), entity.getApiId());
    }

    @Override
    public Optional<Character> findById(Long id) {
        return characterRepositoryJpa.findById(id)
                .map(this::mapToCharacter);
    }

    @Override
    public Character save(Character character) {
        CharacterEntity entity = new CharacterEntity();
        entity.setId(character.getId());
        entity.setName(character.getName());
        entity.setSpecies(character.getSpecies());
        entity.setGender(character.getGender());
        entity.setApiId(character.getApiId());

        CharacterEntity savedEntity = characterRepositoryJpa.save(entity);
        return new Character(savedEntity.getId(), savedEntity.getName(), savedEntity.getSpecies(), savedEntity.getGender(), savedEntity.getApiId());
    }

    @Override
    public void deleteById(Long id) {
        characterRepositoryJpa.deleteById(id);
    }

    @Override
    public void saveAll(List<Character> characters) {
        List<CharacterEntity> entities = characters.stream()
                .filter(character -> !characterRepositoryJpa.existsByApiId(character.getApiId()))
                .map(character -> {
                    CharacterEntity entity = new CharacterEntity();
                    entity.setName(character.getName());
                    entity.setSpecies(character.getSpecies());
                    entity.setGender(character.getGender());
                    entity.setApiId(character.getApiId());
                    return entity;
                })
                .collect(Collectors.toList());

        characterRepositoryJpa.saveAll(entities);
    }
}
