package com.example.matholl.Persistence.Repositories;

import com.example.matholl.Persistence.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findByID(long id);
    public List<User> findAll();
    public User findByUsername(String username);
    public void delete(User user);
    public User findByEmail(String email);
}
