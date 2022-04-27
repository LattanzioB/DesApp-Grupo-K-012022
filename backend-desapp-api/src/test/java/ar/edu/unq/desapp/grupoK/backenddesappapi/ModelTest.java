package ar.edu.unq.desapp.grupoK.backenddesappapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import model.Crypto;
import model.SystemManager;
import model.Transaction;
import model.User;

public class ModelTest {
    
    @Test
    public void systemManagerCreatesUser(){
        User user = new User("Juan", "surname", "email", "adress", "password" , 12345678 , 98765432);
        SystemManager system = new SystemManager();
        system.addUser(user);
        assertEquals(system.getUsers().size(), 1);
    }

    @Test
    public void UserCreatesTransactionAndTransactionStateIsPublished(){
        Crypto bnb = new Crypto("BNB"); 
        User user = new User("Juan", "surname", "email", "adress", "password" , 12345678 , 98765432);
        Transaction transaction = new Transaction(bnb,  0.5,  user, "buy");
        
        assertEquals(user.getTransactionsPublished().size(), 1);
        assertTrue(transaction.isPublishedState());
    }

    @Test
    public void ConsumerCanTakeATransactionAndTransactionChangeState(){
        Crypto bnb = new Crypto("BNB"); 
        User user = new User("Juan", "surname", "email", "adress", "password" , 12345678 , 98765432);
        User user2 = new User("Juan", "surname", "email2", "adress", "password" , 12345678 , 98765432);
        
        Transaction transaction = new Transaction(bnb,  0.5,  user, "buy");

        user2.takeTransaction(transaction);

        assertEquals(user.getTransactionsPublished().get(0), user2.getTransactionsTaken().get(0));
        assertTrue(transaction.isTakenState());
    }

    @Test
    public void PublisherCanReceiveATransactionAndTransactionStateChangeToReceived(){
        Crypto bnb = new Crypto("BNB"); 
        User user = new User("Juan", "surname", "email", "adress", "password" , 12345678 , 98765432);
        User user2 = new User("Juan", "surname", "email2", "adress", "password" , 12345678 , 98765432);
        
        Transaction transaction = new Transaction(bnb,  0.5,  user, "buy");

        user2.takeTransaction(transaction);

        user.transferReceived(transaction);

        assertEquals(user.getTransactionsPublished().get(0), user2.getTransactionsTaken().get(0));
        assertTrue(transaction.isReceivedState());
    }

}
