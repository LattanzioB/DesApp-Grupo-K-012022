package ar.edu.unq.desapp.grupoK.backenddesappapi.dtos;

public class TakeTransactionDto {
    private Integer consumerId;
    private Integer transactionId;
    
    public Integer getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(Integer consumerId) {
        this.consumerId = consumerId;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }

    public TakeTransactionDto() {
    }

    

}
