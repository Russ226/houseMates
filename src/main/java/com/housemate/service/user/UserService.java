package com.housemate.service.user;

import com.housemate.models.User;

public interface UserService {

    boolean createNewUser(String email, String username);

    boolean createNewUser(String email, String username, String password);

    boolean createNewUser(User user);

    User selectUserByUsername(String username);

    boolean login(String username);

    String getAuthKey(String username);
}
