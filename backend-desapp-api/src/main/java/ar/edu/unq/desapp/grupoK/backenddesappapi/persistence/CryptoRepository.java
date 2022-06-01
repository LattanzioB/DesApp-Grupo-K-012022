package ar.edu.unq.desapp.grupoK.backenddesappapi.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Crypto;

public interface CryptoRepository extends CrudRepository<Crypto, Integer> {

    @Query(value = "SELECT * FROM Crypto c where c.name = :name",nativeQuery = true)
    public Optional<Crypto> findByName(@Param("name") String cryptoName);
}