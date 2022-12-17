package com.example.matholl.Controllers;

import com.example.matholl.FileUploadUtil;
import com.example.matholl.Persistence.Entities.Ingredient;
import com.example.matholl.Persistence.Entities.Recipe;
import com.example.matholl.Persistence.Repositories.RecipeRepository;
import com.example.matholl.Services.IngredientService;
import com.example.matholl.Services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class RecipeController {
    RecipeService recipeService;
    IngredientService ingredientService;

    @Autowired
    public RecipeController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @RequestMapping(value = "/newRecipe", method = RequestMethod.POST)
    public String newRecipePOST(Recipe recipe) {
        recipeService.save(recipe);
        return "redirect:/";
    }

    @RequestMapping(value = "/uppskriftir/{foodtype}/{id}", method = RequestMethod.GET)
    public String getAllRecipes(String foodType, Model model, @PathVariable("id") String id) {
        Recipe recipe = recipeService.findRecipeByID(Long.parseLong(id));
        List<Ingredient> ingredients = ingredientService.findIngredientsByRecipeId(Long.parseLong(id));
        model.addAttribute("recipe", recipe);
        model.addAttribute("ingredients", ingredients);
        return "course";
    }

    @RequestMapping(value = "/uppskriftir/{foodtype}/{id}/editRecipe", method = RequestMethod.GET)
    public String editRecipe(@PathVariable("id") String id, Model model, HttpSession session) {
        if (session.getAttribute("userLoggedIn") == null) {
            System.err.println(session.getAttribute("userLoggedIn"));
            return "redirect:/login";
        }
        Recipe recipe = recipeService.findRecipeByID(Long.parseLong(id));
        model.addAttribute("recipe", recipe);
        return "editRecipe";
    }

    @RequestMapping(value = "/uppskriftir/{foodtype}/{id}/editRecipe", method = RequestMethod.POST)
    public String editRecipePOST(Recipe recipe, @PathVariable("id") String id) {
        recipe.setID(Long.parseLong(id));
        recipeService.save(recipe);
        return "redirect:/uppskriftir/{foodtype}/{id}";
    }

}
