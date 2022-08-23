INSERT INTO documents (license_number, type, expiration_date )VALUES('GIBDD 7725', 'DRIVER_LICENSE', '2030-09-03');
INSERT INTO documents (license_number, type, expiration_date )VALUES('GIBDD 7777', 'DRIVER_LICENSE', '2030-08-09');
INSERT INTO documents (license_number, type, expiration_date )VALUES('GIBDD 1904', 'DRIVER_LICENSE', '2025-12-03');
INSERT INTO documents (license_number, type, expiration_date )VALUES('GIBDD 6663', 'DRIVER_LICENSE', '2026-09-22');

INSERT INTO users (first_name, last_name, email, password, license_id, role)VALUES('John', 'Doe', 'john_doe@yopmail.com', 'qwerty123', 1, 'OWNER');
INSERT INTO users (first_name, last_name, email, password, license_id, role)VALUES('Elvis', 'Presley', 'king_Presley@yopmail.com', 'qwerty123', 2, 'OWNER');
INSERT INTO users (first_name, last_name, email, password, license_id, role)VALUES('Jane', 'Doe', 'jane_doe@yopmail.com', 'qwerty123', 3, 'CUSTOMER');
INSERT INTO users (first_name, last_name, email, password, license_id, role)VALUES('Charles', 'Manson', 'charly_thebeast@yopmail.com', 'qwerty123', 4, 'CUSTOMER');


INSERT INTO cars (brand, model, color, release_year, registration_number, owner_id, availability)VALUES('Mercedes', 'AMG GT', 'silver', '2015', '6446 II-1', 1, true);
INSERT INTO cars (brand, model, color, release_year, registration_number, owner_id, availability)VALUES('Audi', 'A6', 'matte-black', '2018', '6593 II-6', 1, true);
INSERT INTO cars (brand, model, color, release_year, registration_number, owner_id, availability)VALUES('Volvo', 'V40', 'cherry', '2017', '7285 AT-4', 2, true);
INSERT INTO cars (brand, model, color, release_year, registration_number, owner_id, availability)VALUES('Chevrolet', 'Equinox', 'black', '2020', '7777 AB-7', 2, true);
INSERT INTO cars (brand, model, color, release_year, registration_number, owner_id, availability)VALUES('Zhiguli', 'VAZ-2101', 'red', '1995', '1234 AB-3', 1, true);