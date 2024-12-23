package com.example.recipecrud.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.recipecrud.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class UserDAOImpl implements UserDAO {

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public User findByEmail(String email) {
        String query = "FROM User WHERE email = :email";
        TypedQuery<User> theQuery = entityManager.createQuery(query, User.class);
        theQuery.setParameter("email", email);

        List<User> result = theQuery.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> theQuery = entityManager.createQuery("FROM User", User.class);
        return theQuery.getResultList();
    }

    
    
}
