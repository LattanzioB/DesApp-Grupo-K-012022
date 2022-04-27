package model;

public class ReceivedState extends TransactionState{

    public ReceivedState(Transaction transaction) {
        super(transaction);
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
    public void cancelTransaction(User user) {
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
