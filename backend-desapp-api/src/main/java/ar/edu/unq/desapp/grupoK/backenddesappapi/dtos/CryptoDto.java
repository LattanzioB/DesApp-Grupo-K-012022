package ar.edu.unq.desapp.grupoK.backenddesappapi.dtos;

import java.util.Date;

public class CryptoDto {
    private String symbol;
    private double price;
    private Date fetchQuoteTime;

    public Date getFetchQuoteTime() {
        return fetchQuoteTime;
    }

    public void setFetchQuoteTime(Date fetchQuoteTime) {
        this.fetchQuoteTime = fetchQuoteTime;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public CryptoDto(){
    }

    public CryptoDto(String name, double quote) {
    }



}
