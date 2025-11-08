package com.cardCreator.application;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CreatureCard {
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

    public CreatureCard(String id, String cardName, String scientificName,
                        String cardLVL, String flavorText, String abilityDesc, String evasionName, String evasionType){

        this.id = id;
        this.cardName = cardName;
        this.scientificName = scientificName;
        this.cardLVL = cardLVL;
        this.flavorText = flavorText;
        this.abilityDesc = abilityDesc;
        this.evasionName = evasionName;
        this.evasionType = evasionType;
    }

    //adders
    public void addAbility(Ability a){ abilities.add(a); }
    public void addResource(Resource r){ resources.add(r); }
    public void addTag(String t){ tags.add(t); }

    //getters
    public String getId(){ return id; }
    public String getCardName(){ return cardName; }
    public String getCardLVL(){ return cardLVL; }
    public String getScientificName(){ return scientificName; }
    public String getFlavorText(){ return flavorText; }
    public String getAbilityDesc(){ return abilityDesc; }
    public ArrayList<Resource> getResources() { return resources; }
    public ArrayList<Ability> getAbilities() { return abilities; }
    public ArrayList<String> getTags(){ return tags; }

    @Override
    public String toString() {
        return "Card: " + cardName + " (Lvl " + cardLVL + ")\n" +
                "Scientific Name: " + scientificName + "\n" +
                "Flavor: " + flavorText + "\n" +
                "Ability Description: " + abilityDesc + "\n" +
                "Resources: " + resources + "\n" +
                "Abilities: " + abilities;
    }
}
