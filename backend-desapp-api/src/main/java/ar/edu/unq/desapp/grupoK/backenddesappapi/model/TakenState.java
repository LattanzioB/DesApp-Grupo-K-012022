package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TakenState extends TransactionState{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stateId;


    public TakenState() {
        super();
        
    }
    @Override
    public void transferTake(Transaction transaction) {
        //error
    }

    @Override
    public void transferReceived(Transaction transaction) {
        transaction.changeState(3);
        if (TimeUnit.MILLISECONDS.toSeconds((new Date().getTime() - transaction.getFrame().getPublishedHour().getTime())) < 1800 ){
            transaction.getConsumer().increasePoints(10);
            transaction.getPublisher().increasePoints(10);
        }else {
            transaction.getConsumer().increasePoints(5);
            transaction.getPublisher().increasePoints(5);
        }
    }

    @Override
    public void cancelTransaction(Transaction transaction, ModelUser user) {
        if (transaction.getPublisher().getEmail() == user.getEmail()){
            user.discountPoints();
            user.deleteTransactionPublished(transaction);
            transaction.getConsumer().deleteTransactionTaken(transaction);
        }else if (transaction.getConsumer().getEmail() == user.getEmail()){
            user.discountPoints();
            user.deleteTransactionTaken(transaction);   
            transaction.changeState(1);
        }
        
    }

    @Override
    public boolean isPublished(Transaction transaction) {
        return false;
    }

    @Override
    public boolean isTaken(Transaction transaction) {
        return true;
    }

    @Override
    public boolean isReceived(Transaction transaction) {
        return false;
    }
    
}
