package com.updatedparceltracker.services;

import com.updatedparceltracker.dto.JwtRequest;
import com.updatedparceltracker.dto.JwtResponse;
import com.updatedparceltracker.dto.RegisterDto;
import com.updatedparceltracker.model.Roles;
import com.updatedparceltracker.model.User;
import com.updatedparceltracker.repository.RoleRepository;
import com.updatedparceltracker.repository.UserRepository;
import com.updatedparceltracker.security.CustomUserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterLoginControllerService {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private CustomUserDetailService customUserDetailService;
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private PasswordEncoder passwordEncoder;
  final Logger logger=LoggerFactory.getLogger(RegisterLoginControllerService.class);
  public ResponseEntity<String> userRegister( RegisterDto registerdto){
    if(userRepository.existsByEmail(registerdto.getEmail())){
      logger.error("{} email is already taken",registerdto.getEmail());
      return new ResponseEntity<>("email is already taken", HttpStatus.BAD_REQUEST);
    }
    User user=new User();
    user.setEmail(registerdto.getEmail());
    user.setName(registerdto.getName());
    user.setPhone(registerdto.getPhone());
    user.setPassword(passwordEncoder.encode(registerdto.getPassword()));
    Roles roles = roleRepository.findByName("ROLE_USER").orElseThrow(()->new UsernameNotFoundException("role exception"));
    user.setRoles(roles);
    userRepository.save(user);
    logger.info("user {} registered successfully",registerdto.getEmail());
    return new ResponseEntity<>("user registered successfully",HttpStatus.OK);
  }
  public ResponseEntity<JwtResponse> loginUser(JwtRequest jwtRequest){
    try{
      this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(),jwtRequest.getPassword()));
      logger.info("SuccessFully login by {}",jwtRequest.getEmail());
    }catch(AuthenticationException e){
      logger.error("bad credentials given by {}",jwtRequest.getEmail());
      return  new ResponseEntity<>(new JwtResponse(e.getMessage()),HttpStatus.UNAUTHORIZED);
    }
    String token=this.jwtService.generateToken(jwtRequest.getEmail());
    return ResponseEntity.ok(new JwtResponse(token));
  }
}
