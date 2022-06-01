package ar.edu.unq.desapp.grupoK.backenddesappapi.dtos;

public class TransactionDto {


    private String cryptoName;
    private double quantity;
    private Integer publisherId;
    private String operationType;
    
    public TransactionDto() {
    }

    public String getCryptoName() {
        return this.cryptoName;
    }

    public void setCryptoName(String cryptoName) {
        this.cryptoName = cryptoName;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }



    
}
