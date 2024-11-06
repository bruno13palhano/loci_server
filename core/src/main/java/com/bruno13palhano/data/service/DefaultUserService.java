package com.bruno13palhano.data.service;

import com.bruno13palhano.User;
import com.bruno13palhano.data.datasource.UserDataSource;
import org.springframework.stereotype.Service;

@Service
public class DefaultUserService implements UserService {
    private final UserDataSource userDataSource;

    public DefaultUserService(UserDataSource userDataSource) {
        this.userDataSource = userDataSource;
    }

    @Override
    public void insert(User user) {
        userDataSource.insert(user);
    }

    @Override
    public void update(User user) {
        userDataSource.update(user);
    }

    @Override
    public void delete(String uid) {
        userDataSource.delete(uid);
    }

    @Override
    public User getByUsername(String username) {
        return userDataSource.getByUsername(username);
    }

    @Override
    public Boolean usernameAlreadyExist(String username) {
        return userDataSource.usernameAlreadyExist(username);
    }

    @Override
    public Boolean emailAlreadyExist(String email) {
        return userDataSource.emailAlreadyExist(email);
    }

    @Override
    public User getByEmail(String email) {
        return userDataSource.getByEmail(email);
    }

    @Override
    public User getById(String uid) {
        return userDataSource.getById(uid);
    }
}
