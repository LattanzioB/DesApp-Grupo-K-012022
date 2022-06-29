package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "publisherId", nullable = true, insertable=true, updatable=true)
    private ModelUser publisher;

    @ManyToOne()
    @JoinColumn(name = "consumerId", nullable = true, insertable=true, updatable=true)
    private ModelUser consumer;

    private String operationType;
    
    
    private Integer transactionState;

     

    @OneToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name = "transactionId", nullable = true, insertable=false, updatable=true)
    private TransactionFrame frame;

    @ManyToOne(cascade = {CascadeType.ALL})
    private TransactionStateAssigner stateAssigner;

    public Transaction(String cryptoName, double quantity, ModelUser publisher, String operationType, double quote){
        this.cryptoName = cryptoName;
        this.quantity = quantity;
        this.quote = quote;
        this.amountARS = this.quote * 200;
        this.publisher = publisher;
        this.operationType = operationType;
        this.transactionState = 1;
        publisher.addTransaction(this);
        this.frame = new TransactionFrame(this);
        this.stateAssigner = new TransactionStateAssigner();
    }



    public Transaction(){

    }

    public TransactionFrame getFrame() {
        return this.frame;
    }



    public Integer getTransactionState(){
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

    public void setTransactionState(Integer transactionState) {
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
        this.transactionState = 1;
        publisher.addTransaction(this);
    }

    public Transaction(ModelUser publisher) {
        this.publisher = publisher;
        publisher.addTransaction(this);
        this.frame = new TransactionFrame(this);
        this.stateAssigner = new TransactionStateAssigner();
        this.transactionState = 1;
    }



    public void takeTransaction(ModelUser user){
        this.stateAssigner.asigneState(this.transactionState).transferTake(this);
        this.consumer = user;
    }

    public void transferReceived(){
        this.stateAssigner.asigneState(this.transactionState).transferReceived(this);
    }

    public void changeState(Integer transactionState) {
        this.transactionState = transactionState;
    }

    public ModelUser getPublisher() {
        return this.publisher;
    }

    public boolean isTakenState() {
        return this.stateAssigner.asigneState(this.transactionState).isTaken(this);
    }

    public boolean isPublishedState() {
        return this.stateAssigner.asigneState(this.transactionState).isPublished(this);
    }
    public boolean isReceivedState() {
        return this.stateAssigner.asigneState(this.transactionState).isReceived(this);
    }


    public ModelUser getConsumer() {
        return this.consumer;
    }

    public void cancelTransaction(ModelUser user) {
        this.stateAssigner.asigneState(this.transactionState).cancelTransaction(this, user);
    }

}
