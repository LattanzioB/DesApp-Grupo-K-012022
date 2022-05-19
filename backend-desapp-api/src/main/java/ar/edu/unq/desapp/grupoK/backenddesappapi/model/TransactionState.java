package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

public abstract class TransactionState {
    protected Transaction transaction;

    public TransactionState(Transaction transaction){
        this.transaction = transaction;
    }

    public abstract void transferTake();
    public abstract void transferReceived();
    public abstract void cancelTransaction(User user);
    public abstract boolean isPublished();
    public abstract boolean isTaken();
    public abstract boolean isReceived();
}
