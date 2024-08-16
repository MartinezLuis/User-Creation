package com.challenge.usercreation.repositories.interfaces;

import com.challenge.usercreation.model.jpa.PhoneJpa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneJpaRepository extends JpaRepository<PhoneJpa, Long> {
}
