package com.housemate.service.user;

import com.housemate.models.User;

public interface UserService {

    boolean isEmailUnique(String email);

    void createNewUser(String email, String username);

    boolean isUsernameUnique(String username);

    User selectUserByUsername(String username);
}
