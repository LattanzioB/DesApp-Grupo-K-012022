package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.Entity;

@Entity
public class PublishedState extends TransactionState {

    public PublishedState(Transaction transaction) {
        super(transaction);
    }

    public PublishedState() {
        super();
    }

    @Override
    public void transferTake() {
        this.transaction.changeState(new TakenState(super.transaction));
        
    }

    @Override
    public void transferReceived() {
        // Error
        
    }

    @Override
    public void cancelTransaction(User user) {
        user.deleteTransactionPublished(this.transaction);
        
    }


    @Override
    public boolean isPublished() {
        return true;
    }


    @Override
    public boolean isTaken() {
        return false;
    }


    @Override
    public boolean isReceived() {
        return false;
    }
    
}
