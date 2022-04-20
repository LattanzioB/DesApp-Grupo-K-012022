package model;

public class Received extends TransactionState{

    public Received(Transaction transaction) {
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
    public void cancelTransaction() {
        //error
        
    }
    
}
