package com.housemate.controller.user;

import com.housemate.models.User;
import com.housemate.service.user.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createUser(@RequestBody User user){
        if(userService.createNewUser(user)) return ResponseEntity.ok(HttpStatus.CREATED);


        return ResponseEntity.ok(HttpStatus.valueOf(409));

    }
    
    //test controller
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public User hello(){
        return new User("bob@email.com", "bob");
    }

    @RequestMapping(value= "/login", method= RequestMethod.GET)
    public boolean login(@RequestParam(value = "username") String username){
        return userService.login(username);

    }
}
