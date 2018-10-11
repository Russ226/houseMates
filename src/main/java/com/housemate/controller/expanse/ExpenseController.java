package com.housemate.controller.expanse;

import com.housemate.service.Budget.ExpenseService;
import com.housemate.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class ExpenseController {

    @Autowired
    UserService userService;

    @Autowired
    ExpenseService expenseService;

    
}
