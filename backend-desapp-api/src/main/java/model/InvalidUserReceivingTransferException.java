package model;

public class InvalidUserReceivingTransferException extends Exception {
    
    public InvalidUserReceivingTransferException(String errorMesage){
        super(errorMesage);
    }
}
