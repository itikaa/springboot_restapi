package com.example.restapi.controller;
import com.example.restapi.collections.users;
import com.example.restapi.repo.userrepository;
import com.example.restapi.service.userservice;
// import com.example.restapi.collections.student;
// import com.example.restapi.service.studentservice;
// import com.example.restapi.service.userservice;
// import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

//import java.time.LocalDateTime;
import java.util.List;
//import java.util.Optional;

@RestController
@RequestMapping("/users")
public class usercontroller {

@Autowired
    private userservice userservice;

    @Autowired
    private userrepository userrepo;



@PutMapping
    public ResponseEntity<?> update( @RequestBody users us){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
String username=authentication.getName();
 users userdb=userservice.findByusername(username);
 if(userdb!=null){
     userdb.setUsername(us.getUsername());
     userdb.setPassword(us.getPassword());
     userservice.savenewuser(userdb);
 }
 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

@DeleteMapping
public ResponseEntity<?> delete(){
    Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
    userrepo.deletebyusername(authentication.getName());
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);

}
    }

