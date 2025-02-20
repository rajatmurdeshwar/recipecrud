package com.example.recipecrud.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.recipecrud.entity.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    @Query("SELECT r FROM Recipe r WHERE " +
            "(:vegan IS NULL OR r.vegan = :vegan) AND " +
            "(:glutenFree IS NULL OR r.glutenFree = :glutenFree) AND " +
            "(:dairyFree IS NULL OR r.dairyFree = :dairyFree)")
    List<Recipe> findByFilters(@Param("vegan") Boolean vegan,
                               @Param("glutenFree") Boolean glutenFree,
                               @Param("dairyFree") Boolean dairyFree);
}
