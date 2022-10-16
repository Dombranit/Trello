package com.example.Trello.Servies.Implements;

import com.example.Trello.Entity.Tasks;
import com.example.Trello.Repository.TaskRepository;
import com.example.Trello.Servies.TaskServies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskImpl implements TaskServies {

    @Autowired
    private TaskRepository repository;

    @Override
    public List<Tasks> allTasks() {
        return repository.findAll();
    }

    @Override
    public Tasks addTask(Tasks tasks) {
        return repository.save(tasks);
    }

    @Override
    public Tasks saveTask(Tasks tasks) {
        return repository.save(tasks);
    }

    @Override
    public void deleteTask(Tasks tasks) {
        repository.delete(tasks);
    }

    @Override
    public Tasks getTask(Long id) {
        return repository.getReferenceById(id);
    }

}
