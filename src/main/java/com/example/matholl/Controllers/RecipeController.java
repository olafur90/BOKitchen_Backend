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

import java.io.IOException;
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
    public String newRecipePOST(@RequestParam("foodType") String foodType, @RequestParam("longDescription") String longDescription, String name, String description, String timetocook, String numberofpeople, @RequestParam("imageUrl") String imageUrl) throws IOException {
        Recipe recipe = new Recipe("", name, description, timetocook, numberofpeople, foodType);
        recipe.setDescription(longDescription);
        if (!imageUrl.isEmpty()) {
            recipe.setImageLink(imageUrl);
        } else {
            recipe.setImageLink("/img/chicken.jpg");
        }
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
    public String editRecipe(@PathVariable("id") String id, Model model) {
        Recipe recipe = recipeService.findRecipeByID(Long.parseLong(id));
        model.addAttribute("recipe", recipe);
        return "editRecipe";
    }

    @RequestMapping(value = "/uppskriftir/{foodtype}/{id}/editRecipe", method = RequestMethod.POST)
    public String editRecipePOST(@PathVariable("id") String id, @RequestParam("name") String name, String description, String timetocook, String longDescription, String numberofpeople, String imageUrl, String foodType) {
        Recipe recipe = recipeService.findRecipeByID(Long.parseLong(id));
        recipe.setName(name);
        recipe.setShortDescription(description);
        recipe.setTimeToCook(timetocook);
        recipe.setDescription(longDescription);
        recipe.setForNumberOfPeople(numberofpeople);
        recipe.setImageLink(imageUrl);
        recipe.setFoodType(foodType);
        recipeService.save(recipe);
        return "redirect:/uppskriftir/{foodtype}/{id}";
    }

}
