package com.example.Trello.Controller;

import com.example.Trello.Entity.Security.Users;
import com.example.Trello.Servies.Security.UserServies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SecurityController {

    @Autowired
    private UserServies userServies;

    @GetMapping(value = "/403")
    private String get403(Model model) {
        model.addAttribute("currentUser", getUserData());
        return "Security/403";
    }

    @GetMapping(value = "/login")
    private String login(Model model) {
        model.addAttribute("currentUser", getUserData());
        return "Security/login";
    }

    @GetMapping(value = "/profile")
    @PreAuthorize("isAuthenticated()")
    public String profile(Model model) {
        model.addAttribute("currentUser", getUserData());
        return "Security/Profile";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("currentUser", getUserData());
        return "Security/register";
    }

    @PostMapping(value = "/register")
    public String register(@RequestParam(name = "user_email") String email,
                           @RequestParam(name = "user_password") String password,
                           @RequestParam(name = "re_user_password") String repassword,
                           @RequestParam(name = "user_fullname") String fullname) {
        if (password.equals(repassword)) {
            Users newUser = new Users();
            newUser.setFullname(fullname);
            newUser.setPassword(password);
            newUser.setEmail(email);
            if (userServies.createUser(newUser) != null) {
                return "redirect:register?success";
            }
        }
        return "redirect:register?error";
    }


    private Users getUserData() {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            User secUser = (User) authentication.getPrincipal();
            Users myUser= userServies.getUserByEmail(secUser.getUsername());
            return myUser;
        }
        return null;
    }

}
