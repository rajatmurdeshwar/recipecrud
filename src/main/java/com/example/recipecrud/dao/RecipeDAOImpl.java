package com.example.recipecrud.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.recipecrud.entity.Recipe;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class RecipeDAOImpl implements RecipeDAO {

    private EntityManager entityManager;

    @Autowired
    public RecipeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Recipe theRecipe) {
        entityManager.persist(theRecipe);
    }

    @Override
    public Recipe findById(Integer id) {
        return entityManager.find(Recipe.class, id);
    }

    @Override
    public List<Recipe> findAll() {
        TypedQuery<Recipe> theQuery = entityManager.createQuery("FROM Recipe", Recipe.class);
        return theQuery.getResultList();
    }

    @Override
    @Transactional
    public void update(Recipe theRecipe) {
        entityManager.merge(theRecipe);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Recipe theRecipe = entityManager.find(Recipe.class, id);
        if (theRecipe != null) {
            entityManager.remove(theRecipe);
        }
    }

    @Override
    @Transactional
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Recipe").executeUpdate();
        return numRowsDeleted;
    }
    
}
