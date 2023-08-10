package com.example.matholl.Controllers;

import com.example.matholl.Persistence.Entities.Ingredient;
import com.example.matholl.Persistence.Entities.Recipe;
import com.example.matholl.Services.IngredientService;
import com.example.matholl.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class NavController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    @Autowired
    public NavController(RecipeService recipeService, IngredientService ingredientService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
    }
}
