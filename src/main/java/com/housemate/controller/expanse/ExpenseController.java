package com.housemate.controller.expanse;

import com.housemate.jsonhelpers.message.ErrorMessage;
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
    public String createUser(@RequestParam(value = "auth_key", required = true) String key, @RequestParam(value = "amount", required = true) String amount){
        if(!userService.authenticateUser(key)) return new ErrorMessage("Invalid Key").getJson();
        try{
            expenseService.newExpense();
            return
        }catch (NumberFormatException e){
            return new ErrorMessage("Invalid number").getJson();
        }


    }



    
}
