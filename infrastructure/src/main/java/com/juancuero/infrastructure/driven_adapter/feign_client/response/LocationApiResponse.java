package com.juancuero.infrastructure.driven_adapter.feign_client.response;

import lombok.*;

import java.util.*;

@Data
@AllArgsConstructor
public class LocationApiResponse {
    private Info info;
    private List<LocationResponse> results;

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
    public static class LocationResponse {
        private Long id;
        private String name;
        private String type;
        private String dimension;

    }
}