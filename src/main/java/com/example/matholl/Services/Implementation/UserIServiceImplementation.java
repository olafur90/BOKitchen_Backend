package com.example.matholl.Services.Implementation;

import com.example.matholl.Persistence.Entities.User;
import com.example.matholl.Persistence.Repositories.UserRepository;
import com.example.matholl.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserIServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserIServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByPreferredUsername(String username) {
        return null;
    }

    @Override
    public User findByID(long id) {
        return userRepository.findByID(id);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User login(User user) {
        return null;
    }
}
