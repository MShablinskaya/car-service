CREATE TABLE driving_license
(
    id              serial NOT NULL,
    license_number  varchar(255),
    type            varchar(255),
    expiration_date timestamp,
    PRIMARY KEY (id)
);

CREATE TABLE car
(
    id                  serial       NOT NULL,
    brand               varchar(255),
    model               varchar(255),
    color               varchar(255),
    release_year        integer,
    registration_number integer,
    owner_id            integer,
    availability        boolean,
    PRIMARY KEY (id),
    CONSTRAINT car_owner_id_owners_id_foreign FOREIGN KEY (owner_id) REFERENCES owners (id) ON DELETE CASCADE
);

CREATE TABLE users
(
    id         serial NOT NULL,
    first_name varchar(255),
    last_name  varchar(255),
    email      varchar(255) unique,
    password   varchar(255),
    license_id integer,
    role       varchar(255),
    PRIMARY KEY (id),
    CONSTRAINT customers_license_id_driving_license_id_foreign FOREIGN KEY (license_id) REFERENCES driving_license (id)
);

CREATE TABLE orders
(
    id           serial NOT NULL,
    customer_id  integer,
    car_id       integer,
    state        varchar(255),
    booking_date timestamp,
    return_date  timestamp,
    PRIMARY KEY (id),
    CONSTRAINT orders_id_customers_id_foreign FOREIGN KEY (customer_id) REFERENCES customers (id),
    CONSTRAINT orders_car_id_car_id_foreign FOREIGN KEY (car_id) REFERENCES car (id)
);