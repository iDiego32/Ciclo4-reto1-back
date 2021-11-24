/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estore.elmercader.services;

import com.estore.elmercader.entities.User;
import com.estore.elmercader.repositories.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author sprue
 */
@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    

    public List<User> getAll() {
        return userRepository.getAll();
    }

    public Optional<User> getUser(int id) {
       return userRepository.getUser(id);
    }

    public User register (User user){
        if (user.getId() ==null){
            if (getByEmail(user.getEmail()) == false) {
                    return userRepository.save(user);
        } else {
            return user;
        }
        } else {
        return user;
    }
}

    public boolean getByEmail(String email) {
        return userRepository.getByEmail(email);
    }
    
    public User getByEmailAndPassword (String email, String password) {
        Optional<User> user = userRepository.getByEmailAndPassword(email, password);
        if (user.isPresent()) {
            return user.get();
        } else {
            User u = new User();
            u.setEmail(email);
            u.setPassword(password);
            u.setName("NO DEFINIDO");
            return u;
        }
    }
    
    public User save(User user) {
        return userRepository.save(user);
    }

    public boolean delete(int id) {

        Boolean d = userRepository.getUser(id).map(user -> {
         userRepository.delete(user);
            return true;
        }).orElse(false);
        return d;

    }

    public User update(User user) {
        if (user.getId()!= null) {
            Optional<User> consultado = userRepository.getUser(user.getId());
            if (consultado.isPresent()) {
                if (user.getEmail() != null) {
                    consultado.get().setEmail(user.getEmail());
                }
                if (user.getPassword() != null) {
                    consultado.get().setPassword(user.getPassword());
                }
                return userRepository.save(consultado.get());
            }
        }
        return user;
    }

}
