package com.example.Trello.Servies.Implements;

import com.example.Trello.Entity.Comments;
import com.example.Trello.Repository.CommentsRepository;
import com.example.Trello.Servies.CommentsServies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CommentsImpl implements CommentsServies {

    @Autowired
    private CommentsRepository repository;

    @Override
    public List<Comments> allComments() {
        return repository.findAll();
    }

    @Override
    public Comments addComment(Comments comments) {
        return repository.save(comments);
    }

    @Override
    public Comments saveComment(Comments comments) {
        return repository.save(comments);
    }

    @Override
    public void deleteComment(Comments comments) {
        repository.delete(comments);
    }

    @Override
    public Comments getComment(Long id) {
        return repository.getReferenceById(id);
    }
}
