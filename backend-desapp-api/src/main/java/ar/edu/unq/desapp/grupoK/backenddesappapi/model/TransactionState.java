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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name = "transactionState", nullable = true, insertable=false, updatable=false)
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
