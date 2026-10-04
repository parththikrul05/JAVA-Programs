CREATE DATABASE IF NOT EXISTS inventory_db;
USE inventory_db;

CREATE TABLE IF NOT EXISTS products (
    product_id INT PRIMARY KEY AUTO_INCREMENT,
    product_name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

INSERT INTO products (product_name, quantity, price) VALUES
('Wireless Mouse', 45, 29.99),
('Mechanical Keyboard', 20, 89.50),
('27-inch Monitor', 12, 249.99);