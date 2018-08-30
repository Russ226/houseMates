package com.housemate.dao.user;

import com.housemate.models.User;

public interface UserDAO {

    boolean createNewUser(String email, String username);

    User selectUserByUsername(String username);

    boolean createNewUser(User user);
}
