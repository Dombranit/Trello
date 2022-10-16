package com.example.Trello.Entity;

import com.example.Trello.Entity.Security.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_folders")
public class Folders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    Long id;
    @Column(name = "name")
    String name;
    @ManyToMany(fetch = FetchType.LAZY)
    List<TaskCategories> categoriesList;
    @ManyToOne(fetch = FetchType.EAGER)
    Users users;
}
