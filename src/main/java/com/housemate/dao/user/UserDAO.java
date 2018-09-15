package com.housemate.dao.user;

import com.housemate.models.User;

public interface UserDAO {

    boolean createNewUser(String email, String username);

    boolean createNewUser(String email, String username, String password);

    User selectUserByUsername(String username);

    boolean createNewUser(User user);

    boolean login(String username);

    String getUserAuthKey(String username);
}
