package com.challenge.usercreation.repositories;

import com.challenge.usercreation.core.usecases.interfaces.InsertUserRepository;
import com.challenge.usercreation.model.Phone;
import com.challenge.usercreation.model.User;
import com.challenge.usercreation.model.jpa.UserJpa;
import com.challenge.usercreation.repositories.interfaces.PhoneJpaRepository;
import com.challenge.usercreation.repositories.interfaces.UserJpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class InsertUserJpaRepository implements InsertUserRepository {

    private final UserJpaRepository userJpaRepository;

    private final PhoneJpaRepository phoneJpaRepository;

    public InsertUserJpaRepository(
            final UserJpaRepository userJpaRepository,
            final PhoneJpaRepository phoneJpaRepository
    ) {
        this.userJpaRepository = userJpaRepository;
        this.phoneJpaRepository = phoneJpaRepository;
    }

    @Transactional
    @Override
    public User execute(User user) {

        UserJpa userJpa = this.userJpaRepository.saveAndFlush(user.toJpa());

        savePhones(userJpa);

        return this.getNewUser(userJpa);
    }

    private void savePhones(UserJpa userJpa) {
        userJpa.getPhones()
                .forEach(phone -> {
                    phone.setUser(userJpa);
                    Phone.fromJpa(this.phoneJpaRepository.save(phone));
                });
    }

    public User getNewUser(UserJpa jpa) {
        return User.builder()
                .uuid(jpa.getUuid())
                .token(jpa.getToken())
                .createdAt(jpa.getCreatedAt())
                .updatedAt(jpa.getUpdatedAt())
                .lastLoginAt(jpa.getLastLoginAt())
                .active(jpa.getActive())
                .build();
    }
}
