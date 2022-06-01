package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.CascadeType;
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
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false, insertable=false, updatable=false)
    private User publisher;
    private String operationType;
    @OneToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name = "transaction", nullable = true, insertable=false, updatable=false)
    private TransactionState transactionState;
    @OneToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name = "transaction", nullable = true, insertable=false, updatable=false)
    private TransactionFrame frame;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = true, insertable=false, updatable=false)
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
