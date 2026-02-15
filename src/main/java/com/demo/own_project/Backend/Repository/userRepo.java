package com.demo.own_project.Backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.own_project.Backend.Model.users;

@Repository
public interface userRepo extends JpaRepository<users, Integer>{
    List<users> findByUsername(String username);
    
    @Query(value = "Select username from Users where username =:name", nativeQuery=true)
    String checkUser(String name);
}
