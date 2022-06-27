package ar.edu.unq.desapp.grupoK.backenddesappapi.webservice;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.desapp.grupoK.backenddesappapi.dtos.UserDto;
import ar.edu.unq.desapp.grupoK.backenddesappapi.model.ModelUser;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.UserService;


@Controller
@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @GetMapping
    public List<ModelUser> getUsers(){
        return (List<ModelUser>) userService.getUsers();
    }

    @GetMapping(value = "/{id}")
    public Optional<ModelUser> getUserByID(Integer id){
        return (Optional<ModelUser>) userService.getUser(id);
    }

    @PostMapping()
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto newUserDto) throws ServerException{

        UserDto userResponse = userService.createUser(newUserDto);


        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);

    }
}
