package com.juancuero.application.use_case.character;

import com.juancuero.domain.driven_port.driven_jpa.CharacterRepository;
import com.juancuero.domain.driven_port.feign_client.*;
import com.juancuero.domain.model.Character;
import com.juancuero.domain.use_case.character.CharacterUseCase;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CharacterUseCaseImpl implements CharacterUseCase {

    private final CharacterRepository characterRepository;
    private final RickAndMortyClientPort rickAndMortyClientPort;

    public CharacterUseCaseImpl(CharacterRepository characterRepository, RickAndMortyClientPort rickAndMortyClientPort) {
        this.characterRepository = characterRepository;
        this.rickAndMortyClientPort = rickAndMortyClientPort;
    }

    @Override
    public Page<Character> getAllCharacters(Pageable pageable) {
        return characterRepository.findAll(pageable);
    }


    @Override
    public Optional<Character> getCharacterById(Long id) {
        return characterRepository.findById(id);
    }

    @Override
    public Character createCharacter(Character character) {
        return characterRepository.save(character);
    }

    @Override
    public Character updateCharacter(Long id, Character character) {
        return characterRepository.findById(id)
                .map(existingCharacter -> {
                    existingCharacter.setName(character.getName());
                    existingCharacter.setGender(character.getGender());
                    existingCharacter.setSpecies(character.getSpecies());
                    return characterRepository.save(existingCharacter);
                })
                .orElse(null);
    }

    @Override
    public void deleteCharacter(Long id) {
        characterRepository.deleteById(id);
    }

    @Override
    public String syncCharacter() {
           rickAndMortyClientPort.syncCharacters();
           return "Se ha enviado la sincronización para characters";
    }
}