package com.saksham.book_network.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotEmpty(message = "First Name is mandatory")
    @NotBlank(message = "First Name is mandatory")
    private String firstName;

    @NotEmpty(message = "Last Name is mandatory")
    @NotBlank(message = "Last Name is mandatory")
    private String lastName;

    @Email(message = "Email is not formatted")
    @NotEmpty(message = "Email is mandatory")
    @NotBlank(message = "Email is mandatory")
    private String email;

    @Email(message = "Password is not formatted")
    @NotEmpty(message = "Password is mandatory")
    @Size(min = 8, message = "Passowrd should be 8 characters long minimum")
    private String password;
}
