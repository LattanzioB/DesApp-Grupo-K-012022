package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import java.util.ArrayList;
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
    public Crypto saveAll(CryptoDto newCryptodto) {
        //VALIDACION
        Crypto newcrypto = new Crypto(newCryptodto.getSymbol(), newCryptodto.getPrice());
        return cryptoRepository.save(newcrypto);
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
