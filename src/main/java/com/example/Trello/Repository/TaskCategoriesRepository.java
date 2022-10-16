package com.example.Trello.Repository;

import com.example.Trello.Entity.TaskCategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TaskCategoriesRepository extends JpaRepository<TaskCategories,Long> {
}
