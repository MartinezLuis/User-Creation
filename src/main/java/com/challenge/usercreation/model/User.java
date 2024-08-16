package com.challenge.usercreation.model;

import com.challenge.usercreation.model.jpa.UserJpa;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @JsonIgnore
    private Long id;

    @JsonInclude(Include.NON_NULL)
    private String name;

    @JsonInclude(Include.NON_NULL)
    private String email;

    @JsonInclude(Include.NON_NULL)
    private String password;

    @JsonInclude(Include.NON_NULL)
    private UUID uuid;

    @JsonInclude(Include.NON_NULL)
    private UUID token;

    @JsonInclude(Include.NON_NULL)
    private LocalDateTime createdAt;

    @JsonInclude(Include.NON_NULL)
    private LocalDateTime updatedAt;

    @JsonInclude(Include.NON_NULL)
    private LocalDateTime lastLoginAt;

    @JsonInclude(Include.NON_NULL)
    private Boolean active;

    @JsonInclude(Include.NON_NULL)
    private List<Phone> phones;

    public UserJpa toJpa() {
        return UserJpa.builder()
                .uuid(UUID.randomUUID())
                .name(this.getName())
                .email(this.getEmail())
                .password(this.getPassword())
                .token(UUID.randomUUID())
                .active(true)
                .phones(this.phones.stream().map(Phone::toJpa).collect(Collectors.toList()))
                .build();
    }

    public static User fromJpa(UserJpa jpa) {
        return User.builder()
                .id(jpa.getId())
                .name(jpa.getName())
                .email(jpa.getEmail())
                .uuid(jpa.getUuid())
                .token(jpa.getToken())
                .createdAt(jpa.getCreatedAt())
                .updatedAt(jpa.getUpdatedAt())
                .lastLoginAt(jpa.getLastLoginAt())
                .active(jpa.getActive())
                .phones(jpa.getPhones().stream().map(Phone::fromJpa).collect(Collectors.toList()))
                .build();
    }

    public Boolean validateMail()
    {
        String regex = "^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.email);
        return matcher.matches();
    }

    public Boolean validatePassword(String regex)
    {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.password);
        return matcher.matches();
    }
}
