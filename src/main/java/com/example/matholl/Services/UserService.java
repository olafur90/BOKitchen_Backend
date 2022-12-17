package com.example.matholl.Services;

import com.example.matholl.Persistence.Entities.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();
    public User findByUsername(String username);
    public User findByID(long id);
    public User findByEmail(String email);
    public User save(User user);
    public void delete(User user);
    public User login(User user);
}
