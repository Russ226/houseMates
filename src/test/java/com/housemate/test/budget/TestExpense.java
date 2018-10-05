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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

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

    }

    @Test
    public void testGettingThisMonthExpenses(){
        User user = userService.selectUserByUsername("bob123");

        List<Expense> budget = expenseService.getThisMonthExpense(user);

        assertEquals(17, budget.size());
    }
}
