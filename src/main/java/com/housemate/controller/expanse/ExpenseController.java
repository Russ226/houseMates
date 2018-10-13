package com.housemate.controller.expanse;

import com.housemate.jsonhelpers.message.ErrorMessage;
import com.housemate.jsonhelpers.message.SuccessExpenseMessage;
import com.housemate.models.User;
import com.housemate.service.Budget.ExpenseService;
import com.housemate.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

    @Autowired
    UserService userService;

    @Autowired
    ExpenseService expenseService;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String createUser(@RequestParam(value = "auth_key") String key, @RequestParam(value = "amount") String amount,
                             @RequestParam(value = "name") String name, @RequestParam(value = "date") String date){

        User user = userService.authenticateUser(key);
        if(user == null) return new ErrorMessage("Invalid Key").getJson();

        if(date != null){
            try{
                DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
                Date parsedDate = format.parse(date);
            }catch (ParseException e){
                return new ErrorMessage("Invalid Date Format").getJson();
            }
        }

        try{
            expenseService.newExpense(user, new BigDecimal(amount), name);
            return new SuccessExpenseMessage("New Expenses added " + user.getUsername(), HttpStatus.CREATED).getJson();
        }catch (NumberFormatException e){
            return new ErrorMessage("Invalid number").getJson();
        }


    }



    
}
