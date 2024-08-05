INSERT INTO users (first_name, last_name, password, email, phone_number, date_of_birth)
VALUES
    ('John', 'Doe', 'password123', 'john.doe@example.com', '555-111-1234', '1990-05-15'),
    ('Jane', 'Smith', 'letmein456', 'jane.smith@example.com', '555-222-5678', '1985-09-23'),
    ('Michael', 'Johnson', 'securepass', 'michael.johnson@example.com', '555-333-9876', '1982-12-07'),
    ('Emily', 'Brown', 'password', 'emily.brown@example.com', '555-444-2345', '1993-07-11'),
    ('William', 'Davis', 'p@ssw0rd!', 'william.davis@example.com', '555-555-7890', '1988-03-29'),
    ('Sarah', 'Wilson', 'abc123xyz', 'sarah.wilson@example.com', '555-666-3456', '1995-02-14'),
    ('Matthew', 'Martinez', 'qwerty123', 'matthew.martinez@example.com', '555-777-6789', '1991-11-30'),
    ('Jessica', 'Garcia', 'pa$$w0rd', 'jessica.garcia@example.com', '555-888-4321', '1987-06-18'),
    ('Daniel', 'Rodriguez', 'pass123word', 'daniel.rodriguez@example.com', '555-999-8765', '1984-04-22'),
    ('Ashley', 'Lopez', 'securepwd', 'ashley.lopez@example.com', '555-1010-2109', '1994-08-08'),
    ('Christopher', 'Gonzalez', 'letmein', 'christopher.gonzalez@example.com', '555-1111-6543', '1989-01-02'),
    ('Amanda', 'Miller', 'password!', 'amanda.miller@example.com', '555-1212-1098', '1992-10-17'),
    ('James', 'Hernandez', '12345678', 'james.hernandez@example.com', '555-1313-7654', '1986-12-03'),
    ('Jennifer', 'Walker', 'passw0rd', 'jennifer.walker@example.com', '555-1414-3210', '1996-04-25'),
    ('Robert', 'Perez', 'password123!', 'robert.perez@example.com', '555-1515-6547', '1983-09-19'),
    ('Megan', 'Sanchez', 'securepassword', 'megan.sanchez@example.com', '555-1616-7891', '1990-07-08'),
    ('David', 'Young', 'abc@123', 'david.young@example.com', '555-1717-4325', '1985-05-12'),
    ('Mary', 'Flores', 'qazwsxedc', 'mary.flores@example.com', '555-1818-8761', '1988-02-28'),
    ('Joshua', 'Nguyen', 'letmein@123', 'joshua.nguyen@example.com', '555-1919-2109', '1993-11-15'),
    ('Samantha', 'King', 'passwordabc', 'samantha.king@example.com', '555-2020-5678', '1987-08-21');

INSERT INTO items (name, price, stock_quantity)
VALUES
    ('Apples', 1.99, 100),
    ('Bananas', 0.79, 150),
    ('Oranges', 1.49, 120),
    ('Potatoes (5 lb bag)', 2.99, 80),
    ('Carrots (per pound)', 0.99, 120),
    ('Lettuce', 1.29, 100),
    ('Tomatoes', 1.69, 90),
    ('Cucumbers', 0.89, 110),
    ('Bell Peppers', 1.79, 80),
    ('Onions (per pound)', 0.69, 130),
    ('Milk (1 gallon)', 3.49, 60),
    ('Eggs (dozen)', 2.29, 100),
    ('Bread (loaf)', 2.99, 120),
    ('Chicken Breasts (per pound)', 3.99, 80),
    ('Ground Beef (per pound)', 4.49, 90),
    ('Salmon Fillet (per pound)', 9.99, 50),
    ('Pasta (1 lb)', 1.49, 150),
    ('Rice (2 lb bag)', 2.79, 100),
    ('Cereal (box)', 3.29, 80),
    ('Coffee (ground, 12 oz)', 5.99, 70);

