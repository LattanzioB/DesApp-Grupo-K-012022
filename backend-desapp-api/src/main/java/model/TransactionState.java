package model;

public abstract class TransactionState {
    private Transaction transaction;

    public TransactionState(Transaction transaction){
        this.transaction = transaction;
    }

    public abstract void transferPaid();
    public abstract void transferReceived();
    public abstract void cancelTransaction();
}
