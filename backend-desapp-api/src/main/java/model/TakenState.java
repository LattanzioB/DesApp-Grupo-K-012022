package model;

public class TakenState extends TransactionState{

    public TakenState(Transaction transaction) {
        super(transaction);
        
    }

    @Override
    public void transferTake() {
        //error
    }

    @Override
    public void transferReceived() {
        super.transaction.changeState(new ReceivedState(super.transaction));
        
    }

    @Override
    public void cancelTransaction() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isPublished() {
        return false;
    }

    @Override
    public boolean isTaken() {
        return true;
    }

    @Override
    public boolean isReceived() {
        return false;
    }
    
}
