package com.example.restapi.controller;

import com.example.restapi.collections.student;
import com.example.restapi.collections.users;
import com.example.restapi.service.studentservice;
import com.example.restapi.service.userservice;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.stream.Collectors;
// import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/student")
public class studentcontroller {

    @Autowired
    private studentservice stservice;
    @Autowired
    private userservice userservice;

@GetMapping
    public ResponseEntity<?> getall(){
         Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
String username=authentication.getName();
    users user=userservice.findByusername((username));
    List<student> all=user.getStudents();
    if(all!=null && !all.isEmpty()){
        return new ResponseEntity<>(all, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
}

@PostMapping
    public ResponseEntity<student> createentry(@RequestBody student mystudent){
    try{
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        stservice.savestudent(mystudent,username);
        return  new ResponseEntity<>(mystudent, HttpStatus.CREATED);
    }
    catch (Exception e){
        return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    }

    @GetMapping("/id/{myid}")
    public ResponseEntity<student> getbyid(@PathVariable ObjectId myid){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        users user=userservice.findByusername(username);
        List<student> collect=user.getStudents().stream().filter(x ->x.getId().equals(myid)).collect(Collectors.toList());
        if(!collect.isEmpty()){
            Optional<student> entry=stservice.findbyid(myid);
            if(entry.isPresent()){
                return new ResponseEntity<>(entry.get(), HttpStatus.OK);
        }
   
    }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @DeleteMapping( "/id/{myid}")
    public ResponseEntity<?> deletebyid(@PathVariable ObjectId myid){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
         boolean remove=stservice.deletebyid(myid,username);
         if(remove){
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }
         else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }
    }


   @PutMapping("/id/{myid}")
    public ResponseEntity<?>  update(@RequestBody student newStudent, @PathVariable ObjectId myid){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        users user=userservice.findByusername(username);
        List<student> collect=user.getStudents().stream().filter(x ->x.getId().equals(myid)).collect(Collectors.toList());

        if(!collect.isEmpty()){
            Optional<student> entry=stservice.findbyid(myid);
            if(entry.isPresent()){
                student old=entry.get();
                old.setName(newStudent.getName()!=null &&!newStudent.getName().equals("")? newStudent.getName() : old.getName());
                old.setAge(newStudent.getAge()!=null &&!newStudent.getAge().equals("")? newStudent.getAge() : old.getAge());
                old.setAddress(newStudent.getAddress()!=null &&!newStudent.getAddress().equals("")? newStudent.getAddress() : old.getAddress());
               old.setName(newStudent.getName());
               old.setAge(newStudent.getAge());
               old.setAddress(newStudent.getAddress());
     
               stservice.savestudent(old);
                return new ResponseEntity<>(old,HttpStatus.OK) ;
        }
    }

         return new ResponseEntity<>(HttpStatus.NOT_FOUND) ;
    }
}
