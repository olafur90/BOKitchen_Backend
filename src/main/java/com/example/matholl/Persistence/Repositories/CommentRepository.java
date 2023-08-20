/*
 * Copyright (c) BÓ Kitchen -  2023
 *
 */

package com.example.matholl.Persistence.Repositories;

import com.example.matholl.Persistence.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment save(Comment comment);

    List<Comment> findByRecipeID(long id);
}
