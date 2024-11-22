package com.example.recipecrud.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="recipe")
public class Recipe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "recipe_id")
    private int recipeId;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "instructions")
    private String instructions;

    @Column(name = "item_image")
    private String itemImage;

    @Column(name = "health_score")
    private int healthScore;

    @Column(name = "ready_in")
    private int readyIn;

    @Column(name = "servings")
    private int servings;

    @Column(name = "vegan")
    private Boolean vegan;

    @Column(name = "gluten_free")
    private Boolean glutenFree;

    @Column(name = "dairy_free")
    private Boolean dairyFree;

    public Recipe(int recipeId, String title, String description, String category, String instructions,
            String itemImage, int healthScore, int readyIn, int servings, Boolean vegan, Boolean glutenFree) {
        this.recipeId = recipeId;
        this.title = title;
        this.description = description;
        this.category = category;
        this.instructions = instructions;
        this.itemImage = itemImage;
        this.healthScore = healthScore;
        this.readyIn = readyIn;
        this.servings = servings;
        this.vegan = vegan;
        this.glutenFree = glutenFree;
    }

    // Default Constructor
    public Recipe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public int getHealthScore() {
        return healthScore;
    }

    public void setHealthScore(int healthScore) {
        this.healthScore = healthScore;
    }

    public int getReadyIn() {
        return readyIn;
    }

    public void setReadyIn(int readyIn) {
        this.readyIn = readyIn;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public Boolean getVegan() {
        return vegan;
    }

    public void setVegan(Boolean vegan) {
        this.vegan = vegan;
    }

    public Boolean getGlutenFree() {
        return glutenFree;
    }

    public void setGlutenFree(Boolean glutenFree) {
        this.glutenFree = glutenFree;
    }

    public Boolean getDairyFree() {
        return dairyFree;
    }

    public void setDairyFree(Boolean dairyFree) {
        this.dairyFree = dairyFree;
    }

    @Override
    public String toString() {
        return "Recipe [id=" + id + ", recipeId=" + recipeId + ", title=" + title + ", description=" + description
                + ", category=" + category + ", instructions=" + instructions + ", itemImage=" + itemImage
                + ", healthScore=" + healthScore + ", readyIn=" + readyIn + ", servings=" + servings + ", vegan="
                + vegan + ", glutenFree=" + glutenFree + ", dairyFree=" + dairyFree + "]";
    }
    
}
