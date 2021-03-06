package com.housemate.service.user;

import com.housemate.dao.user.UserDAO;
import com.housemate.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private Validator validator;

    @Override
    @Transactional
    public boolean createNewUser(String email, String username) {
        return userDAO.createNewUser(email, username);
    }

    @Override
    @Transactional
    public boolean createNewUser(String email, String username, String password) {
        return userDAO.createNewUser(email, username, password);
    }

    @Override
    @Transactional
    public boolean createNewUser(User user) {
        return userDAO.createNewUser(user);
    }


    @Override
    @Transactional
    public User selectUserByUsername(String username) {
        return userDAO.selectUserByUsername(username);
    }

    @Override
    public User authenticateUser(String key) {
        return userDAO.authenticateUser(key);
    }

    @Override
    public String getAuthKey(String username) {
        return userDAO.getUserAuthKey(username);
    }

    @Override
    public User getUser(String authKey) {
        return userDAO.getUser(authKey);
    }
}
