package com.challenge.usercreation.controllers;

import com.challenge.usercreation.core.usecases.CreateUserUseCase;
import com.challenge.usercreation.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CreateUserController {

    // Minimum seven characters, at least one letter and one number
    private static final String PASS_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{7,}$";

    private final CreateUserUseCase createUserUseCase;

    public CreateUserController(
            final CreateUserUseCase createUserUseCase
    ) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping("/user/create")
    @ResponseStatus( HttpStatus.CREATED )
    public User createUser(@RequestBody final User user) {

        if (!user.validateMail()) {
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Invalid email" );
        }

        if (!user.validatePassword(PASS_PATTERN)) {
            throw new ResponseStatusException( HttpStatus.BAD_REQUEST, "Invalid password" );
        }

        return createUserUseCase.execute(user);
    }
}
