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
    public boolean isEmailUnique(String email) {
        
        return userDAO.isEmailUnique(email);
    }

    @Override
    @Transactional
    public void createNewUser(String email, String username) {
        userDAO.createNewUser(email, username);
    }

    @Override
    @Transactional
    public boolean isUsernameUnique(String username) {

        return userDAO.isUsernameUnique(username);
    }

    @Override
    @Transactional
    public User selectUserByUsername(String username) {
        return userDAO.selectUserByUsername(username);
    }
}
