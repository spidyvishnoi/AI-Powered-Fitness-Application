package com.fitness.userservice.services;


import com.fitness.userservice.Repository.UserRepository;
import com.fitness.userservice.dto.RegisterRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.models.User;
import jdk.jshell.spi.ExecutionControl;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.core.support.RepositoryMethodInvocationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServices {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserResponse register(RegisterRequest request){

        if(userRepository.existsByEmail(request.getEmail())){
           throw new RuntimeException("Email already exists");
        }

       User user = new  User();
       user.setEmail(request.getEmail());
       user.setPassword(passwordEncoder.encode(request.getPassword()));
       user.setFirstname(request.getFirstname());
       user.setLastname(request.getLastname());

        User savedUser = userRepository.save(user);

        UserResponse userResponse = new UserResponse();
       userResponse.setId(savedUser.getId());
       userResponse.setFirstname(savedUser.getFirstname());
       userResponse.setLastname(savedUser.getLastname());
       userResponse.setEmail(savedUser.getEmail());
       userResponse.setCreatedAt(savedUser.getCreatedAt());
       userResponse.setUpdatedAt(savedUser.getUpdatedAt());
       return userResponse;
    }

    public UserResponse getUserProfile(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User Not Found"));
        UserResponse userResponse = new UserResponse();
        userResponse.setFirstname(user.getFirstname());
        userResponse.setLastname(user.getLastname());
        userResponse.setEmail(user.getEmail());
        userResponse.setCreatedAt(user.getCreatedAt());
        userResponse.setUpdatedAt(user.getUpdatedAt());
        return userResponse;
    }
}
