CREATE DATABASE IF NOT EXISTS user01_pizzeria CHARACTER SET utf8 COLLATE utf8_general_ci;

-- grant permission user01_pizzeria to user01
-- grant all on user01_pizzeria.* to user01@'%' identified by '1mot2PassTropCool';

CREATE TABLE IF NOT EXISTS ingredients (
	name VARCHAR(255) PRIMARY KEY,
	price DECIMAL(13,2) NOT NULL
);

INSERT INTO ingredients (name, price)
VALUES
('Dough', 3),
('Tomato Sauce', 1.5),
('White Cream', 1.5),
('Cheese', 2),
('Mushroom', 1),
('Sea food', 2),
('Ham', 2),
('Olive', 1);
