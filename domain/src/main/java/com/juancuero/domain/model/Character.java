package com.juancuero.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Character {
    private Long id;
    private String name;
    private String species;
    private String gender;
    private Long apiId;
}