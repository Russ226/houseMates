package com.housemate.dao.user;

import com.housemate.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;



    @Override
    public User selectUserByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "FROM User U WHERE U.username =:username";
        Query query = session.createQuery(queryString, User.class);
        query.setParameter("username", username);

        List<User> userList = query.list();

        return userList.get(0);
    }

    @Override
    public boolean createNewUser(User user) {
        Session session = sessionFactory.getCurrentSession();

        try{
            session.save(user);
        }catch(org.springframework.dao.DataIntegrityViolationException as){
            as.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean login(String username) {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "FROM User U WHERE U.username =:username";
        Query query = session.createQuery(queryString, User.class);
        query.setParameter("username", username);

        List<User> userList = query.list();

        return userList.size() > 0;
    }

    @Override
    public boolean createNewUser(String email, String username) {

        User user = new User(email, username);

        Session session = sessionFactory.getCurrentSession();

        try{
            session.save(user);
        }catch(org.springframework.dao.DataIntegrityViolationException as){
            as.printStackTrace();
            return false;
        }

        return true;

    }


}
