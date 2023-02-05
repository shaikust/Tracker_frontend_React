package com.updatedparceltracker;

import com.updatedparceltracker.dto.RegisterDto;
import com.updatedparceltracker.model.Roles;
import com.updatedparceltracker.model.User;
import com.updatedparceltracker.repository.ParcelRepository;
import com.updatedparceltracker.repository.RoleRepository;
import com.updatedparceltracker.repository.TrackingRepository;
import com.updatedparceltracker.repository.UserRepository;
import com.updatedparceltracker.services.ParcelControllerService;
import com.updatedparceltracker.services.RegisterLoginControllerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {RegisterLoginTest.class})
public class RegisterLoginTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private RegisterLoginControllerService controllerService;

    public List<User> user=new ArrayList<>();
    public Set<Roles> roles;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void testUserRegisterSuccess() {
        Roles roles= new Roles(1,"ROLE_USER",user);
        User user = new User(2,"test@example.com","Test User",123456,"password",roles);
        RegisterDto registerDto=new RegisterDto("test@example.com","Test User",123456,"password");

        when(roleRepository.findByName("ROLE_USER")).thenReturn(Optional.of(roles));
        when(userRepository.save(user)).thenReturn(null);
        when(passwordEncoder.encode(registerDto.getPassword())).thenReturn(null);
        assertEquals(ResponseEntity.ok(String.format("user registered successfully")),controllerService.userRegister(registerDto));
    }

}
