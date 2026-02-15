package com.demo.own_project.Backend.Security.UserDetails;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.demo.own_project.Backend.Model.users;

@Service
public class CustomUserDetailService implements UserDetails {
    @Autowired
    public users user;
    @Autowired
    public customUserDetailController service;

   public CustomUserDetailService(users user){
    this.user = user;
   }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public Integer getId(){
        return user.getId();
    }
       
}
