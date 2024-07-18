package com.juancuero.infrastructure.driven_adapter.feign_client;

import com.juancuero.infrastructure.driven_adapter.feign_client.response.*;

        import org.springframework.cloud.openfeign.FeignClient;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "rickAndMortyClient", url = "${rickandmortyapi}")
public interface RickAndMortyFeignClient {

    @GetMapping(
            value = "/character",
            consumes = "application/json",
            produces = "application/json"
    )
    CharacterApiResponse getCharacters(@RequestParam(value = "page", required = false) Integer page);

    @GetMapping(
            value = "/location",
            consumes = "application/json",
            produces = "application/json"
    )
    LocationApiResponse getLocations(@RequestParam(value = "page", required = false) Integer page);
}