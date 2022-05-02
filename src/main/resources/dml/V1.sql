CREATE TABLE address
(
    id          bigserial   NOT NULL PRIMARY KEY,
    post_code   varchar(8)  NOT NULL,
    land        varchar(16) NOT NULL,
    city        varchar(16) NOT NULL,
    street      varchar(32) NOT NULL,
    home_number varchar(8)  NOT NULL
);

CREATE TABLE "user"
(
    id           bigserial                   NOT NULL PRIMARY KEY,
    first_name   varchar(32)                 NOT NULL,
    last_name    varchar(32)                 NOT NULL,
    password     varchar(32)                 NOT NULL,
    api_key      varchar(32)                 NOT NULL,
    phone_number varchar(16),
    email        varchar(32)                 NOT NULL UNIQUE,
    enabled      boolean                     NOT NULL DEFAULT false,
    baned        boolean                     NOT NULL DEFAULT false,
    ban_reason   varchar(128),
    address_id   bigint,
    created_at   TIMESTAMP WITHOUT TIME ZONE NOT NULL,

    FOREIGN KEY (address_id)
        REFERENCES address (id)
);

CREATE TABLE user_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,

    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id)
        REFERENCES "user" (id)
);

CREATE TABLE land
(
    id   bigserial   NOT NULL PRIMARY KEY,
    name varchar(32) NOT NULL
);

CREATE TABLE wine
(
    id              bigserial                   NOT NULL PRIMARY KEY,
    name            varchar(32)                 NOT NULL,
    descriptions    varchar(256),
    image_url       varchar(64),
    enable          boolean                     NOT NULL DEFAULT false,
    price           varchar(32),
    discount        smallint                    NOT NULL DEFAULT 0,
    amount_for_sale int                         NOT NULL DEFAULT 0,
    sold_amount     int                         NOT NULL DEFAULT 0,
    land_id         bigint                      NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE NOT NULL,

    FOREIGN KEY (land_id)
        REFERENCES wine (id)
);

CREATE TABLE customer_review
(
    user_id bigint   NOT NULL,
    wine_id bigint   NOT NULL,
    message varchar(256),
    rating  smallint NOT NULL,
    confirm boolean  NOT NULL default false,

    PRIMARY KEY (user_id, wine_id)
);

CREATE TABLE cart
(
    id      bigserial NOT NULL PRIMARY KEY,
    user_id bigint    NOT NULL,

    FOREIGN KEY (user_id)
        REFERENCES "user" (id)
);

CREATE TABLE cart_item
(
    id      bigserial NOT NULL PRIMARY KEY,
    wine_id bigint    NOT NULL,
    cart_id bigint    not null,
    amount  smallint  NOT NULL default 1,

    FOREIGN KEY (wine_id)
        REFERENCES wine (id),
    FOREIGN KEY (cart_id)
        REFERENCES cart (id)
);