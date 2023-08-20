/*
 * Copyright (c) BÓ Kitchen -  2023
 *
 */

package com.example.matholl.Services.Implementation;

import com.example.matholl.Persistence.Entities.Comment;
import com.example.matholl.Persistence.Repositories.CommentRepository;
import com.example.matholl.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImplementation implements CommentService {
    CommentRepository commentRepository;

    @Autowired
    public CommentServiceImplementation(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment save(Comment comment) {
        return this.commentRepository.save(comment);
    }

    @Override
    public List<Comment> findByRecipeID(long id) {
        return this.commentRepository.findByRecipeID(id);
    }
}
