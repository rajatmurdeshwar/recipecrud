package com.example.recipecrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.recipecrud.dao.RecipeRepository;
import com.example.recipecrud.entity.Recipe;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public void save(Recipe theRecipe) {
        recipeRepository.save(theRecipe);
    }

    public Recipe findById(Integer id) {
        return recipeRepository.findById(id).orElse(null);
    }

    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    public void update(Recipe theRecipe) {
        recipeRepository.save(theRecipe); // `save` also updates if ID exists
    }

    public void delete(Integer id) {
        recipeRepository.deleteById(id);
    }

    public int deleteAll() {
        long countBefore = recipeRepository.count();
        recipeRepository.deleteAll();
        return (int) countBefore;
    }

    @Override
    public List<Recipe> filterRecipes(Boolean vegan, Boolean glutenFree, Boolean dairyFree) {
        return recipeRepository.findByFilters(vegan, glutenFree, dairyFree);
    }
}
