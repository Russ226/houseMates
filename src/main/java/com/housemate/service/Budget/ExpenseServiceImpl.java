package com.housemate.service.Budget;

import com.housemate.dao.budget.ExpenseDAO;
import com.housemate.models.Expense;
import com.housemate.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {
    @Autowired
    ExpenseDAO expenseDAO;

    @Override
    @Transactional
    public List<Expense> getExpense(User user, String month, int year) throws ParseException {
        return expenseDAO.getExpense(user, month, year);
    }

    @Override
    @Transactional
    public List<Expense> getExpense(User user) {
        return expenseDAO.getExpense(user);
    }

    @Override
    @Transactional
    public void newExpense(User user, BigDecimal amount, String name, Date date) {
        expenseDAO.newExpense(user, amount, name, date);
    }

    @Override
    @Transactional
    public void newExpense(User user, BigDecimal amount, String name) {
        expenseDAO.newExpense(user, amount, name);
    }
}
