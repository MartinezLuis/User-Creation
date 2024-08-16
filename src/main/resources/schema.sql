CREATE SEQUENCE phone_seq START WITH 1 INCREMENT BY 1;

CREATE SEQUENCE tbl_user_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE phone
(
    phone_id     BIGINT NOT NULL,
    number       VARCHAR(255),
    city_code    VARCHAR(255),
    country_code VARCHAR(255),
    user_id      BIGINT NOT NULL,
    CONSTRAINT pk_phone PRIMARY KEY (phone_id)
);

CREATE TABLE tbl_user
(
    user_id       BIGINT NOT NULL,
    uuid          UUID,
    name          VARCHAR(255),
    email         VARCHAR(255),
    password      VARCHAR(255),
    token         UUID,
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP,
    last_login_at TIMESTAMP,
    active        BOOLEAN,
    CONSTRAINT pk_tbl_user PRIMARY KEY (user_id)
);

ALTER TABLE tbl_user
    ADD CONSTRAINT uc_tbl_user_email UNIQUE (email);

ALTER TABLE phone
    ADD CONSTRAINT FK_PHONE_ON_USER FOREIGN KEY (user_id) REFERENCES tbl_user (user_id);