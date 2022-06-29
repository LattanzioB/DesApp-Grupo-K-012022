package ar.edu.unq.desapp.grupoK.backenddesappapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ar.edu.unq.desapp.grupoK.backenddesappapi.exception.InvalidUserReceivingTransferException;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.ModelUser;

public class ModelTest {
    

    @Test
    public void UserCreatesTransactionAndTransactionStateIsPublished(){
        ModelUser user = new ModelUser();
        Transaction transaction = new Transaction(user);
        transaction.setPublisher(user);
        assertEquals(user.getTransactionsPublished().size(), 1);
        assertTrue(transaction.isPublishedState());
    }

    @Test
    public void ConsumerCanTakeATransactionAndTransactionChangeStateAndTheConsumerHasANewTransactionTaken(){

        ModelUser user = new ModelUser();
        ModelUser user2 = new ModelUser();
        
        Transaction transaction = new Transaction(user);

        user2.takeTransaction(transaction);

        assertEquals(user.getTransactionsPublished().get(0), user2.getTransactionsTaken().get(0));
        assertTrue(transaction.isTakenState());
        assertEquals(user2.getTransactionsTaken().size(), 1);
    }

    @Test
    public void PublisherCanReceiveATransactionAndTransactionStateChangeToReceived() throws InvalidUserReceivingTransferException{
        ModelUser user = new ModelUser();
        ModelUser user2 = new ModelUser();
        
        Transaction transaction = new Transaction(user);


        user2.takeTransaction(transaction);

        user.transferReceived(transaction);

        assertEquals(user.getTransactionsPublished().get(0), user2.getTransactionsTaken().get(0));
        assertTrue(transaction.isReceivedState());
    }

    @Test
    public void ConsumerCannotReceiveATransactionAndTransactionStateDoenstChangeToReceived() throws InvalidUserReceivingTransferException{
        ModelUser user = new ModelUser();
        user.setEmail("email");
        ModelUser user2 = new ModelUser();
        user2.setEmail("email2");
        Transaction transaction = new Transaction(user);

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

        ModelUser user = new ModelUser();

        Transaction transaction = new Transaction(user);

        user.cancelTransaction(transaction);

        assertEquals(user.getTransactionsPublished().size(), 0);

    }
 
    @Test
    public void PublisherCancelsTransactionWhenIsJustTakenAndTransferIsRemovedFromsHisTransfersPublishedAndHisPopularityIsReduced(){
        ModelUser user = new ModelUser();
        user.setEmail("email");
        ModelUser user2 = new ModelUser();
        user2.setEmail("email2");
        
        Transaction transaction = new Transaction(user);

        user2.takeTransaction(transaction);

        user.cancelTransaction(transaction);

        assertEquals(user.getTransactionsPublished().size(), 0);
        assertEquals(user.getPopularity(), -20);
        assertEquals(user2.getTransactionsTaken().size(), 0);
    }

    @Test
    public void ConsumerCancelsTransactionWhenIsJustTakenAndTransferIsRemovedFromsHisTransfersTakenAndHisPopularityIsReducedAndTransferGoesBackToPublishedState(){
        ModelUser user = new ModelUser();
        user.setEmail("email");
        ModelUser user2 = new ModelUser();
        user2.setEmail("email2");
        Transaction transaction = new Transaction(user);

        user2.takeTransaction(transaction);

        user2.cancelTransaction(transaction);

        assertEquals(user.getTransactionsPublished().size(), 1);
        assertEquals(user2.getPopularity(), -20);
        assertEquals(user2.getTransactionsTaken().size(), 0);
        assertTrue(transaction.isPublishedState());
    }

}

