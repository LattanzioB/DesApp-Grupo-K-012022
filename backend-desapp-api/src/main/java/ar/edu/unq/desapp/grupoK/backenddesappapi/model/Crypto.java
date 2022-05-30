package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.Id;

public class Crypto {
    
    @Id
    private Integer id;

    private String name;
    private String quoteFetechTime;
    private double quote;

    public Crypto(String name, Double quote){
        this.name = name;
        //dummy value, until it can be fetch from a service
        this.quote = quote;
    }

    public double getQuote() {
        return this.quote;
    }
}
