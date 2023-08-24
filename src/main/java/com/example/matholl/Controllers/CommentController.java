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

/**
 * The controller for the comment API
 */
@RestController
@RequestMapping("comments")
@CrossOrigin(origins = {"http://localhost:4200", "https://mathollfrontend-production.up.railway.app/"})
public class CommentController {
    private CommentService commentService;

    /**
     * The constructor for the controller
     * @param commentService The comment service that will be used in the controller
     */
    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /**
     * Finds all comments for a recipe
     * @param recipeId The id of the recipe to find comments for
     * @return A list of comments matching the recipe id
     */
    @GetMapping(value = "/{recipeId}")
    public List<Comment> findByRecipeId(String recipeId) {
        return commentService.findByRecipeID(Long.parseLong(recipeId));
    }
}
