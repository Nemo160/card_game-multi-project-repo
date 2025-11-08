package com.cardCreator.application;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CardParser {
    private final Gson gson;
    public CardParser(){
        gson = new GsonBuilder().setPrettyPrinting().create();
    }
    public void parseCreatureJSON(CreatureCard card){
        try(FileWriter writer = new FileWriter(card.getId() + ".json")){
            gson.toJson(card,writer);
            System.out.println("Card saved as " +card.getId() + ".json");

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void parseBiomeJSON(BiomeCard card){
        try(FileWriter writer = new FileWriter("card.getId()" + ".json")){
            gson.toJson(card,writer);
            System.out.println("Card saved as " +card.getId() + ".json");

        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
}
