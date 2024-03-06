package com.hnv99.hmall.serivce;

import com.hnv99.hmall.model.entity.User;

import java.util.List;

public interface UserService {
    User getUser(Long id);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    List<User> getAllUsers();
}
