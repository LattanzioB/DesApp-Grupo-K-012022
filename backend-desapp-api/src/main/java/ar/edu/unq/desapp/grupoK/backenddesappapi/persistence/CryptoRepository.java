package ar.edu.unq.desapp.grupoK.backenddesappapi.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Crypto;

@Repository
public interface CryptoRepository extends CrudRepository<Crypto, Integer> {
/* 
    @Query(value = "SELECT * FROM CRYPTO c where c.NAME = ?#{[0]}, nativeQuery = true")
    public List<Crypto> findByName(@Param("name") String name);*/
}