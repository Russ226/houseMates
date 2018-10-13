package com.housemate.controller.expanse;

import com.google.gson.Gson;
import com.housemate.jsonhelpers.auth.AuthKey;
import com.housemate.jsonhelpers.message.ErrorMessage;
import com.housemate.jsonhelpers.message.SuccessExpenseMessage;
import com.housemate.models.User;
import com.housemate.service.Budget.ExpenseService;
import com.housemate.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Month;
import java.util.Calendar;
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
    public String createExpense(@RequestParam(value = "auth_key") String key, @RequestParam(value = "amount") String amount,
                             @RequestParam(value = "name") String name, @RequestParam(value = "date") String date){
        Date parsedDate = null;
        User user = userService.authenticateUser(key);
        if(user == null) return new ErrorMessage("Invalid Key").getJson();

        if(date != null){
            try{
                DateFormat format = new SimpleDateFormat("yyyy/MM/dd", Locale.ENGLISH);
                parsedDate = format.parse(date);
            }catch (ParseException e){
                return new ErrorMessage("Invalid Date Format").getJson();
            }
        }

        try{
            expenseService.newExpense(user, new BigDecimal(amount), name, parsedDate);
            return new SuccessExpenseMessage("New Expenses added " + user.getUsername(), HttpStatus.CREATED).getJson();
        }catch (NumberFormatException e){
            return new ErrorMessage("Invalid number").getJson();
        }
    }

    @RequestMapping(value= "/get/expense", method= RequestMethod.GET)
    public String getExpense(@RequestParam(value = "month", required = false) String month, @RequestParam(value = "year", defaultValue = "4") int year,
                             @RequestParam(value = "key", required = true) String key){
        String parsedMonth;
        User user = userService.authenticateUser(key);
        if(user == null) return new ErrorMessage("Invalid Key").getJson();

        if(month != null && year != 4){
            try{
                parsedMonth = Month.valueOf(month).name();
                if(year > Calendar.getInstance().get(Calendar.YEAR) || year < 1900){
                    return new ErrorMessage("Invalid year").getJson();
                }
                return new Gson().toJson(expenseService.getByMonthExpense(user, parsedMonth, year));
            }catch (IllegalArgumentException e){
                return new ErrorMessage("Invalid Month").getJson();
            } catch (ParseException e) {
                return new ErrorMessage("Invalid Month").getJson();
            }
        }

        return new Gson().toJson(expenseService.getThisMonthExpense(user));



    }



    
}
