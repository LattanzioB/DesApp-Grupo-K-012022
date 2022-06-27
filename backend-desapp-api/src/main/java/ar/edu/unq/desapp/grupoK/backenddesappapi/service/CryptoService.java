package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoK.backenddesappapi.dtos.CryptoDto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Crypto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.CryptoRepository;

@Service
public class CryptoService {
    
    private List<String> cryptosToGet = Arrays.asList("ALICEUSDT","MATICUSDT","AXSUSDT","AAVEUSDT","ATOMUSDT","NEOUSDT","DOTUSDT","CAKEUSDT","ADAUSDT","TRXUSDT","AUDIOUSDT","BTCUSDT","ETHUSDT","BNBUSDT");
    
    private String urlGetName = "https://api.binance.us/api/v3/ticker/price?symbol=";
    private String urlGetAll = "https://api.binance.us/api/v3/ticker/price";


    
    public String getUrlGetName() {
        return urlGetName;
    }

    public String getUrlGetAll() {
        return urlGetAll;
    }

    @Autowired
    private CryptoRepository cryptoRepository;
    

    @Transactional
    public Iterable<Crypto> getCryptos(){
        return cryptoRepository.findAll();
    }

    @Transactional
    public Optional<Crypto> getCrypto(Integer id){
        return cryptoRepository.findById(id);
    }

    @Transactional
    public Crypto save(CryptoDto newCryptodto) {
        //VALIDACION
        Crypto newcrypto = new Crypto(newCryptodto.getSymbol(), newCryptodto.getPrice());
        return cryptoRepository.save(newcrypto);
    }

     @Transactional
    public ArrayList<CryptoDto> saveAll(CryptoDto[] cryptodtos) {
        //VALIDACION
        ArrayList<CryptoDto> finalCryptos = new ArrayList<CryptoDto>();

        for (CryptoDto cryptoDto : cryptodtos) {
            if(cryptosToGet.contains(cryptoDto.getSymbol())){
                finalCryptos.add(cryptoDto);
            }
        }

        for (CryptoDto cryptoDto2 : finalCryptos) {
            Crypto newcrypto = new Crypto(cryptoDto2.getSymbol(), cryptoDto2.getPrice(), new Date());

            cryptoRepository.save(newcrypto);
        }
        return finalCryptos;
    }

    @Transactional
    public Optional<Crypto> getCryptoByName(String cryptoName) {
        Iterable<Crypto> cryptos = cryptoRepository.findAll();
        Optional<Crypto> cryptores = Optional.empty(); 
        for (Crypto crypto : cryptos) {
            if (crypto.getName().equals(cryptoName)){
                cryptores = Optional.of(crypto);
            }
        }
        return cryptores;
    }
}
