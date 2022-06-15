package ar.edu.unq.desapp.grupoK.backenddesappapi.model;

import javax.persistence.*;

import ar.edu.unq.desapp.grupoK.backenddesappapi.exception.InvalidUserReceivingTransferException;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer userId;
    


    private String name;
    private String surname;

    @Column(unique = true)
    private String email;

    private String adress;
    private String password;
    private Integer cvu;
    private Integer wallet;
    private Integer popularity;
    @OneToMany
    @JoinColumn(name = "userId", nullable = true, insertable=false, updatable=true)
    private List<Transaction> transactionsPublished;
    @OneToMany
    @JoinColumn(name = "userId", nullable = true, insertable=false, updatable=true)
    private List<Transaction> transactionsTaken;

    public User(){
        
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCvu() {
        return cvu;
    }

    public void setCvu(Integer cvu) {
        this.cvu = cvu;
    }

    public Integer getWallet() {
        return wallet;
    }

    public void setWallet(Integer wallet) {
        this.wallet = wallet;
    }

    public User(String name, String surname, String email, String adress, String password, Integer cvu, Integer wallet){
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.adress = adress;
        this.password = password;
        this.cvu = cvu;
        this.wallet = wallet;
        this.popularity = 0;
        this.transactionsPublished  = new ArrayList<Transaction>();
        this.transactionsTaken  = new ArrayList<Transaction>();
    }

    public Integer getPopularity() {
        return this.popularity;
    }

    public String getEmail() {
        return this.email;
    }

    public List<Transaction> getTransactionsPublished() {
        return this.transactionsPublished;
    }

    public List<Transaction> getTransactionsTaken() {
        return this.transactionsTaken;
    }

    public void addTransaction(Transaction transaction) {
        this.transactionsPublished.add(transaction);
    }

    public Integer operationsQuantity() {
        return this.transactionsPublished.size();
    }

    public void takeTransaction(Transaction transaction) {
        this.transactionsTaken.add(transaction);
        transaction.takeTransaction(this);
    }

    public void transferReceived(Transaction transaction) throws InvalidUserReceivingTransferException{
        if (transaction.getPublisher().getEmail() == this.getEmail()){
            transaction.transferReceived();
        }else{
            throw new InvalidUserReceivingTransferException("Error this user cannot set this transfer as received");
        }
        
    }

    public void cancelTransaction(Transaction transaction) {
        transaction.cancelTransaction(this);
        
    }

    public void deleteTransactionPublished(Transaction transaction) {
        this.transactionsPublished.remove(transaction);
    }

    public void discountPoints() {
        this.popularity -= 20;
    }

    public void deleteTransactionTaken(Transaction transaction) {
        this.transactionsTaken.remove(transaction);
    }
}
