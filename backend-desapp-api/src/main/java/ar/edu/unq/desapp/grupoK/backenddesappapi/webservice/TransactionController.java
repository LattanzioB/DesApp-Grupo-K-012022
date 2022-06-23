package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

import java.rmi.ServerException;
import java.util.ArrayList;

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
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.TransactionService;


@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    

    @PostMapping
    public ResponseEntity<TransactionDto> publishTransaction(@RequestBody TransactionDto transactionDto) throws ServerException{
        transactionService.newTransaction(transactionDto);

        return new ResponseEntity<>(transactionDto, HttpStatus.CREATED);

    }

    @GetMapping(value = "/active")
    public ArrayList<TransactionDto> getActiveTransactions(){
      
        return transactionService.getActiveTransactions();
    }


    @PostMapping(value = "/take")
    public ResponseEntity<TakeTransactionDto> takeTransaction(@RequestBody TakeTransactionDto takeTransactionDto){
        transactionService.takeTransaction(takeTransactionDto);
        
        return new ResponseEntity<>(takeTransactionDto, HttpStatus.OK);
    }
}


    



