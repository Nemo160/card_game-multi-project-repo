package com.cardgame.common.model;

import lombok.Data;

import java.util.ArrayList;
//import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class BiomeCard {
    private String biomeId;
    private final String category = "biome";
    private String name;
    private String flavorText;
    private ArrayList<Resource> resources = new ArrayList<>();
    private ArrayList<Ability> abilities = new ArrayList<>();

}
