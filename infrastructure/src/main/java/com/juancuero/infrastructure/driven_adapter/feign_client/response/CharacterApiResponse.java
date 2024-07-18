package com.juancuero.infrastructure.driven_adapter.feign_client.response;

import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
public class CharacterApiResponse {
    private Info info;
    private List<CharacterResponse> results;

    @Data
    @AllArgsConstructor
    public static class Info {
        private int count;
        private int pages;
        private String next;
        private String prev;

    }

    @Data
    @AllArgsConstructor
    public static class CharacterResponse {
        private Long id;
        private String name;
        private String species;
        private String gender;

    }
}