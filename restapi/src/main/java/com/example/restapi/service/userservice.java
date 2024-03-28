package com.example.restapi.service;

//import com.example.restapi.collections.student;
import com.example.restapi.collections.users;
//import com.example.restapi.repo.studentrepository;
import com.example.restapi.repo.userrepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Arrays;
@Component
public class userservice {
    @Autowired
    private userrepository userrepo;

     private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public void savenewuser(users us){
        us.setPassword(passwordEncoder.encode(us.getPassword()));
        us.setRoles(Arrays.asList("USER"));
        userrepo.save(us);
    }

    public void saveadmin(users us){
        us.setPassword(passwordEncoder.encode(us.getPassword()));
        us.setRoles(Arrays.asList("USER","ADMIN"));
        userrepo.save(us);
    }

    public void saveuser(users us){
        userrepo.save(us);
    }

    public List<users> getall(){
        return userrepo.findAll();
    }
    public Optional<users> findbyid(ObjectId id){
        return userrepo.findById(id);
    }
    public void deletebyid(ObjectId id){
        userrepo.deleteById(id);
    }
    public users findByusername(String username){ return userrepo.findByusername(username);}
}
