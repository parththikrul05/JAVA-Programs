CREATE DATABASE IF NOT EXISTS auth_db;
USE auth_db;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL
);

INSERT INTO users (username, password) VALUES 
('admin', 'admin123'),
('john_doe', 'securePass');