package com.housemate.dao.user;

import com.housemate.models.User;

public interface UserDAO {
    boolean isEmailUnique(String email);

    void createNewUser(String email, String username);

    boolean isUsernameUnique(String username);

    User selectUserByUsername(String username);
}
