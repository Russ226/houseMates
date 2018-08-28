package com.housemate.test.users;

import com.housemate.models.User;
import com.housemate.service.user.UserService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(locations={"/Context.xml"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestUser {
    @Autowired
    UserService userService;

    @Autowired
    SessionFactory sessionFactory;



    @Test
    @Transactional
    public void testCreateUser(){
        userService.createNewUser("bob@email.com", "Bob123");

        Session session = sessionFactory.getCurrentSession();

        User user = session.get(User.class, 1);

        assertEquals("bob@email.com", user.getEmailAddress());
        assertEquals("Bob123", user.getUsername());

    }
}
