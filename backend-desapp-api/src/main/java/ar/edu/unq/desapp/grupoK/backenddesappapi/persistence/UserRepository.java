package ar.edu.unq.desapp.grupoK.backenddesappapi.persistence;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.ModelUser;

@Repository
public interface UserRepository extends CrudRepository<ModelUser, Integer> {
}
