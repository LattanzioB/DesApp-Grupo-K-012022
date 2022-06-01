package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Crypto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String quoteFetechTime;
    private double quote;

    public Crypto(String name, Double quote){
        this.name = name;
        //dummy value, until it can be fetch from a service
        this.quote = quote;
    }

    public Crypto(String string) {
    }

    public Crypto() {
    }

    public double getQuote() {
        return this.quote;
    }

    public String getName() {
        return this.name;
    }
}
