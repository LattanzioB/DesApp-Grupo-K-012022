package ar.edu.unq.desapp.grupoK.backenddesappapi.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TransactionStateAssigner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(cascade = {CascadeType.ALL})
    private PublishedState publishedState;
    @OneToOne(cascade = {CascadeType.ALL})
    private TakenState takenState;
    @OneToOne(cascade = {CascadeType.ALL})
    private ReceivedState receivedState;

    public TransactionStateAssigner(){
        publishedState = new PublishedState();
        takenState = new TakenState();
        receivedState = new ReceivedState();
    }
    
    public TransactionState asigneState(Integer n){
        TransactionState stateToAssign= null;
        if(n == 1){
            stateToAssign = publishedState;
        }
            
        if(n == 2) {
            stateToAssign = takenState;
        } 
        if (n == 3) {
            stateToAssign = receivedState;
        }

        return stateToAssign;
    }
}
