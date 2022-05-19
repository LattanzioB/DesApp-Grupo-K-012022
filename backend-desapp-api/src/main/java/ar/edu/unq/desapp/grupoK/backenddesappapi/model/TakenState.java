package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

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
        this.transaction.changeState(new ReceivedState(super.transaction));
        
    }

    @Override
    public void cancelTransaction(User user) {
        if (transaction.getPublisher().getEmail() == user.getEmail()){
            user.discountPoints();
            user.deleteTransactionPublished(this.transaction);
            this.transaction.getConsumer().deleteTransactionTaken(this.transaction);
        }else if (transaction.getConsumer().getEmail() == user.getEmail()){
            user.discountPoints();
            user.deleteTransactionTaken(this.transaction);   
            this.transaction.changeState(new PublishedState(this.transaction));
        }
        
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
