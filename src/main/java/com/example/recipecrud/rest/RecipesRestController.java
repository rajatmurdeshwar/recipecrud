package com.example.recipecrud.rest;

import org.springframework.web.bind.annotation.RestController;

import com.example.recipecrud.dao.RecipeDAO;
import com.example.recipecrud.entity.Recipe;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
public class RecipesRestController {

    private RecipeDAO recipeDAO;

    public RecipesRestController(RecipeDAO theRecipeDao) {
        recipeDAO = theRecipeDao;
    }

    // add recipe to db
    @PostMapping("/")
    public ResponseEntity<String> addRecipe(@RequestBody Recipe theRecipe) {
        if (theRecipe == null) {
            return ResponseEntity.badRequest().body("Invalid Recipe Data");
        }
        recipeDAO.save(theRecipe);
        return ResponseEntity.status(HttpStatus.CREATED).body("Recipe added successfully!");
    }
    

    // get all recipes
    @GetMapping("/recipes")
    public ResponseEntity<List<Recipe>> getRecipe() {
        List<Recipe> recipes = recipeDAO.findAll();
        if (recipes.isEmpty()) {
            throw new RecipeNotFoundException("Recipes not found ");
        }
        return ResponseEntity.ok(recipes);
    }
    
}
