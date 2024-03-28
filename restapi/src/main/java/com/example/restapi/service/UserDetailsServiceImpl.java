 package com.example.restapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.restapi.collections.users;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Component;

import com.example.restapi.repo.userrepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{

@Autowired
 private userrepository userrepo;

 @Override
 public UserDetails loadUserByUsername (String username) throws UsernameNotFoundException{
    users user=userrepo.findByusername(username);
    if(user!=null){
        UserDetails userdetails=  org.springframework.security.core.userdetails.User.builder()
        .username(user.getUsername())
        .password(user.getPassword())
        .roles(user.getRoles().toArray(new String[0]))
        .build();
        return userdetails;
    }
    throw new UsernameNotFoundException("username not found with username:"+username);
 }
 
}
