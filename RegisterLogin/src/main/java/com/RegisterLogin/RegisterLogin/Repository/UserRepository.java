package com.RegisterLogin.RegisterLogin.Repository;

import com.RegisterLogin.RegisterLogin.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    public Optional<User> findByUsername(String username);

    public User findByUserId(String userId);

}
