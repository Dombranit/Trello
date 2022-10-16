package com.example.Trello.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_tasks")
public class Tasks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id")
    Long id;
    @Column(name = "title")
    String title;
    @Column(name = "description")
    String description; // TEXT
    @Column(name = "status")
    int status; // 0 - todo, 1 - intest, 2 - done, 3 - failed
    @ManyToOne(fetch = FetchType.LAZY)
    Folders folder; // Many To One
}
