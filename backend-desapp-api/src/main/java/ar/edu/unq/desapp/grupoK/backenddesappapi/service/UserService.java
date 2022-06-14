package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.dtos.TransactionDto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public Optional<User> getUser(Integer id){
        return userRepository.findById(id);
    }

    @Transactional
    public User save(User newUser) {
        //VALIDACION
        return userRepository.save(newUser);
    }

    public void addTransaction(User user, Transaction transaction) {
        userRepository.save(user);
    }



}
