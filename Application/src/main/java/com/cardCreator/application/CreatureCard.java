package com.cardCreator.application;

import lombok.Data;

import java.util.ArrayList;


@Data
public class creatureCard {
    private String id;
    private String cardName;
    private String scientificName;
    private String cardLVL;
    private String evasionName;
    private String evasionType;
    private String flavorText;
    private String abilityDesc;
    private ArrayList<Resource> resources = new ArrayList<>();
    private ArrayList<Ability> abilities = new ArrayList<>();
    private ArrayList<String> tags = new ArrayList<>();

}
