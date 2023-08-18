package com.example.matholl.Controllers;

import com.example.matholl.Persistence.Entities.User;
import com.example.matholl.Services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

// TODO: Remember CORS for localhost and live
@Controller
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) { this.userService = userService; }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginUserGET(HttpSession session, Model model) {
        session.getAttribute("userLoggedIn");
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginUserPOST(User user, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) return "redirect:/login";
        User exists = userService.login(user);
        if (exists != null)  {
            session.setAttribute("userLoggedIn", exists);
            model.addAttribute("userLoggedIn", exists);
            return "redirect:/uppskriftir/pasta";
        }
        // Returns to front page
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logOutUser(HttpSession session) {
        session.removeAttribute("userLoggedIn");
        return "redirect:/";
    }
}
