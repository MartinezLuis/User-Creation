package com.challenge.usercreation.repositories.interfaces;

import com.challenge.usercreation.model.jpa.UserJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpa, Long> {
}
