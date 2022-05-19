package ar.edu.unq.desapp.grupoK.backenddesappapi.exception;

public class InvalidUserReceivingTransferException extends Exception {
    
    public InvalidUserReceivingTransferException(String errorMesage){
        super(errorMesage);
    }
}
