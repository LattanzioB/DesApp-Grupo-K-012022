package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.Entity;

@Entity
public class ReceivedState extends TransactionState{

    public ReceivedState(Transaction transaction) {
        super(transaction);
    }

    public ReceivedState() {
        super();
    }

    @Override
    public void transferTake() {
        // error
        
    }

    @Override
    public void transferReceived() {
        //error
        
    }

    @Override
    public void cancelTransaction(ModelUser user) {
        //error
        
    }

    @Override
    public boolean isPublished() {
        return false;
    }

    @Override
    public boolean isTaken() {
        return false;
    }

    @Override
    public boolean isReceived() {
        return true;
    }
    
}
