package com.bruno13palhano.data.shared;

import com.bruno13palhano.User;

public interface UserData<T extends User> {
    void insert(T user);

    void update(T user);

    void delete(String uid);

    T getByUsername(String username);

    Boolean usernameAlreadyExist(String username);

    Boolean emailAlreadyExist(String email);

    T getByEmail(String email);

    T getById(String uid);
}
