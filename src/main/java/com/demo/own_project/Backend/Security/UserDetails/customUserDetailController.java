package com.demo.own_project.Backend.Security.UserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.own_project.Backend.Model.users;
import com.demo.own_project.Backend.Repository.userRepo;

@Service
public class customUserDetailController implements UserDetailsService {
   
    @Autowired
    public userRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         users user = repo.findByUsername(username).get(0);
        if(user == null){
            throw new UsernameNotFoundException("User not found");
        }
        else{
            return new CustomUserDetailService(user);
        }
    }

    
}
