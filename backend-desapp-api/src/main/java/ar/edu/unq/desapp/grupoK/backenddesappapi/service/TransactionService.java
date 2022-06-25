package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import java.rmi.ServerException;
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
    private UserService userService;

    @Autowired
    private CryptoService cryptoService;

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
    public void newTransaction(TransactionDto transactiondto) throws ServerException {
        Optional<ModelUser> user = userService.getUser(transactiondto.getPublisherId());
        Optional<Crypto> crypto = cryptoService.getCryptoByName(transactiondto.getCryptoName());
        Transaction newTransaction;

        if (user == null || crypto == null) {
            throw new ServerException(null);
        } else {
            newTransaction = new Transaction(crypto.get().getName(),
            transactiondto.getQuantity(), user.get(), transactiondto.getOperationType(), crypto.get().getQuote());
            transactionRepository.save(newTransaction);
        }


    }

    @Transactional
    public ArrayList<TransactionDto> getActiveTransactions(){
        ArrayList<TransactionDto> activeTransactionsList= new ArrayList<TransactionDto>();
        Iterable<Optional<Transaction>> activeTransactions = StreamSupport.stream(this.getTransactions().spliterator(), false)
        .filter(ts -> ts.getTransactionState() == 1 || ts.getTransactionState() == 2)
        .map((t) -> Optional.of(t)).collect(Collectors.toList());
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


    @Transactional
    public void takeTransaction(TakeTransactionDto takeTransactionDto) {
        Optional<ModelUser> consumer = userService.getUser(takeTransactionDto.getConsumerId());
        Optional<Transaction> transaction = this.getTransaction(takeTransactionDto.getTransactionId());


        //Integer transactionstateId = transaction.get().getTransactionState().getId();
        consumer.get().takeTransaction(transaction.get());
        //transaction.get().takeTransaction(consumer.get());
        //stateService.deleteState(transactionstateId);

        transactionRepository.save(transaction.get());
    }
}
