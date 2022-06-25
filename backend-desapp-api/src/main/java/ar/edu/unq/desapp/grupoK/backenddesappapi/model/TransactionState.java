package ar.edu.unq.desapp.grupoK.backenddesappapi.model;



public abstract class TransactionState {



    public TransactionState(){
    }



    public abstract void transferTake(Transaction transaction);
    public abstract void transferReceived(Transaction transaction);
    public abstract void cancelTransaction(Transaction transaction, ModelUser user);
    public abstract boolean isPublished(Transaction transaction);
    public abstract boolean isTaken(Transaction transaction);
    public abstract boolean isReceived(Transaction transaction);
}
