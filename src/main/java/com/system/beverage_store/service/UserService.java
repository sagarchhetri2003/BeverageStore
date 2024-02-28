package com.system.beverage_store.service;


import com.system.beverage_store.entity.Product;
import com.system.beverage_store.entity.User;
import com.system.beverage_store.pojo.UserPojo;

import java.util.List;

public interface UserService {
    String save(UserPojo userPojo);
    UserPojo findByEmail(String email);
    String update(UserPojo userPojo);

    void processPasswordResetRequest(String email);

    void resetPassword(String email, String OTP, String password);

    List<User> fetchAll();

    void deleteById(Integer id);

    Product fetchById(Integer id);
}
