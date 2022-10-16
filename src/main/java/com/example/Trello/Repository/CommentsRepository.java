package com.example.Trello.Repository;

import com.example.Trello.Entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CommentsRepository extends JpaRepository<Comments,Long> {
}
