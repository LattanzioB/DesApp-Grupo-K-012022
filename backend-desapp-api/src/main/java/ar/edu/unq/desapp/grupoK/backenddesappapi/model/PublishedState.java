package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PublishedState extends TransactionState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stateId;

    public PublishedState() {
        super();
    }

    @Override
    public void transferTake(Transaction transaction) {
        
        transaction.changeState(2);
        
    }

    @Override
    public void transferReceived(Transaction transaction) {
        // Error
        
    }

    @Override
    public void cancelTransaction(Transaction transaction,ModelUser user) {
        user.deleteTransactionPublished(transaction);
        
    }


    @Override
    public boolean isPublished(Transaction transaction) {
        return true;
    }


    @Override
    public boolean isTaken(Transaction transaction) {
        return false;
    }


    @Override
    public boolean isReceived(Transaction transaction) {
        return false;
    }

    
}
