package com.housemate.test.users;

import com.housemate.controller.user.UserController;
import com.housemate.models.User;
import com.housemate.service.user.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations={"/Context.xml"})
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@Transactional
public class TestUserController {
    @Autowired
    UserService userService;

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    UserController userController;

    private static Validator validator;

    @Before
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @After
    public void killBob(){
        Session session = sessionFactory.getCurrentSession();

        String queryString = "delete from User U where U.username = :username";
        Query query =  session.createQuery(queryString);

        query.setParameter("username", "Bob123");
        try{
            query.executeUpdate();
        }catch (org.hibernate.AssertionFailure as){
            as.printStackTrace();
        }


    }

    @Test
    public void testCreateUser(){
        User user = new User("bob@email.com", "Bob123", "GFFGFG");

        HttpStatus httpStatus = userController.createUser(user);

        assertEquals(HttpStatus.CREATED, httpStatus);

    }
}
