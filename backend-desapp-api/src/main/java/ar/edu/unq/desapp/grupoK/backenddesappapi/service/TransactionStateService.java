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
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.TransactionState;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.CryptoRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.TransactionStateRepository;

@Service
public class TransactionStateService {
    

    @Autowired
    private TransactionStateRepository transactionRepository;
    

    @Transactional
    public Iterable<TransactionState> getStates(){
        return transactionRepository.findAll();
    }

    @Transactional
    public Optional<TransactionState> getState(Integer id){
        return transactionRepository.findById(id);
    }

    @Transactional
    public TransactionState save(TransactionState newState) {
        //VALIDACION
        return transactionRepository.save(newState);
    }
}
