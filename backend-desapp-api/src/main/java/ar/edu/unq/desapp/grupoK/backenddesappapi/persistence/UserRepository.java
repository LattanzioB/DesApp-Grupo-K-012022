package ar.edu.unq.desapp.grupoK.backenddesappapi.persistence;


import org.springframework.data.repository.CrudRepository;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
}