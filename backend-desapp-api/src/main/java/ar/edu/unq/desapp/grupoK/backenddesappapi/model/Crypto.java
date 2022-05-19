package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

public class Crypto {
    
    private String name;
    private String quoteFetechTime;
    private double quote;

    public Crypto(String name){
        this.name = name;
        //dummy value, until it can be fetch from a service
        this.quote = 0.5;
    }

    public double getQuote() {
        return this.quote;
    }
}
