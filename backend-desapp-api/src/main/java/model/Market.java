package model;

import java.util.List;
import java.util.stream.Collectors;

public class Market {
 
    private List<Transaction> transactions;
//User 
//OperationQuantity: (A que hace referencia)
//-Popularity:

    public Integer getUserOperationsQuantity(String email) {
        return transactions.stream().filter(t -> t.getUser().getEmail() == email).collect(Collectors.toList()).size();
    }
}
