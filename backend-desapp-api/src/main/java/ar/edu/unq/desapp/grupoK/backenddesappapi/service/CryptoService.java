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
    public Crypto save(Crypto newCrypto) {
        //VALIDACION
        return cryptoRepository.save(newCrypto);
    }

    public Optional<Crypto> getCryptoByName(String cryptoName) {
        var crypto  = StreamSupport.stream(cryptoRepository.findAll().spliterator(), false)
        .map((o) -> Optional.of(o)).collect(Collectors.toList()).get(0); 
        return crypto;
    }
}
