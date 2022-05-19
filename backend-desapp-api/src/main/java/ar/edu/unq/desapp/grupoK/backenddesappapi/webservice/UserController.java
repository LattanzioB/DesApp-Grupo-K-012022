package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.UserService;

@RestController("/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}")
    public List<User> getUsers(){
        return (List<User>) userService.getUsers();
    }
}
