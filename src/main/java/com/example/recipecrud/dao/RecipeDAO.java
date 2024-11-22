package com.example.recipecrud.dao;

import java.util.List;

import com.example.recipecrud.entity.Recipe;

public interface RecipeDAO {

    void save(Recipe theRecipe);

    Recipe findById(Integer id);

    List<Recipe> findAll();

    void update(Recipe theRecipe);

    void delete(Integer id);

    int deleteAll();
    
}
