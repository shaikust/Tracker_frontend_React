package com.updatedparceltracker.security;

import com.updatedparceltracker.model.Roles;
import com.updatedparceltracker.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.updatedparceltracker.repository.UserRepository;

import java.util.Collection;
import java.util.Collections;

@Component
public class CustomUserDetailService implements UserDetailsService {
private  final UserRepository userRepo;

  public CustomUserDetailService(UserRepository userRepository) {
    this.userRepo = userRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

   User user = userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user is not found" + email));
    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthority(user.getRoles()));
  }
  private Collection<? extends GrantedAuthority> mapRolesToAuthority(Roles roles) {
    return Collections.singleton(new SimpleGrantedAuthority(roles.getName()));
  }
}
