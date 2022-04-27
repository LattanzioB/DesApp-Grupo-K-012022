package model;

public class Transaction {
    private Crypto crypto;
    private double quantity;
    private double quote;
    private double amountARS;
    private User publisher;
    private String operationType;
    private TransactionState transactionState;
    private TransactionFrame frame;
    private User consumer;

    public Transaction(Crypto crypto, double quantity, User publisher, String operationType){
        this.crypto = crypto;
        this.quantity = quantity;
        this.quote = crypto.getQuote();
        this.amountARS = this.quote * 200;
        this.publisher = publisher;
        this.operationType = operationType;
        this.transactionState = new PublishedState(this);
        publisher.addTransaction(this);
        this.frame = new TransactionFrame(this);
    }

    public void takeTransaction(User user){
        this.transactionState.transferTake();
        this.consumer = user;
    }

    public void transferReceived(){
        this.transactionState.transferReceived();
    }

    public void changeState(TransactionState transactionState) {
        this.transactionState = transactionState;
    }

    public User getPublisher() {
        return this.publisher;
    }

    public boolean isTakenState() {
        return this.transactionState.isTaken();
    }

    public boolean isPublishedState() {
        return this.transactionState.isPublished();
    }
    public boolean isReceivedState() {
        return this.transactionState.isReceived();
    }
}
