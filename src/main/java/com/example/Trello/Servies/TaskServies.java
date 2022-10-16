package com.example.Trello.Servies;

import com.example.Trello.Entity.Tasks;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TaskServies {
    List<Tasks> allTasks();
    Tasks addTask(Tasks tasks);
    Tasks saveTask(Tasks tasks);
    void deleteTask(Tasks tasks);
    Tasks getTask(Long id);


}
