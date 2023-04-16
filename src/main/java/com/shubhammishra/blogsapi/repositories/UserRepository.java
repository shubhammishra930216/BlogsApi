package com.shubhammishra.blogsapi.repositories;

import com.shubhammishra.blogsapi.entiities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
