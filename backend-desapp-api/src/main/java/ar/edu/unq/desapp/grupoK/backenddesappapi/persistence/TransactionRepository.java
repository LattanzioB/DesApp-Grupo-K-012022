package ar.edu.unq.desapp.grupoK.backenddesappapi.persistence;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
}