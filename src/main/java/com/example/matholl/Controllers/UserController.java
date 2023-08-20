package com.example.matholl.Controllers;

import com.example.matholl.Persistence.Entities.User;
import com.example.matholl.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

// TODO: Remember CORS for localhost and live
@RestController
@CrossOrigin(origins = {"http://localhost:4200", "https://mathollfrontend-production.up.railway.app/"})
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @PostMapping(value = "/login")
    public boolean loginUserGET(@RequestBody String userEmail, String password) {
        System.out.println(userEmail);
        System.out.println(password);
        try {
            User user = userService.findByEmail(userEmail);
            return user.getPassword().equals(password);
        }
        catch (Exception e) {
            return false;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOutUser(HttpSession session) {
        session.removeAttribute("userLoggedIn");
        return "redirect:/";
    }
}
