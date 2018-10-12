package com.housemate.controller.expanse;

import com.housemate.models.User;
import com.housemate.service.Budget.ExpenseService;
import com.housemate.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    UserService userService;

    @Autowired
    ExpenseService expenseService;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public HttpStatus createUser(@RequestBody User user, @RequestParam(value = "amount", required = false) String amount){
        if(!userService.login(user.getUsername())) return HttpStatus.CREATED;

        return HttpStatus.valueOf(409);

    }



    
}
