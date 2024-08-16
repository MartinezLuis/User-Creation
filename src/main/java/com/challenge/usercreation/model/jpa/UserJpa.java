package com.challenge.usercreation.model.jpa;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TBL_USER")
public class UserJpa {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private UUID uuid;

    private String name;

    @Column(unique=true)
    private String email;

    private String password;

    private UUID token;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @UpdateTimestamp
    @Column(name = "last_login_at")
    private LocalDateTime lastLoginAt;

    private Boolean active;

    @OneToMany(mappedBy = "user")
    private List<PhoneJpa> phones;
}
