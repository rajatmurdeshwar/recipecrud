package com.example.recipecrud.rest;

import org.springframework.web.bind.annotation.RestController;

import com.example.recipecrud.JwtUtil;
import com.example.recipecrud.entity.Recipe;
import com.example.recipecrud.service.RecipeServiceImpl;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
public class RecipesRestController {

    private final RecipeServiceImpl recipeServiceImpl;
    private final JwtUtil jwtUtil;

    @Autowired
    public RecipesRestController(RecipeServiceImpl recipeService, JwtUtil jwtUtil) {
        this.recipeServiceImpl = recipeService;
        this.jwtUtil = jwtUtil;
    }

    // Add recipe to database
    @PostMapping("/addRecipe")
    public ResponseEntity<Map<String, String>> addRecipe(@RequestBody Recipe theRecipe, HttpServletRequest request) {
        String email = extractEmailFromToken(request);
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Unauthorized"));
        }

        if (theRecipe == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid Recipe Data"));
        }
        recipeServiceImpl.save(theRecipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Recipe added successfully!"));
    }

    // Get all recipes
    @GetMapping("/recipes")
    public ResponseEntity<List<Recipe>> getRecipes(HttpServletRequest request) {
        String email = extractEmailFromToken(request);
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        List<Recipe> recipes = recipeServiceImpl.findAll();
        if (recipes.isEmpty()) {
            throw new RecipeNotFoundException("Recipes not found");
        }
        return ResponseEntity.ok(recipes);
    }

    // Get recipe by ID
    @GetMapping("/{recipeId}")
    public ResponseEntity<Recipe> getRecipe(int recipeId, HttpServletRequest request) {
        String email = extractEmailFromToken(request);
        if (email == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Recipe recipe = recipeServiceImpl.findById(recipeId);
        if (recipe == null) {
            throw new RecipeNotFoundException("Recipes not found");
        }
        return ResponseEntity.ok(recipe);
    }

    // Filter recipes by vegan, glutenfree, dairyfree
    @GetMapping("/recipes/filter")
    public ResponseEntity<List<Recipe>> filterRecipes(
        @RequestParam(name = "vegan", required = false) Boolean vegan,
        @RequestParam(name = "glutenFree", required = false) Boolean glutenFree,
        @RequestParam(name = "dairyFree", required = false) Boolean dairyFree) {

        List<Recipe> recipes = recipeServiceImpl.filterRecipes(vegan, glutenFree, dairyFree);
        if (recipes.isEmpty()) {
            throw new RecipeNotFoundException("No recipes found with the specified filters");
        }
        return ResponseEntity.ok(recipes);
    }

    // Extract email from JWT token
    private String extractEmailFromToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        String token = authHeader.substring(7);
        try {
            return jwtUtil.extractUsername(token);
        } catch (RuntimeException e) {
            return null;
        }
    }


}

