package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return (List<User>) userService.getUsers();
    }

    @GetMapping(value = "/{id}")
    public Optional<User> getUserByID(Integer id){
        return (Optional<User>) userService.getUser(id);
    }

    @PostMapping()
    public ResponseEntity<User> create(@RequestBody User newUser) throws ServerException{
        User user = userService.save(newUser);
        if (user == null) {
            throw new ServerException(null);
        } else {

            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
    }
}
