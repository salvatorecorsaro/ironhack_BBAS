CREATE TABLE hibernate_sequences
(
    sequence_name varchar(255) NOT NULL
        PRIMARY KEY,
    next_val      bigint NULL
);

CREATE TABLE transaction
(
    id                  bigint AUTO_INCREMENT
        PRIMARY KEY,
    amount              decimal(19, 2) NULL,
    currency            varchar(255) NULL,
    date_time           datetime(6) NULL,
    receiver_account_id bigint NULL,
    sender_account_id   bigint NULL
);

CREATE TABLE user
(
    dtype      varchar(31) NOT NULL,
    id         bigint AUTO_INCREMENT
        PRIMARY KEY,
    password   varchar(255) NULL,
    username   varchar(255) NULL,
    hashed_key varchar(255) NULL
);

CREATE TABLE account_holder
(
    id              bigint AUTO_INCREMENT
        PRIMARY KEY,
    date_of_birth   date NULL,
    email           varchar(255) NULL,
    first_name      varchar(255) NULL,
    last_name       varchar(255) NULL,
    middle_name     varchar(255) NULL,
    country         varchar(255) NULL,
    details         varchar(255) NULL,
    number          varchar(255) NULL,
    street          varchar(255) NULL,
    account_user_id bigint NULL,
    CONSTRAINT fkim6v2onawdjjq5yfofuivwbv7
        FOREIGN KEY (account_user_id) REFERENCES user (id)
);

CREATE TABLE checking
(
    id                      bigint NOT NULL
        PRIMARY KEY,
    amount                  decimal(19, 2) NULL,
    currency                varchar(255) NULL,
    creation_date           date NULL,
    penalty_fee             decimal(19, 2) NULL,
    primary_owner_id        bigint NULL,
    secondary_owner_id      bigint NULL,
    minimum_balance         decimal(19, 2) NULL,
    monthly_maintenance_fee decimal(19, 2) NULL,
    secret_key              varchar(255) NULL,
    status                  varchar(255) NULL,
    CONSTRAINT fk_b82tfohl96mgd9qpyvuh76y50
        FOREIGN KEY (secondary_owner_id) REFERENCES account_holder (id),
    CONSTRAINT fk_musxfdudc7uojslyp98d3mauw
        FOREIGN KEY (primary_owner_id) REFERENCES account_holder (id)
);

CREATE TABLE credit_card
(
    id                 bigint NOT NULL
        PRIMARY KEY,
    amount             decimal(19, 2) NULL,
    currency           varchar(255) NULL,
    creation_date      date NULL,
    penalty_fee        decimal(19, 2) NULL,
    primary_owner_id   bigint NULL,
    secondary_owner_id bigint NULL,
    credit_limit       decimal(19, 2) NULL,
    interest_rate      decimal(19, 2) NULL,
    CONSTRAINT fk_dv6lpkj5lsejxgbd6d49oq5xv
        FOREIGN KEY (primary_owner_id) REFERENCES account_holder (id),
    CONSTRAINT fk_nd7ca4vcwmf7tmfvgux18q7gk
        FOREIGN KEY (secondary_owner_id) REFERENCES account_holder (id)
);

CREATE TABLE saving
(
    id                 bigint NOT NULL
        PRIMARY KEY,
    amount             decimal(19, 2) NULL,
    currency           varchar(255) NULL,
    creation_date      date NULL,
    penalty_fee        decimal(19, 2) NULL,
    primary_owner_id   bigint NULL,
    secondary_owner_id bigint NULL,
    interest_rate      decimal(19, 2) NULL,
    minimum_balance    decimal(19, 2) NULL,
    secret_key         varchar(255) NULL,
    status             varchar(255) NULL,
    CONSTRAINT fk_fd1hhy1vrlvxkgs9sw4tc037w
        FOREIGN KEY (secondary_owner_id) REFERENCES account_holder (id),
    CONSTRAINT fk_jmnqtc6c87jm0f7611icky0cl
        FOREIGN KEY (primary_owner_id) REFERENCES account_holder (id)
);

CREATE TABLE student
(
    id                 bigint NOT NULL
        PRIMARY KEY,
    amount             decimal(19, 2) NULL,
    currency           varchar(255) NULL,
    creation_date      date NULL,
    penalty_fee        decimal(19, 2) NULL,
    primary_owner_id   bigint NULL,
    secondary_owner_id bigint NULL,
    secret_key         varchar(255) NULL,
    status             varchar(255) NULL,
    CONSTRAINT fk_42i3cw41m5kqsm1huuffbevm8
        FOREIGN KEY (primary_owner_id) REFERENCES account_holder (id),
    CONSTRAINT fk_jb5ce5t52sujha8wjhkd921a5
        FOREIGN KEY (secondary_owner_id) REFERENCES account_holder (id)
);


