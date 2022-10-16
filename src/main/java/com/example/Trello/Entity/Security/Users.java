package com.example.Trello.Entity.Security;

import com.example.Trello.Entity.Folders;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "email")
    private String email;
    @Column (name = "password")
    private String password;
    @Column(name = "full_name")
    private String fullname;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Roles> roles;
}
