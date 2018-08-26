package com.housemate.service.user;

public interface UserService {

    boolean isEmailUnique(String email);

    void createNewUser(String email, String username);

}
