package model;

public class Published extends TransactionState {

    public Published(Transaction transaction) {
        super(transaction);
    }


    @Override
    public void transferTake() {
        super.transaction.changeState(new Taken(super.transaction));
        
    }

    @Override
    public void transferReceived() {
        // Error
        
    }

    @Override
    public void cancelTransaction() {
        // descontar puntos al usuario que cancelo
        
    }
    
}
