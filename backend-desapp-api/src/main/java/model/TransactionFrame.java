package model;

import java.util.Date;

public class TransactionFrame {
    private Transaction transaction;
    private Date publishedHour;
    private Integer operationsQuantity;
    private Integer popularity;

    public TransactionFrame(Transaction transaction, Market market){
        this.transaction = transaction;
        this.publishedHour = new Date();
        this.operationsQuantity = this.getUserOperations(market);
        this.popularity = this.getUserPopularity();
    }

    private Integer getUserPopularity() {

        return this.transaction.getUser().getPopularity();
    }

    private Integer getUserOperations(Market market){
        return market.getUserOperationsQuantity(this.transaction.getUser().getEmail());
    }

}
