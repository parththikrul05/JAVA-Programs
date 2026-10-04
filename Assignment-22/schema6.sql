CREATE DATABASE IF NOT EXISTS hospital_db;
USE hospital_db;

CREATE TABLE IF NOT EXISTS staff (
    staff_id INT AUTO_INCREMENT PRIMARY KEY,
    login_id VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(50) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    role ENUM('Doctor', 'Nurse') NOT NULL
);

INSERT INTO staff (login_id, password, full_name, role) VALUES 
('DOC101', 'docpass123', 'Dr. Aris Thorne', 'Doctor'),
('NRS201', 'nursepriority', 'Nurse Sarah Jenkins', 'Nurse');