CREATE TABLE IF NOT EXISTS bill_item_records(
  id                     BIGINT NOT NULL PRIMARY KEY,
  quantity               INT,
  purchased_unit_price   FLOAT(53), -- double precision

  bill_id                BIGINT references bills(id),
  item_id                BIGINT references items(id)

);

CREATE SEQUENCE bill_item_record_seq START WITH 100 INCREMENT BY 50;

