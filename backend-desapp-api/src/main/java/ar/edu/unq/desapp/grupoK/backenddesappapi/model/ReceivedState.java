package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ReceivedState extends TransactionState{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stateId;

    public ReceivedState() {
        super();
    }

    @Override
    public void transferTake(Transaction transaction) {
        // error
        
    }

    @Override
    public void transferReceived(Transaction transaction) {
        //error
        
    }

    @Override
    public void cancelTransaction(Transaction transaction, ModelUser user) {
        //error
        
    }

    @Override
    public boolean isPublished(Transaction transaction) {
        return false;
    }

    @Override
    public boolean isTaken(Transaction transaction) {
        return false;
    }

    @Override
    public boolean isReceived(Transaction transaction) {
        return true;
    }
    
}
