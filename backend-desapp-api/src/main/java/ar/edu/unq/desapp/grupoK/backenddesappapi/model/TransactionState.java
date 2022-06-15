package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public abstract class TransactionState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stateId;
    @OneToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name = "transactionId", nullable = true, insertable=false, updatable=true)
    protected Transaction transaction;

    public Integer getId(){
        return this.stateId;
    }

    public TransactionState(Transaction transaction){
        this.transaction = transaction;
    }

    public TransactionState(){
    }

    public void setTransaction(Transaction transaction){
        this.transaction = transaction;
    }

    public Transaction getTransaction(){
        return this.transaction;
    }

    public abstract void transferTake();
    public abstract void transferReceived();
    public abstract void cancelTransaction(ModelUser user);
    public abstract boolean isPublished();
    public abstract boolean isTaken();
    public abstract boolean isReceived();
}
