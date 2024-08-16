package com.challenge.usercreation.core.usecases.interfaces;

import com.challenge.usercreation.model.User;

@FunctionalInterface
public interface InsertUserRepository {
    User execute(User user);
}
