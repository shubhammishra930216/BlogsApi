package com.shubhammishra.blogsapi.repositories;

import com.shubhammishra.blogsapi.entiities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmailId(String emailid);
}
