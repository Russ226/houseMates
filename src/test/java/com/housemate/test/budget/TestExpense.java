package com.housemate.test.budget;

import com.housemate.models.Expense;
import com.housemate.models.User;
import com.housemate.service.Budget.ExpenseService;
import com.housemate.service.user.UserService;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.temporal.TemporalAccessor;
import java.util.Date;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations={"/Context.xml"})
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class TestExpense {
    @Autowired
    UserService userService;

    @Autowired
    SessionFactory sessionFactory;


    @Autowired
    ExpenseService expenseService;
    

    @Test
    public void testNewExpenseWithoutDate(){
        User user = userService.selectUserByUsername("bob123");

        expenseService.newExpense(user, BigDecimal.valueOf(20.00), "food");
    }

    @Test
    public void testGettingThisMonthExpenses(){
        User user = userService.selectUserByUsername("bob123");

        List<Expense> budget = expenseService.getThisMonthExpense(user);

        assertEquals(17, budget.size());
    }

    @Test
    public void testGettingNovemberExpenses() throws ParseException {
        User user = userService.selectUserByUsername("bob123");

        List<Expense> budget = expenseService.getByMonthExpense(user,"November", 2018);

        assertEquals(5, budget.size());
    }

    @Test
    public void testGettingLastExpense(){
        User user = userService.selectUserByUsername("bob123");

        Expense expense = expenseService.getLastExpense(user);

        assertEquals("soda", expense.getExpenseName());
        assertEquals("2018-11-03 13:17:17.0" , expense.getCreatedOn().toString());


    }

    @Test
    public void testGettingLastFiveExpenses(){
        String[] dates = {"2018-11-03 13:17:17.0", "2018-11-02 13:17:17.0", "2018-11-01 13:17:17.0"};

        User user = userService.selectUserByUsername("bob123");

        List<Expense> expenses = expenseService.getLastXExpenses(user, 5);

        assertEquals(5, expenses.size());
        assertEquals(dates[0], expenses.get(0).getCreatedOn().toString());
        assertEquals(dates[0], expenses.get(1).getCreatedOn().toString());
        assertEquals(dates[1], expenses.get(2).getCreatedOn().toString());
        assertEquals(dates[1], expenses.get(3).getCreatedOn().toString());
        assertEquals(dates[2], expenses.get(4).getCreatedOn().toString());


    }

    @Test
    public void testGettingLastTenExpenses(){
        String[] dates = {"2018-11-03 13:17:17.0", "2018-11-02 13:17:17.0", "2018-11-01 13:17:17.0"};

        User user = userService.selectUserByUsername("bob123");

        List<Expense> expenses = expenseService.getLastXExpenses(user, 10);

        assertEquals(10, expenses.size());

    }


}
