package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionId;
    private String cryptoName;
    private double quantity;
    private double quote;
    private double amountARS;

    @ManyToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name = "userId", nullable = false, insertable=false, updatable=false)
    //@Column(name= "PUBLISHER")
    private ModelUser publisher;

    private String operationType;
    
    @OneToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name = "transactionId", nullable = true, insertable=false, updatable=true)
    private TransactionState transactionState;

    @OneToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name = "transactionId", nullable = true, insertable=false, updatable=true)
    private TransactionFrame frame;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = true, insertable=false, updatable=true)
    //@Column(name= "CONSUMER")
    private ModelUser consumer;

    public Transaction(String cryptoName, double quantity, ModelUser publisher, String operationType, double quote){
        this.cryptoName = cryptoName;
        this.quantity = quantity;
        this.quote = quote;
        this.amountARS = this.quote * 200;
        this.publisher = publisher;
        this.operationType = operationType;
        this.transactionState = new PublishedState(this);
        publisher.addTransaction(this);
        this.frame = new TransactionFrame(this);
    }



    public Transaction(){

    }

    public TransactionState getTransactionState(){
        return this.transactionState;
    }

    public void setId(Integer id) {
        this.transactionId = id;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setQuote(double quote) {
        this.quote = quote;
    }

    public void setAmountARS(double amountARS) {
        this.amountARS = amountARS;
    }

    public void setPublisher(ModelUser publisher) {
        this.publisher = publisher;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public void setTransactionState(TransactionState transactionState) {
        this.transactionState = transactionState;
    }

    public void setFrame(TransactionFrame frame) {
        this.frame = frame;
    }

    public void setConsumer(ModelUser consumer) {
        this.consumer = consumer;
    }

    public String getCryptoName() {
        return cryptoName;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getQuote() {
        return quote;
    }

    public String getOperationType() {
        return operationType;
    }

    public Transaction(Crypto bnb, double d, ModelUser publisher, String string) {
        this.transactionState = new PublishedState(this);
        publisher.addTransaction(this);
    }

    public void takeTransaction(ModelUser user){
        this.transactionState.transferTake();
        this.consumer = user;
    }

    public void transferReceived(){
        this.transactionState.transferReceived();
    }

    public void changeState(TransactionState transactionState) {
        this.transactionState = transactionState;
    }

    public ModelUser getPublisher() {
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


    public ModelUser getConsumer() {
        return this.consumer;
    }

    public void cancelTransaction(ModelUser user) {
        this.transactionState.cancelTransaction(user);
    }

}
