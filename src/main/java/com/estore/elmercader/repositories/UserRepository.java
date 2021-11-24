/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estore.elmercader.repositories;

import com.estore.elmercader.entities.User;
import com.estore.elmercader.repositories.crud.UserCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author sprue
 */
@Repository
public class UserRepository {
    
@Autowired
    private UserCrudRepository repo;

    public List<User> getAll(){  
        return (List<User>) repo.findAll();
    }
    
    public Optional<User> getUser(int id){
        return repo.findById(id);
    }
    
    public boolean getByEmail(String email) {
        Optional<User> user = repo.findByEmail(email);
        return user.isPresent();
    }
    
    public Optional<User> getByEmailAndPassword(String email, String password) {
        return repo.findByEmailAndPassword(email, password);
    }

    public User save(User user) {
        return repo.save(user);
    }

    public void delete(User user) {
        repo.delete(user);
    }
}