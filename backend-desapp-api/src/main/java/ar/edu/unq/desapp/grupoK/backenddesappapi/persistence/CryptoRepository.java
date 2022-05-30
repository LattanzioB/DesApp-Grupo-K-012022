package ar.edu.unq.desapp.grupoK.backenddesappapi.persistence;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Crypto;

public interface CryptoRepository extends CrudRepository<Crypto, Integer> {
}