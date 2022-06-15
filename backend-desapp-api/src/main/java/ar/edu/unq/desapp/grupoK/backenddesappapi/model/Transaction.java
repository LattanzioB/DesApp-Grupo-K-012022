package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.ManyToAny;

@Entity
public class Transaction {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String cryptoName;
    private double quantity;
    private double quote;
    private double amountARS;
    @ManyToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name = "userId", nullable = false, insertable=false, updatable=false)
    //@Column(name= "PUBLISHER")
    private User publisher;
    private String operationType;
    @OneToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name = "transactionState", nullable = true, insertable=false, updatable=false)
    private TransactionState transactionState;
    @OneToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name = "transactionFrame", nullable = true, insertable=false, updatable=false)
    private TransactionFrame frame;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = true, insertable=false, updatable=false)
    //@Column(name= "CONSUMER")
    private User consumer;

    public Transaction(String cryptoName, double quantity, User publisher, String operationType, double quote){
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

    public void setId(Integer id) {
        this.id = id;
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

    public void setPublisher(User publisher) {
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

    public void setConsumer(User consumer) {
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

    public Transaction(Crypto bnb, double d, User user, String string) {
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


    public User getConsumer() {
        return this.consumer;
    }

    public void cancelTransaction(User user) {
        this.transactionState.cancelTransaction(user);
    }

}
