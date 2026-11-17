package com.fitness.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank(message = "Email is required")
    @Email(message="Invalid Email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 10 , message = "Password must have 6 characters")
    private String password;

    private String firstname;
    private String lastname;

}
