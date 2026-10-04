CREATE DATABASE IF NOT EXISTS academic_db;
USE academic_db;

CREATE TABLE IF NOT EXISTS students (
    roll_no INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    course VARCHAR(50) NOT NULL,
    marks DOUBLE NOT NULL
);