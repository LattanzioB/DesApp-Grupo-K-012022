package ar.edu.unq.desapp.grupoK.backenddesappapi.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unq.desapp.grupoK.backenddesappapi.dtos.UserDto;
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

    @Transactional
    public UserDto createUser(UserDto userDto){
        ModelUser user = new ModelUser(userDto.getName(), userDto.getSurname(), userDto.getEmail(), userDto.getAdress(), userDto.getPassword(), userDto.getCvu(), userDto.getWallet());
        userRepository.save(user);

        UserDto userResponse = new UserDto();
        userResponse.setName(userDto.getName());
        userResponse.setSurname(userDto.getSurname());
        userResponse.setEmail(userDto.getEmail());
        userResponse.setAdress(userDto.getAdress());
        userResponse.setCvu(userDto.getCvu());
        userResponse.setWallet( userDto.getWallet());
        return userResponse;
    }



}
