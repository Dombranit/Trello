package com.example.Trello.Repository.Security;

import com.example.Trello.Entity.Security.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Roles,Long> {

         Roles findByRole(String role);

}
