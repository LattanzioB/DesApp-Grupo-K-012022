package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.ModelUser;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Iterable<ModelUser> getUsers(){
        return userRepository.findAll();
    }

    @Transactional
    public Optional<ModelUser> getUser(Integer id){
        return userRepository.findById(id);
    }

    @Transactional
    public ModelUser save(ModelUser newUser) {
        //VALIDACION
        return userRepository.save(newUser);
    }

    public void addTransaction(ModelUser user, Transaction transaction) {
        userRepository.save(user);
    }



}
