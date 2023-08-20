/*
 * Copyright (c) BÓ Kitchen -  2023
 *
 */

package com.example.matholl.Services;

import com.example.matholl.Persistence.Entities.Comment;

import java.util.List;

public interface CommentService {
    public Comment save(Comment comment);

    public List<Comment> findByRecipeID(long id);
}
