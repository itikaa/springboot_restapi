package com.example.restapi.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import com.example.restapi.collections.users;
import com.example.restapi.service.userservice;

@RestController
@RequestMapping("/admin")
public class admincontroller {

    @Autowired
    private userservice userservice;

   @GetMapping("/all-users")
public  ResponseEntity<List<users>> getalluser(){
    List<users> all=userservice.getall();
    if(all!=null && !all.isEmpty()){
        return new ResponseEntity<>(all,HttpStatus.OK);
    }
    return new ResponseEntity<>(all,HttpStatus.NOT_FOUND);
}

@PostMapping("/create-new-admin")
public void create(@RequestBody users user){
 userservice.saveadmin(user);
}
    
}
