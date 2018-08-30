package com.housemate.service.user;

import com.housemate.models.User;

public interface UserService {

    boolean createNewUser(String email, String username);

    boolean createNewUser(User user);

    User selectUserByUsername(String username);
}
