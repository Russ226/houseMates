package com.housemate.dao.user;

import com.housemate.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

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
        user.setAuth(UUID.randomUUID().toString().substring(0,8));

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
    public boolean createNewUser(String email, String username, String password) {
        User user = new User(email, username, password);

        user.setAuth(UUID.randomUUID().toString().substring(0,8));

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
    public boolean authenticateUser(String key) {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "FROM User U WHERE U.auth =:key";
        Query query = session.createQuery(queryString, User.class);
        query.setParameter("key", key);

        List<User> userList = query.list();

        return userList.size() > 0;
    }

    @Override
    public String getUserAuthKey(String username) {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "FROM User U WHERE U.username =:username";
        Query query = session.createQuery(queryString, User.class);
        query.setParameter("username", username);

        List<User> userList = query.list();

        return userList.get(0).getAuth();
    }

    @Override
    public User getUser(String authKey) {
        Session session = sessionFactory.getCurrentSession();

        String queryString = "FROM User U WHERE U.auth =:aut";
        Query query = session.createQuery(queryString, User.class);
        query.setParameter("aut", authKey);

        List<User> userList = query.list();

        if(userList.size() > 0){
            return userList.get(0);

        }

        return null;
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
