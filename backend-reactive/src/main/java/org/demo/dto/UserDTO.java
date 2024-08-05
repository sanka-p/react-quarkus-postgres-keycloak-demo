package org.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private Long id;
    @NotNull(message="First name cannot be empty")
    private String firstName;
    private String lastName;
    @Min(value = 8, message = "Password cannot be less than 8 characters")
    private String password;
    @Email(message="Invalid email address")
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
}
