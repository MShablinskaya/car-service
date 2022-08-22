CREATE TABLE documents
(
    id              serial primary key,
    license_number  varchar(256),
    type            varchar(256),
    expiration_date timestamp
);
CREATE TABLE users
(
    id         serial primary key,
    first_name varchar(256),
    last_name  varchar(256),
    email      varchar(256) unique,
    password   varchar(256),
    license_id integer,
    role       varchar(256),
    FOREIGN KEY (license_id) REFERENCES documents (id)
);

CREATE TABLE car
(
    id                  serial primary key,
    brand               varchar(256),
    model               varchar(256),
    color               varchar(256),
    release_year        integer,
    registration_number integer,
    owner_id            integer,
    availability        boolean,
    FOREIGN KEY (owner_id) REFERENCES users (id)
);


CREATE TABLE orders
(
    id           serial primary key,
    customer_id  integer,
    car_id       integer,
    state        varchar(256),
    booking_date timestamp,
    return_date  timestamp,
    FOREIGN KEY (customer_id) REFERENCES users (id),
    FOREIGN KEY (car_id) REFERENCES car (id)
);