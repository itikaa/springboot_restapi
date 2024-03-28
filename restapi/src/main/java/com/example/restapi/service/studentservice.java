package com.example.restapi.service;

import com.example.restapi.collections.student;
import com.example.restapi.collections.users;
import com.example.restapi.repo.studentrepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

//import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class studentservice {
    @Autowired
    private studentrepository studentrepo;
    @Autowired
    private userservice userservice;

public void savestudent(student st){
       studentrepo.save(st);

    }

    @Transactional
    public void savestudent(student st,String username){
    try {
        users user = userservice.findByusername(username);
        //student.setDate(LocalDateTime.now());
        student saved = studentrepo.save(st);
        user.getStudents().add(saved);
        userservice.saveuser(user);
    }
  catch(Exception e){
throw new RuntimeException("an error occurred while saving the entry",e);
        }
    }

    public List<student> getallentries(){
        return studentrepo.findAll();
    }
    public Optional<student> findbyid(ObjectId id){
        return studentrepo.findById(id);
    }

    @Transactional
    public boolean deletebyid(ObjectId id,String username){
        boolean remove=false;
        try{
      users user=userservice.findByusername(username);
      remove = user.getStudents().removeIf(x->x.getId().equals(id));
     if(remove){
        userservice.saveuser(user);
        studentrepo.deleteById(id);

     }
     }
     catch (Exception e){
        System.out.println(e);
        throw new RuntimeException("an error occured while  deleting an entry",e);
    }
       return remove;
    }
}
