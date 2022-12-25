package com.svu.bus.controller;

import com.svu.bus.model.User;

public interface InsertAction {
    void insertComplate(User user);
    void insertError(String s);
}
