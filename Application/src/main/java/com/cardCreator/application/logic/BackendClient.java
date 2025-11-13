package com.cardcreator.application.logic;

import com.cardgame.common.model.CreatureCard;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BackendClient {
    private static final String BASE_URL = "http://localhost:8080/api";
    private static final Gson gson = new Gson();

    public static void sendCard(CreatureCard card, String cardType) {
        String endpoint = "";
        String json = gson.toJson(card);
        if(cardType.equals("creature")){  endpoint = BASE_URL + "/creature-cards";}
        if(cardType.equals("biome")){  endpoint = BASE_URL + "/biome-cards";}
        //if(endpoint.isEmpty()){throw new Exception(new String ("Not valid card type"));}
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(endpoint);
            request.setEntity(new StringEntity(json, StandardCharsets.UTF_8));
            request.setHeader("Content-Type", "application/json");

            client.execute(request, response -> {
                System.out.println("Response code: " + response.getCode());
                return null;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<CreatureCard> getAllCreatureCards() {
        String endpoint = BASE_URL + "/creature-cards";
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            return client.execute(new HttpGet(endpoint), response -> {
                String json = new String(response.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
                Type listType = new TypeToken<List<CreatureCard>>(){}.getType();
                return gson.fromJson(json, listType);
            });
        } catch (IOException e) {
            e.printStackTrace();
            return List.of();
        }
    }

}
