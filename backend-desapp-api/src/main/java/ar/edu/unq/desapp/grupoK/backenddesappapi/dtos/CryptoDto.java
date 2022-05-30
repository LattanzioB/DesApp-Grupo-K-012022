package ar.edu.unq.desapp.grupoK.backenddesappapi.dtos;

public class CryptoDto {
    private String name;
    private double quote;

    public CryptoDto(){
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuote() {
        return quote;
    }

    public void setQuote(double quote) {
        this.quote = quote;
    }

}
