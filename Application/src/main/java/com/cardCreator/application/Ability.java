package com.cardCreator.application;

public class Ability {
    private String name;
    private String tappable;
    private String effect;

    public Ability(String name, String tappable, String effect) {
        this.name = name;
        this.tappable = tappable;
        this.effect = effect;
    }

    public String getName(){ return name; }
    public String getTappable(){ return tappable; }
    public String getEffect(){ return effect; }


}
