package com.challenge.usercreation.controllers;

import com.challenge.usercreation.core.usecases.CreateUserUseCase;
import com.challenge.usercreation.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateUserController {

    private final CreateUserUseCase createUserUseCase;

    public CreateUserController(
            final CreateUserUseCase createUserUseCase
    ) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping("/user/create")
    @ResponseStatus( HttpStatus.CREATED )
    public User createUser(@RequestBody final User user) {
        return createUserUseCase.execute(user);
    }
}
