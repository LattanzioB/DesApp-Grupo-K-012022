package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoK.backenddesappapi.dtos.TakeTransactionDto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.dtos.TransactionDto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Crypto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.ModelUser;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.TransactionState;
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

    @Transactional
    public Transaction newTransaction(TransactionDto transactiondto, ModelUser user,Crypto crypto) {
        Transaction newTransaction = new Transaction(crypto.getName(),
        transactiondto.getQuantity(), user, transactiondto.getOperationType(), crypto.getQuote());
        return transactionRepository.save(newTransaction);
    }

    @Transactional
    public ArrayList<TransactionDto> getActiveTransactions(Iterable<TransactionState> states){
        ArrayList<TransactionDto> activeTransactionsList= new ArrayList<TransactionDto>();
        Iterable<Optional<Transaction>> activeTransactions = StreamSupport.stream(states.spliterator(), false)
        .filter(ts -> ts.isPublished() || ts.isTaken())
        .map((o) -> Optional.of(o.getTransaction())).collect(Collectors.toList());
        for(Optional<Transaction> transaction: activeTransactions){
            Transaction newtransaction = transaction.get();
            var transactionDto = new TransactionDto();
            transactionDto.setCryptoName(newtransaction.getCryptoName());
            transactionDto.setPublisherId(newtransaction.getPublisher().getUserId());
            transactionDto.setOperationType(newtransaction.getOperationType());
            transactionDto.setQuantity(newtransaction.getQuantity());
            activeTransactionsList.add(transactionDto);
        }
        return activeTransactionsList;
    }

    public void takeTransaction(TakeTransactionDto takeTransactionDto, ModelUser consumer, Transaction transaction) {
//        Integer transactionstateId = transaction.getTransactionState().getId();
        transaction.takeTransaction(consumer);
//        stateService.deleteState(transactionstateId);


        transactionRepository.save(transaction);
    }
}
