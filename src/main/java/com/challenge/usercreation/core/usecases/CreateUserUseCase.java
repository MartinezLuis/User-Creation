package com.challenge.usercreation.core.usecases;

import com.challenge.usercreation.core.usecases.interfaces.InsertUserRepository;
import com.challenge.usercreation.model.User;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {

    private final InsertUserRepository insertUserRepository;

    public CreateUserUseCase(InsertUserRepository insertUserRepository) {
        this.insertUserRepository = insertUserRepository;
    }

    public User execute(final User user) {
        return insertUserRepository.execute(user);
    }
}
