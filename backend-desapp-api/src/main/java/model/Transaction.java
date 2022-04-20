package model;

public class Transaction {
    private Crypto crypto;
    private Float quantity;
    private Float quote;
    private Float amountARS;
    private User publisher;
    private String operationType;
    private TransactionState transactionState;

    public Transaction(Crypto crypto, Float quantity, User publisher, String operationType){
        this.crypto = crypto;
        this.quantity = quantity;
        this.quote = crypto.getQuote();
        this.amountARS = this.quote * 200;
        this.publisher = publisher;
        this.operationType = operationType;
        this.transactionState = new Published(this);
    }

    public void takeTransaction(){
        this.transactionState.transferTake();
    }

    public void transferReceived(){
        this.transactionState.transferReceived();
    }
    public void changeState(TransactionState transactionState) {
        this.transactionState = transactionState;
    }
}
