-- 1. Create the database
CREATE DATABASE IF NOT EXISTS your_database;
USE your_database;

-- 2. Create the products table
CREATE TABLE IF NOT EXISTS products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50) NOT NULL
);

-- 3. Insert sample records
INSERT INTO products (name, category) VALUES
('Wireless Mouse', 'Electronics'),
('Ergonomic Keyboard', 'Electronics'),
('USB-C Hub', 'Accessories'),
('Desk Lamp', 'Furniture'),
('Standing Desk', 'Furniture');

-- 4. Query used in Program 1 to retrieve all records
SELECT id, name, category FROM products;