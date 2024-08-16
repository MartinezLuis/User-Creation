package com.challenge.usercreation.model.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PHONE")
public class PhoneJpa {

    @Id
    @GeneratedValue
    @Column(name = "phone_id")
    private Long id;

    private String number;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "country_code")
    private String countryCode;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserJpa user;
}
