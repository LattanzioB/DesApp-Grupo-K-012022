package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import java.util.Date;

public class TransactionFrame {
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
