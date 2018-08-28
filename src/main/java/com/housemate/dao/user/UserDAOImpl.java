package com.housemate.dao.user;

import com.housemate.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.PrintStream;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public boolean isEmailUnique(String email) {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "FROM User U WHERE U.emailAddress =:email";
        Query query = session.createQuery(queryString, User.class);
        query.setParameter("email", email);

        List<User> userList = query.list();
        if(userList == null) return false;
        return userList.size() > 0;
    }

    @Override
    public boolean isUsernameUnique(String username) {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "FROM User U WHERE U.username =:username";
        Query query = session.createQuery(queryString, User.class);
        query.setParameter("username", username);

        List<User> userList = query.list();
        if(userList == null) return false;
        return userList.size() > 0;
    }

    @Override
    public void createNewUser(String email, String username) {

        User user = new User(email, username);

        Session session = sessionFactory.getCurrentSession();

        try{
            session.save(user);
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
