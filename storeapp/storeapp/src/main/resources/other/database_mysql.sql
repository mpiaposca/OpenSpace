DROP SCHEMA purchases;
CREATE SCHEMA purchases;
USE purchases;

CREATE TABLE client (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50),
	surname VARCHAR(50),
	email VARCHAR(90)
);

CREATE TABLE product (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(50),
	bar_code VARCHAR(70),
    quantity FLOAT,
    type VARCHAR(50),
    price FLOAT,
	version LONG
);

CREATE TABLE orders (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	client INTEGER,
	data DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (client) REFERENCES client(id)
);

CREATE TABLE lineoforder (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	order INTEGER,
	product INTEGER,
    quantity INTEGER,
    FOREIGN KEY (order) REFERENCES order(id),
    FOREIGN KEY (product) REFERENCES product(id)
);