INSERT INTO bills (issue_date, user_id, total_amount)
VALUES
    ('2024-07-15', 1, 9.87),
    ('2024-07-16', 2, 6.56),
    ('2024-07-17', 3, 3.08),
    ('2024-07-18', 4, 2.47),
    ('2024-07-19', 5, 5.78),
    ('2024-07-20', 7, 10.96),
    ('2024-07-21', 8, 8.78),
    ('2024-07-22', 10, 9.28),
    ('2024-07-23', 11, 3.78),
    ('2024-07-24', 12, 1.58),
    ('2024-07-25', 13, 3.18),
    ('2024-07-26', 14, 3.78),
    ('2024-07-27', 15, 7.38),
    ('2024-07-28', 16, 7.78),
    ('2024-07-29', 17, 11.76),
    ('2024-07-30', 18, 7.28),
    ('2024-07-31', 19, 3.48),
    ('2024-08-01', 20, 3.48),
    ('2024-08-02', 1, 1.78),
    ('2024-08-03', 2, 6.66),
    ('2024-08-04', 3, 2.98),
    ('2024-08-05', 4, 5.76),
    ('2024-08-06', 5, 8.36),
    ('2024-08-07', 7, 4.47),
    ('2024-08-08', 8, 7.78),
    ('2024-08-09', 10, 3.29),
    ('2024-08-10', 11, 11.97),
    ('2024-08-11', 12, 4.78),
    ('2024-08-12', 13, 3.18),
    ('2024-08-13', 14, 7.48);


INSERT INTO bill_item_records (bill_id, item_id, quantity, purchased_unit_price)
VALUES
    (1, 1, 3, 1.99),
    (1, 5, 3, 0.99),
    (2, 3, 1, 1.49),
    (2, 7, 2, 1.69),
    (3, 6, 1, 1.29),
    (3, 9, 1, 1.79),
    (4, 8, 2, 0.89),
    (4, 10, 1, 0.69),
    (5, 11, 1, 3.49),
    (5, 12, 1, 2.29),
    (6, 13, 2, 2.99),
    (6, 15, 1, 4.49),
    (7, 14, 1, 3.99),
    (7, 17, 2, 1.49),
    (8, 16, 1, 2.79),
    (8, 19, 1, 5.99),
    (9, 18, 1, 3.29),
    (9, 20, 2, 9.99),
    (10, 1, 1, 1.99),
    (10, 4, 1, 2.99),
    (11, 2, 1, 0.79),
    (11, 6, 1, 1.29),
    (12, 3, 1, 1.49),
    (12, 7, 3, 1.69),
    (13, 5, 3, 0.99),
    (13, 9, 1, 1.79),
    (14, 8, 2, 0.89),
    (14, 11, 2, 3.49),
    (15, 10, 1, 0.69),
    (15, 13, 1, 2.99),
    (16, 12, 1, 2.29),
    (16, 15, 1, 4.49),
    (17, 14, 1, 3.99),
    (17, 17, 1, 1.49),
    (18, 16, 1, 2.79),
    (18, 19, 3, 5.99),
    (19, 18, 1, 3.29),
    (19, 20, 1, 9.99),
    (20, 1, 2, 1.99),
    (20, 3, 1, 1.49),
    (21, 2, 1, 0.79),
    (21, 5, 3, 0.99),
    (22, 4, 1, 2.99),
    (22, 7, 2, 1.69),
    (23, 6, 1, 1.29),
    (23, 9, 1, 1.79),
    (24, 8, 2, 0.89),
    (24, 10, 1, 0.69),
    (25, 11, 1, 3.49),
    (25, 12, 1, 2.29),
    (26, 13, 2, 2.99),
    (26, 15, 1, 4.49),
    (27, 14, 1, 3.99),
    (27, 17, 2, 1.49),
    (28, 16, 1, 2.79),
    (28, 19, 1, 5.99),
    (29, 18, 1, 3.29),
    (29, 20, 2, 9.99),
    (30, 1, 1, 1.99),
    (30, 4, 1, 2.99);


