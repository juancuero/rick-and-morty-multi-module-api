package com.juancuero.domain.driven_port.feign_client;

import java.util.*;
import com.juancuero.domain.model.Character;

public interface RickAndMortyClientPort {
    void syncCharacters();
    void syncLocations();
}