package com.example.Trello.Servies.Implements.Security;

import com.example.Trello.Entity.Security.Roles;
import com.example.Trello.Entity.Security.Users;
import com.example.Trello.Repository.Security.RoleRepository;
import com.example.Trello.Repository.Security.UserRepository;
import com.example.Trello.Servies.Security.UserServies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.ArrayList;

@Service
public class UserImpl implements UserServies {
    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Users getUser(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public Users getUserData() {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)){
                User secUser = (User) authentication.getPrincipal();
                Users myUser= getUserByEmail(secUser.getUsername());
                return myUser;
            }
            return null;
        }

    @Override
    public Users createUser(Users users) {
        Users checkUsers = repository.findByEmail(users.getEmail());
        if (checkUsers == null) {
            Roles role= roleRepository.findByRole("Role_User");
            if(role!=null){
                ArrayList<Roles> rolesArrayList = new ArrayList<>();
                rolesArrayList.add(role);
                users.setRoles(rolesArrayList);
                users.setPassword(passwordEncoder.encode(users.getPassword()));
                 return repository.save(users);

            }
        }
        return  null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users myUser=repository.findByEmail(username);
        if(myUser!=null){
            User secUser=new User(myUser.getEmail(),myUser.getPassword(),myUser.getRoles());
            return secUser;
        }
        throw new UsernameNotFoundException("User Not found");
    }

    @Override
    public Users getUserByEmail(String email) {
        return repository.findByEmail(email);
    }
}
