package com.example.Trello.Servies;

import com.example.Trello.Entity.Comments;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CommentsServies {
    List<Comments> allComments();
    Comments addComment(Comments comments);
    Comments saveComment(Comments comments);
    void deleteComment (Comments comments);
    Comments getComment(Long id);
}
