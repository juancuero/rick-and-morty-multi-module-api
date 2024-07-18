package com.juancuero.domain.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
    private Long id;
    private String name;
    private String type;
    private String dimension;
    private Long apiId;
}