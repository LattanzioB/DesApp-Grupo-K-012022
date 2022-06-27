package ar.edu.unq.desapp.grupoK.backenddesappapi.dtos;

public class ProcessTransactionDto {
    private Integer userId;
    private Integer transactionId;
    
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userIdd) {
        this.userId = userIdd;
    }

    public Integer getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Integer transactionIdd) {
        this.transactionId = transactionIdd;
    }

    public ProcessTransactionDto() {
    }

    

}
