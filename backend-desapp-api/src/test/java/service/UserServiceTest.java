package service;

import org.mockito.MockitoAnnotations;

import ar.edu.unq.desapp.grupoK.backenddesappapi.model.ModelUser;
import ar.edu.unq.desapp.grupoK.backenddesappapi.persistence.UserRepository;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.UserService;
import ar.edu.unq.desapp.grupoK.backenddesappapi.service.builders.UserBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testUserServiceFindAll() {
        MockitoAnnotations.initMocks(this);
        List<ModelUser> users = new ArrayList<>();
        users.add(UserBuilder.userwithName("Gonzalo").withSurName("Molina")
        .withAdress("Calle Falsa 123").withPassword("Perro10.")
        .withEmail("gmolina@gmail.com").withCvu(123456).withWallet(78910).build());
        users.add(UserBuilder.userwithName("Bruno").withSurName("Lattanzio")
        .withAdress("Calle Mitre 3214").withPassword("Gatito10.")
        .withEmail("blattanzio@gmail.com").withCvu(7891011).withWallet(36572944).build());
        when(userRepository.findAll()).thenReturn(users);
        Iterable<ModelUser> usersRecovered = userService.getUsers();
        assertEquals(2, StreamSupport.stream(usersRecovered.spliterator(), false).count());
        assertEquals(users, usersRecovered);
    }

}
