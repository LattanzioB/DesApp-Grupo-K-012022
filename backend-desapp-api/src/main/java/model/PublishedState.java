package model;

public class PublishedState extends TransactionState {

    public PublishedState(Transaction transaction) {
        super(transaction);
    }


    @Override
    public void transferTake() {
        super.transaction.changeState(new TakenState(super.transaction));
        
    }

    @Override
    public void transferReceived() {
        // Error
        
    }

    @Override
    public void cancelTransaction() {
        // descontar puntos al usuario que cancelo
        
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
