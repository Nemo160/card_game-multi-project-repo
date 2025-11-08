package com.cardCreator.application;

import lombok.Data;

import java.util.ArrayList;

@Data
public class BiomeCard {
    private String id;
    private final String category = "biome";
    private String name;
    private String flavorText;
    private ArrayList<Resource> resources = new ArrayList<>();
    private ArrayList<Ability> abilities = new ArrayList<>();

}
