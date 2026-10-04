CREATE DATABASE IF NOT EXISTS school_db;
USE school_db;

CREATE TABLE IF NOT EXISTS students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(50),
    gpa DECIMAL(3, 2)
);

INSERT INTO students (name, department, gpa) VALUES
('Aarav Sharma', 'Computer Science', 3.85),
('Ananya Patel', 'Information Technology', 3.70),
('Rohan Verma', 'Mechanical Engineering', 3.45);