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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL,optional = true)
    @JoinColumn(name = "frame", nullable = true, insertable=false, updatable=false)
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

    private Integer getUserPopularity() {

        return this.transaction.getPublisher().getPopularity();
    }

    private Integer getUserOperations(Transaction transaction){
        return transaction.getPublisher().operationsQuantity();
    }

}
