package com.RegisterLogin.RegisterLogin.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "User")
//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    @ToString.Exclude //add toString to ignore error stackOverFlow
    private int userId;

    @Column(name = "user_code")
    private String userCode;

    @Column(name = "username")
    private String username;

    @Column(name = "img")
    private String img;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "gender")
    private boolean gender;

    @Column(name = "address")
    private String address;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;



}
