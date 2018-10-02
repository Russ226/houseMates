package com.housemate.service.Budget;

import com.housemate.models.Expense;
import com.housemate.models.User;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


public interface ExpenseService {

    List<Expense> getExpense(User user, String month, int year) throws ParseException;

    List<Expense> getExpense(User user);

    void newExpense(User user, BigDecimal amount, String name, Date date);

    void newExpense(User user, BigDecimal amount, String name);
}
