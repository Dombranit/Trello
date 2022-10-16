package com.example.Trello.Repository;

import com.example.Trello.Entity.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TaskRepository extends JpaRepository<Tasks,Long> {
}
