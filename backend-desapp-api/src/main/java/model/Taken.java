package model;

public class Taken extends TransactionState{

    public Taken(Transaction transaction) {
        super(transaction);
        
    }

    @Override
    public void transferTake() {
        //error
    }

    @Override
    public void transferReceived() {
        super.transaction.changeState(new Received(super.transaction));
        
    }

    @Override
    public void cancelTransaction() {
        // TODO Auto-generated method stub
        
    }
    
}
