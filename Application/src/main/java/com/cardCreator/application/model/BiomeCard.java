package com.cardcreator.application.model;

import lombok.Data;

import java.util.ArrayList;
//import org.springframework.data.mongodb.core.mapping.Document;

@Data
public class BiomeCard {
    private String id;
    private final String category = "biome";
    private String name;
    private String flavorText;
    private ArrayList<Resource> resources = new ArrayList<>();
    private ArrayList<Ability> abilities = new ArrayList<>();

}
