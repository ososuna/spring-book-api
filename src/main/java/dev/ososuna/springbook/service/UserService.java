package dev.ososuna.springbook.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import dev.ososuna.springbook.model.LoginRequest;
import dev.ososuna.springbook.model.User;
import dev.ososuna.springbook.repository.UserRepository;

@Service
public class UserService {
  
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public List<User> getAllUsers() {
    return this.userRepository.findAllByActiveTrue();
  }

  public User getUserByEmail(String email) {
    return userRepository.findByEmailAndActiveTrue(email)
      .orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
      );
  }

  public User createUser(User user) {
    userRepository.findByEmailAndActiveTrue(user.getEmail()).ifPresent(action -> {
      throw new ResponseStatusException(
        HttpStatus.BAD_REQUEST,
        "User with email " + user.getEmail() + " already exists"
      );
    });
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    return userRepository.save(user);
  }

  public User loginUser(LoginRequest loginRequest) {
    return userRepository.findByEmailAndPasswordAndActiveTrue(
      loginRequest.getEmail(),
      loginRequest.getPassword()
    ).orElseThrow(() ->
      new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid credentials")
    );
  }

  public User updateUser(Long id, User user) {
    return userRepository.findByIdAndActiveTrue(id)
      .map(foundUser -> {
        foundUser.setFirstName(user.getFirstName());
        foundUser.setLastName(user.getLastName());
        foundUser.setEmail(user.getEmail());
        foundUser.setAge(user.getAge());
        foundUser.setProfileImageUrl(user.getProfileImageUrl());
        return userRepository.save(foundUser);
      })
      .orElseThrow(() ->
        new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
      );
  }

  public User deleteUser(Long id) {
    var user = userRepository.findById(id).orElseThrow(() ->
      new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found")
    );
    user.setActive(false);
    return userRepository.save(user);
  }
}
