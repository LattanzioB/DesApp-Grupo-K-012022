package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.TransactionRepository;


@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;
    

    @Transactional
    public Iterable<Transaction> getTransactions(){
        return transactionRepository.findAll();
    }

    @Transactional
    public Optional<Transaction> getTransaction(Integer id){
        return transactionRepository.findById(id);
    }

    @Transactional
    public Transaction save(Transaction newTransaction) {
        //VALIDACION
        return transactionRepository.save(newTransaction);
    }
}
