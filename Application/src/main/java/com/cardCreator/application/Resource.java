package com.cardCreator.application;

public class Resource {
    private String resource;
    private String amount;

    public Resource(String resource, String amount){
        this.resource = resource;
        this.amount = amount;
    }
    //getters
    public String getResource(){ return resource; }
    public String getAmount(){ return amount; }

}
