package ar.edu.unq.desapp.grupoK.backenddesappapi.service.builders;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.ModelUser;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Transaction;

public class UserBuilder {

    private String name;
    private String surname;

    private String email;

    private String adress;
    private String password;
    private Integer cvu;
    private Integer wallet;
    private Integer popularity = 0;
    private List<Transaction> transactionsPublished = new ArrayList<>();;
    private List<Transaction> transactionsTaken = new ArrayList<>();;

    public static UserBuilder userwithName(String name) {
        UserBuilder builder = new UserBuilder();
        builder.name = name;
        return builder;
    }

    public ModelUser build() {
        return new ModelUser(name,surname,email,adress,password,cvu,wallet);
    }

    public UserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public UserBuilder withSurName(String surname) {
        this.surname = surname;
        return this;
    }



    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withAdress(String adress) {
        this.adress = adress;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withCvu(Integer cvu) {
        this.cvu = cvu;
        return this;
    }

    public UserBuilder withWallet(Integer wallet) {
        this.wallet = wallet;
        return this;
    }



}