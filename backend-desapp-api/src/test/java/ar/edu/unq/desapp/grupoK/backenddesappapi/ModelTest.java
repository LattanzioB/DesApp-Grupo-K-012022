package ar.edu.unq.desapp.grupoK.backenddesappapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupoK.backenddesappapi.exception.InvalidUserReceivingTransferException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Crypto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.SystemManager;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.ModelUser;

public class ModelTest {
    
    @Test
    public void systemManagerCreatesUser(){
        ModelUser user = new ModelUser("Juan", "surname", "email", "adress", "password" , 12345678 , 98765432);
        SystemManager system = new SystemManager();
        system.addUser(user);
        assertEquals(system.getUsers().size(), 1);
    }

    @Test
    public void UserCreatesTransactionAndTransactionStateIsPublished(){
        Crypto bnb = new Crypto("BNB"); 
        ModelUser user = new ModelUser("Juan", "surname", "email", "adress", "password" , 12345678 , 98765432);
        Transaction transaction = new Transaction(bnb,  0.5,  user, "buy");
        
        assertEquals(user.getTransactionsPublished().size(), 1);
        assertTrue(transaction.isPublishedState());
    }

    @Test
    public void ConsumerCanTakeATransactionAndTransactionChangeStateAndTheConsumerHasANewTransactionTaken(){
        Crypto bnb = new Crypto("BNB"); 
        ModelUser user = new ModelUser("Juan", "surname", "email", "adress", "password" , 12345678 , 98765432);
        ModelUser user2 = new ModelUser("Juan", "surname", "email2", "adress", "password" , 12345678 , 98765432);
        
        Transaction transaction = new Transaction(bnb,  0.5,  user, "buy");

        user2.takeTransaction(transaction);

        assertEquals(user.getTransactionsPublished().get(0), user2.getTransactionsTaken().get(0));
        assertTrue(transaction.isTakenState());
        assertEquals(user2.getTransactionsTaken().size(), 1);
    }

    @Test
    public void PublisherCanReceiveATransactionAndTransactionStateChangeToReceived() throws InvalidUserReceivingTransferException{
        Crypto bnb = new Crypto("BNB"); 
        ModelUser user = new ModelUser("Juan", "surname", "email", "adress", "password" , 12345678 , 98765432);
        ModelUser user2 = new ModelUser("Juan", "surname", "email2", "adress", "password" , 12345678 , 98765432);
        
        Transaction transaction = new Transaction(bnb,  0.5,  user, "buy");

        user2.takeTransaction(transaction);

        user.transferReceived(transaction);

        assertEquals(user.getTransactionsPublished().get(0), user2.getTransactionsTaken().get(0));
        assertTrue(transaction.isReceivedState());
    }

    @Test
    public void ConsumerCannotReceiveATransactionAndTransactionStateDoenstChangeToReceived() throws InvalidUserReceivingTransferException{
        Crypto bnb = new Crypto("BNB"); 
        ModelUser user = new ModelUser("Juan", "surname", "email", "adress", "password" , 12345678 , 98765432);
        ModelUser user2 = new ModelUser("Juan", "surname", "email2", "adress", "password" , 12345678 , 98765432);
        
        Transaction transaction = new Transaction(bnb,  0.5,  user, "buy");

        user2.takeTransaction(transaction);

        try {
            user2.transferReceived(transaction);
          }
          catch (InvalidUserReceivingTransferException e) {
          }
        assertEquals(user.getTransactionsPublished().get(0), user2.getTransactionsTaken().get(0));
        assertTrue(transaction.isTakenState());
    }

    @Test
    public void PublisherCancelsTransactionWhenIsJustPublishedAndTransferIsRemovedFromsHisTransfersPublished(){
        Crypto bnb = new Crypto("BNB"); 
        ModelUser user = new ModelUser("Juan", "surname", "email", "adress", "password" , 12345678 , 98765432);

        Transaction transaction = new Transaction(bnb,  0.5,  user, "buy");

        user.cancelTransaction(transaction);

        assertEquals(user.getTransactionsPublished().size(), 0);

    }

    @Test
    public void PublisherCancelsTransactionWhenIsJustTakenAndTransferIsRemovedFromsHisTransfersPublishedAndHisPopularityIsReduced(){
        Crypto bnb = new Crypto("BNB"); 
        ModelUser user = new ModelUser("Juan", "surname", "email", "adress", "password" , 12345678 , 98765432);
        ModelUser user2 = new ModelUser("Juan", "surname", "email2", "adress", "password" , 12345678 , 98765432);
        
        Transaction transaction = new Transaction(bnb,  0.5,  user, "buy");

        user2.takeTransaction(transaction);

        user.cancelTransaction(transaction);

        assertEquals(user.getTransactionsPublished().size(), 0);
        assertEquals(user.getPopularity(), -20);
        assertEquals(user2.getTransactionsTaken().size(), 0);
    }

    @Test
    public void ConsumerCancelsTransactionWhenIsJustTakenAndTransferIsRemovedFromsHisTransfersTakenAndHisPopularityIsReducedAndTransferGoesBackToPublishedState(){
        Crypto bnb = new Crypto("BNB"); 
        ModelUser user = new ModelUser("Juan", "surname", "email", "adress", "password" , 12345678 , 98765432);
        ModelUser user2 = new ModelUser("Juan", "surname", "email2", "adress", "password" , 12345678 , 98765432);
        
        Transaction transaction = new Transaction(bnb,  0.5,  user, "buy");

        user2.takeTransaction(transaction);

        user2.cancelTransaction(transaction);

        assertEquals(user.getTransactionsPublished().size(), 1);
        assertEquals(user2.getPopularity(), -20);
        assertEquals(user2.getTransactionsTaken().size(), 0);
        assertTrue(transaction.isPublishedState());
    }

}
