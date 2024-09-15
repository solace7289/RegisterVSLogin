package com.RegisterLogin.RegisterLogin.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.bind.DefaultValue;
//import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "User")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) //su dung UUID de khong bi trung lap
    @Column(name = "user_id")
    @ToString.Exclude //add toString to ignore error stackOverFlow
    String userId;

    @Column(name = "first_name")
    String firstName;

    @Column(name = "last_name")
    String lastName;

    @Column(name = "username")
    String username;

    @Column(name = "img")
    String img;

    @Column(name = "dob")
    LocalDate dob;

    @Column(name = "gender")
    boolean gender;

    @Column(name = "address")
    String address;

    @Column(name = "password")
    String password;

    @Column(name = "email")
    String email;

}
