package com.example.restapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class userservicetest {

@Autowired
private userservice userservice;

    @Test
    public void testfindbyusername(){
        assertEquals(4,2+2);
        assertNotNull(userservice.findByusername("ram"));
       }
}
