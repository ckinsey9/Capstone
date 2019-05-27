package com.example.Capstone.Models.Data;

import com.example.Capstone.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    //added method to grab user data by username, not just Id
    User findByUsername(String username);


}
