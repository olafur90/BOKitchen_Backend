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
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

/***************************************************************************
 *  Nafn     : Ólafur Pálsson
 *  T-póstur : olp10@hi.is
 *
 *  Lýsing   :
 *
 *
 ****************************************************************************/

@Controller
public class NavController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    @Autowired
    public NavController(RecipeService recipeService, IngredientService ingredientService) {
        this.ingredientService = ingredientService;
        this.recipeService = recipeService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goHome() {
        // CreateDummyData();
        return "home";
    }

    @RequestMapping(value = "uppskriftir", method = RequestMethod.GET)
    public String goToRecipes() {
        return "redirect:/";
    }

    @RequestMapping(value = "uppskriftir/{foodtype}", method = RequestMethod.GET)
    public String goToFoodType(@PathVariable("foodtype") String foodType, Model model, HttpSession session) {
        session.getAttribute("userLoggedIn");
        // CreateDummyData();
        model.addAttribute("foodtype", foodType);
        List<Recipe> recipes = recipeService.findRecipesByFoodType(foodType);
        model.addAttribute("recipes", recipes);
        return "foodtype";
    }

    @RequestMapping(value = "newRecipe", method = RequestMethod.GET)
    public String createNewRecipe(Model model) {
        model.addAttribute("recipe", new Recipe());
        return "newRecipe";
    }

    @RequestMapping(value = "dummyData", method = RequestMethod.GET)
    public String dummyData() {
        
        return "redirect:/";
    }

    public void CreateDummyData() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("", "Mexíkóskt kjúklingalasagna","Geggjað gott kjúklingalasagna","50-60 mínútur", "Fyrir 2-3","kjuklingur"));
        recipes.add(new Recipe("","Mexíkóosta kjúklingur","Geggjað góður kjúlli, gott að hafa grjón með","40 mínútur", "Fyrir 2","kjuklingur"));
        recipes.add(new Recipe("","Lasagna","Hef reynt að herma eftir mömmu lasagna, en þetta er mín útgáfa af því","40-80 mínútur", "Fyrir 3-4","hakk"));
        recipes.add(new Recipe("","Karrí fiskur","Góður karrýfiskur. Kartöflur og grjón með","30-40 mínútur", "Fyrir 2","fiskur"));
        recipes.add(new Recipe("","Soðin ýsa","Týpíski heimilisfiskurinn, geggjað fyrir börn líka","30 mínútur", "Fyrir 2","fiskur"));
        recipes.add(new Recipe("","Geggjuð gúllassúpa","Frábær gúllassúpa fengin frá Evu Laufey","120+ mínútur", "Fyrir 4+","supur"));
        recipes.add(new Recipe("","Pestó pasta","Geggjað pestó pasta sem birna kenndi mér","30 mínútur", "Fyrir 2","pasta"));
        recipes.add(new Recipe("","Beikon rjóma pasta","Beikonpastað góða klikkar aldrei","50 mínútur", "Fyrir 3-4","pasta"));
        for (Recipe recipe : recipes) {
            Ingredient ingredient = new Ingredient("Beikon", recipe);
            ingredientService.save(ingredient);
            recipe.addIngredient(ingredient);
            recipeService.save(recipe);
        }
    }
}
