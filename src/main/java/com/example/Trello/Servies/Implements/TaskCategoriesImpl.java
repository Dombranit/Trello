package com.example.Trello.Servies.Implements;

import com.example.Trello.Entity.TaskCategories;
import com.example.Trello.Repository.TaskCategoriesRepository;
import com.example.Trello.Servies.TaskCategoriesServies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskCategoriesImpl implements TaskCategoriesServies {

    @Autowired
    private TaskCategoriesRepository repository;

    @Override
    public List<TaskCategories> allTaskCat() {
        return repository.findAll();
    }

    @Override
    public TaskCategories addTaskCat(TaskCategories taskCategories) {
        return repository.save(taskCategories);
    }

    @Override
    public TaskCategories saveTaskCat(TaskCategories taskCategories) {
        return repository.save(taskCategories);
    }

    @Override
    public TaskCategories getTaskCat(Long id) {
        return repository.getReferenceById(id);
    }

    @Override
    public void deleteTaskCat(TaskCategories taskCategories) {
        repository.delete(taskCategories);
    }
}
