CREATE TABLE users
(
  id            BIGINT NOT NULL PRIMARY KEY,
  first_name    VARCHAR(50),
  last_name     VARCHAR(50),
  password      VARCHAR(50),
  email         VARCHAR(50),
  phone_number  VARCHAR(20),
  date_of_birth DATE
);

CREATE SEQUENCE users_seq START WITH 100 INCREMENT BY 50;
