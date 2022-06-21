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
    public ResponseEntity<TransactionDto> publishTransaction(@RequestBody TransactionDto transactionDto) throws ServerException{
        Optional<ModelUser> user = userService.getUser(transactionDto.getPublisherId());
        Optional<Crypto> crypto = cryptoService.getCryptoByName(transactionDto.getCryptoName());

        if (user == null || crypto == null) {
            throw new ServerException(null);
        } else {
            transactionService.newTransaction(transactionDto, user.get(), crypto.get());
            return new ResponseEntity<>(transactionDto, HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/active")
    public ArrayList<TransactionDto> getActiveTransactions(){
        
        return transactionService.getActiveTransactions(stateService.getStates());
    }


    @PostMapping(value = "/take")
    public ResponseEntity<TakeTransactionDto> takeTransaction(@RequestBody TakeTransactionDto takeTransactionDto){
        Optional<ModelUser> consumer = userService.getUser(takeTransactionDto.getConsumerId());
        Optional<Transaction> transaction = transactionService.getTransaction(takeTransactionDto.getTransactionId());
        transactionService.takeTransaction(takeTransactionDto, consumer.get(), transaction.get());
        
        return new ResponseEntity<>(takeTransactionDto, HttpStatus.OK);
    }
}


    



