CREATE TABLE bills
(
  id             BIGINT NOT NULL PRIMARY KEY,
  issue_date     DATE,
  total_amount   FLOAT(53), -- double precision

  user_id        BIGINT references users(id)
);

CREATE SEQUENCE bills_seq START WITH 100 INCREMENT BY 50;


