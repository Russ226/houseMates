package com.housemate.dao.user;

public interface UserDAO {
    boolean isEmailUnique(String email);

    void createNewUser(String email, String username);

    boolean isUsernameUnique(String username);
}
