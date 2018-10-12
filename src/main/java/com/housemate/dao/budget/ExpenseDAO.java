package com.housemate.dao.budget;


import com.housemate.models.Expense;
import com.housemate.models.User;

import java.math.BigDecimal;
import java.util.Date;
import java.text.ParseException;
import java.util.List;

public interface ExpenseDAO {

    List<Expense> getByMonthExpense(User user, String month, int year) throws ParseException;

    List<Expense> getThisMonthExpense(User user);

    void newExpense(User user, BigDecimal amount, String name, Date date);

    void newExpense(User user, BigDecimal amount, String name);

    Expense getLastExpense(User user);

    List<Expense> getLastTenExpenses(User user);
}
