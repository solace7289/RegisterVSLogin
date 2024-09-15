package com.RegisterLogin.RegisterLogin.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateRequest {
     String firstName;
     String lastName;
     String img;
     LocalDate dob;
     boolean gender;
     String address;

    @Size(min = 8, message = "Password must be at least 8 characters!")
    @NotNull
    String password;
    String email;

}
