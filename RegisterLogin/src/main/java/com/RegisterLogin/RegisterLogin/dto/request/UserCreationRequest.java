package com.RegisterLogin.RegisterLogin.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {

     String firstName;
     String lastName;

    @Size(min = 3, message = "USERNAME_INVALID")
    @NotNull
     String username;
     String img;

    @DateTimeFormat
     LocalDate dob;
     boolean gender;
     String address;

    @Size(min = 8, message = "PASSWORD_INVALID")
    @NotNull
     String password;

    @Email
     String email;
}
