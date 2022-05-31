package ar.edu.unq.desapp.grupoK.backenddesappapi.dtos;

public class CryptoDto {
    private String symbol;
    private double price;

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



}
