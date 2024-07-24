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
@CrossOrigin(origins = {"http://localhost:4200", "https://bokitchen.up.railway.app/"})
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    /* FIXME: Maybe OK to remove since we're using Auth0 for login
    @PostMapping(value = "/login")
    public boolean loginUserGET(@RequestBody String userEmail, String password) {
        try {
            User user = userService.findByEmail(userEmail);
            //return user.getPassword().equals(password);
            return false;
        }
        catch (Exception e) {
            return false;
        }
    }
     */

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOutUser(HttpSession session) {
        session.removeAttribute("userLoggedIn");
        return "redirect:/";
    }

    /*
    @PostMapping(value = "/register")
    public User registerUserGET(@RequestBody User user) {
        try {
            userService.save(user);
            return user;
        }
        catch (Exception e) {
            return null;
        }
    }
    */

}
