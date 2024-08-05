CREATE TABLE items
(
  id             BIGINT NOT NULL PRIMARY KEY,
  name           VARCHAR(50),
  price          FLOAT(53), -- double precision
  stock_quantity INT
);

CREATE SEQUENCE items_seq START WITH 100 INCREMENT BY 50;

