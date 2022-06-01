package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import ar.edu.unq.desapp.grupoK.backenddesappapi.dtos.TransactionDto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Crypto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.CryptoService;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.TransactionService;
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

    @PostMapping
    public ResponseEntity<TransactionDto> publishTransaction(@RequestBody TransactionDto transaction) throws ServerException{
        Optional<User> user = userService.getUser(transaction.getPublisherId());
        Optional<Crypto> crypto = cryptoService.getCryptoByName(transaction.getCryptoName());
        Transaction newTransaction = new Transaction(crypto.get().getName(),
        transaction.getQuantity(), user.get(), transaction.getOperationType(), crypto.get().getQuote());
        transactionService.save(newTransaction);


        if (user == null || crypto == null) {
            throw new ServerException(null);
        } else {

            return new ResponseEntity<>(transaction, HttpStatus.CREATED);
        }
    }

}


    



