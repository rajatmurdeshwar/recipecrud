package com.example.recipecrud.service;

import java.util.List;
import com.example.recipecrud.entity.Recipe;

public interface RecipeService {
    List<Recipe> filterRecipes(Boolean vegan, Boolean glutenFree, Boolean dairyFree);
}


