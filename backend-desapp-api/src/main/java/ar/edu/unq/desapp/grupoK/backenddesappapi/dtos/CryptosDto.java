package ar.edu.unq.desapp.grupoK.backenddesappapi.dtos;

import java.util.List;

public class CryptosDto {
    private List<String> cryptos;


    public List<String> getCryptos() {
        return cryptos;
    }

    public void setCryptos(List<String> cryptos) {
        this.cryptos = cryptos;
    }

    public CryptosDto(){
        
    }

    public Boolean containsCrypto(String crypto){
        return this.cryptos.contains(crypto);
    }


}
