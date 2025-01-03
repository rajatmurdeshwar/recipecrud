package com.example.recipecrud.rest;

import org.springframework.web.bind.annotation.RestController;

import com.example.recipecrud.JwtUtil;
import com.example.recipecrud.dao.RecipeDAO;
import com.example.recipecrud.entity.Recipe;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api")
public class RecipesRestController {

    private final RecipeDAO recipeDAO;
    private final JwtUtil jwtUtil;

    public RecipesRestController(RecipeDAO theRecipeDao, JwtUtil jwtUtil) {
        this.recipeDAO = theRecipeDao;
        this.jwtUtil = jwtUtil;

    }

    // add recipe to db
    @PostMapping("/addRecipe")
    public ResponseEntity<Map<String, String>> addRecipe(@RequestBody Recipe theRecipe, HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Unauthorized"));
        }
    
        String token = authHeader.substring(7);
        try {
            String email = jwtUtil.extractUsername(token);
            // Optional: Verify user exists in the system
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Invalid token"));
        }
    
        if (theRecipe == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid Recipe Data"));
        }
        recipeDAO.save(theRecipe);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("message", "Recipe added successfully!"));
    }
    
    

    // get all recipes
    @GetMapping("/recipes")
    public ResponseEntity<List<Recipe>> getRecipe(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    
        String token = authHeader.substring(7);
        try {
            jwtUtil.validateToken(token);
            // Optional: Verify user exists in the system
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    
        List<Recipe> recipes = recipeDAO.findAll();
        if (recipes.isEmpty()) {
            throw new RecipeNotFoundException("Recipes not found");
        }
        return ResponseEntity.ok(recipes);
    }
    
    
}
