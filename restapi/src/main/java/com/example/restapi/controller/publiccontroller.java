package com.example.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restapi.collections.users;
import com.example.restapi.service.userservice;

@RestController
@RequestMapping("/public")
public class publiccontroller {

    @Autowired
    private userservice userservice;


    @PostMapping
    public boolean createuser(@RequestBody users us){
    userservice.savenewuser(us);
    return true;
}
}
