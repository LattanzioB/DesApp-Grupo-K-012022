package model;

public abstract class TransactionState {
    private Transaction transaction;

    public TransactionState(Transaction transaction){
        this.transaction = transaction;
    }

    public abstract void transferTake();
    public abstract void transferReceived();
    public abstract void cancelTransaction();
}
