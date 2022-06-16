package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.desapp.grupoK.backenddesappapi.dtos.TakeTransactionDto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.dtos.TransactionDto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Crypto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.ModelUser;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.CryptoService;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.TransactionService;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.TransactionStateService;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.UserService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserService userService;

    @Autowired
    private CryptoService cryptoService;

    @Autowired
    private TransactionStateService stateService;
    

    @PostMapping
    public ResponseEntity<TransactionDto> publishTransaction(@RequestBody TransactionDto transaction) throws ServerException{
        Optional<ModelUser> user = userService.getUser(transaction.getPublisherId());
        Optional<Crypto> crypto = cryptoService.getCryptoByName(transaction.getCryptoName());
        Transaction newTransaction = new Transaction(crypto.get().getName(),
        transaction.getQuantity(), user.get(), transaction.getOperationType(), crypto.get().getQuote());
        transactionService.save(newTransaction);
        userService.save(user.get());


        if (user == null || crypto == null) {
            throw new ServerException(null);
        } else {

            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/active")
    public List<TransactionDto> getActiveTransactions(){
        ArrayList<TransactionDto> activeTransactionsList= new ArrayList<TransactionDto>();
        Iterable<Optional<Transaction>> activeTransactions = StreamSupport.stream(stateService.getStates().spliterator(), false)
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


    @PostMapping(value = "/take")
    public ResponseEntity<TakeTransactionDto> takeTransaction(@RequestBody TakeTransactionDto takeTransactionDto){
        Optional<ModelUser> consumer = userService.getUser(takeTransactionDto.getConsumerId());
        Optional<Transaction> transaction = transactionService.getTransaction(takeTransactionDto.getTransactionId());
        Integer transactionstateId = transaction.get().getTransactionState().getId();
        transaction.get().takeTransaction(consumer.get());
        stateService.deleteState(transactionstateId);
        userService.save(consumer.get());

        transactionService.save(transaction.get());
        return new ResponseEntity<>(takeTransactionDto, HttpStatus.OK);
    }
}


    



