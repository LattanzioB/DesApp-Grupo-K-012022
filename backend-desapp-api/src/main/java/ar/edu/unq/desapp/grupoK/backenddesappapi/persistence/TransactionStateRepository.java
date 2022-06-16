package ar.edu.unq.desapp.grupoK.backenddesappapi.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.TransactionState;

@Repository
public interface TransactionStateRepository extends CrudRepository<TransactionState, Integer> {

}