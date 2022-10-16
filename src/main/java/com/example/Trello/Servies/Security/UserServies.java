package com.example.Trello.Servies.Security;

import com.example.Trello.Entity.Security.Users;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

public interface UserServies extends UserDetailsService {
    Users getUserByEmail(String email);
    Users createUser(Users users);
    Users  getUserData();

    Users getUser(Long id);
}
