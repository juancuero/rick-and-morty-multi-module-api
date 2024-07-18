package com.juancuero.infrastructure.driven_adapter.feign_client;

import com.juancuero.domain.driven_port.driven_jpa.*;
import com.juancuero.domain.driven_port.feign_client.*;
import com.juancuero.domain.model.*;
import com.juancuero.domain.model.Character;
import com.juancuero.infrastructure.driven_adapter.feign_client.response.*;

import java.util.*;

import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

@Component
public class RickAndMortyClientImpl implements RickAndMortyClientPort {

    private final RickAndMortyFeignClient rickAndMortyFeignClient;

    private final CharacterRepository characterRepository;
    private final LocationRepository locationRepository;


    public RickAndMortyClientImpl(RickAndMortyFeignClient rickAndMortyFeignClient,CharacterRepository characterRepository,LocationRepository locationRepository) {
        this.rickAndMortyFeignClient = rickAndMortyFeignClient;
        this.characterRepository = characterRepository;
        this.locationRepository = locationRepository;
    }
    @Override
    @Async
    public void syncCharacters() {

        int pageInitial = 1;

        CharacterApiResponse response;

        do {
            response = rickAndMortyFeignClient.getCharacters(pageInitial);
            saveCharacters(response.getResults());
            pageInitial++;
        } while (response.getInfo().getNext() != null);

    }

    @Override
    @Async
    public void syncLocations() {
        int pageInitial = 1;

        LocationApiResponse response;

        do {
            response = rickAndMortyFeignClient.getLocations(pageInitial);
            saveLocations(response.getResults());
            pageInitial++;
        } while (response.getInfo().getNext() != null);
    }

    private void saveCharacters(List<CharacterApiResponse.CharacterResponse> characterResponses) {
        List<Character> domainCharacters = new ArrayList<>();
        for (CharacterApiResponse.CharacterResponse characterResponse : characterResponses) {
            Character character = new Character();
            character.setName(characterResponse.getName());
            character.setSpecies(characterResponse.getSpecies());
            character.setGender(characterResponse.getGender());
            character.setApiId(characterResponse.getId());
            domainCharacters.add(character);
        }
        characterRepository.saveAll(domainCharacters);
    }

    private void saveLocations(List<LocationApiResponse.LocationResponse>  locationResponses) {
        List<Location> domainLocations = new ArrayList<>();
        for (LocationApiResponse.LocationResponse characterResponse :  locationResponses) {
            Location location = new Location();
            location.setName(characterResponse.getName());
            location.setDimension(characterResponse.getDimension());
            location.setType(characterResponse.getType());
            location.setApiId(characterResponse.getId());
            domainLocations.add(location);
        }
        locationRepository.saveAll(domainLocations);
    }
}
