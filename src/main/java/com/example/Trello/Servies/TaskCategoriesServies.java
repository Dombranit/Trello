package com.example.Trello.Servies;

import com.example.Trello.Entity.TaskCategories;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TaskCategoriesServies {
    List<TaskCategories> allTaskCat();
    TaskCategories addTaskCat(TaskCategories taskCategories);
    TaskCategories saveTaskCat(TaskCategories taskCategories);
    TaskCategories getTaskCat(Long id);
    void deleteTaskCat (TaskCategories taskCategories);


}