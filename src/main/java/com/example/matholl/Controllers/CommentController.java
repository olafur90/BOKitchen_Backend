/*
 * Copyright (c) BÓ Kitchen -  2023
 *
 */

package com.example.matholl.Controllers;

import com.example.matholl.Persistence.Entities.Comment;
import com.example.matholl.Services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("comments")
@CrossOrigin(origins = {"http://localhost:4200", "https://mathollfrontend-production.up.railway.app/"})
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/{recipeId}")
    public List<Comment> findByRecipeId(String recipeId) {
        return commentService.findByRecipeID(Long.parseLong(recipeId));
    }
}
