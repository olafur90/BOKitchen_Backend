package com.example.matholl.Services.Implementation;


import com.example.matholl.Persistence.Entities.Ingredient;
import com.example.matholl.Persistence.Repositories.IngredientRepository;
import com.example.matholl.Services.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientServiceImplementation implements IngredientService {


    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientServiceImplementation(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient save(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> findIngredientsByRecipeId(long id) {
        return ingredientRepository.findIngredientsByRecipeID(id);
    }
}
