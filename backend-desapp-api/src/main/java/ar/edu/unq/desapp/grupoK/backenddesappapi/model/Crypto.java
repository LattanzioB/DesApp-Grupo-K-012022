package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Crypto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private String name;
    @Column
    private Date quoteFetchTime;
    @Column
    private double quote;

    public Crypto(String name, Double quote){
        this.name = name;
        //dummy value, until it can be fetch from a service
        this.quote = quote;
    }

    public Crypto(String name, Double quote, Date date){
        this.name = name;
        //dummy value, until it can be fetch from a service
        this.quote = quote;
        this.quoteFetchTime = date;
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
