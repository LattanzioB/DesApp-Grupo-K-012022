package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class TransactionFrame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name = "transactionId", nullable = true, insertable=false, updatable=true)
    private Transaction transaction;
    private Date publishedHour;
    private Integer operationsQuantity;
    private Integer popularity;

    public TransactionFrame(Transaction transaction){
        this.transaction = transaction;
        this.publishedHour = new Date();
        this.operationsQuantity = this.getUserOperations(transaction);
        this.popularity = this.getUserPopularity();
    }

    public TransactionFrame(){

    }
    private Integer getUserPopularity() {

        return this.transaction.getPublisher().getPopularity();
    }

    private Integer getUserOperations(Transaction transaction){
        return transaction.getPublisher().operationsQuantity();
    }

}
