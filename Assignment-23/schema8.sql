CREATE DATABASE IF NOT EXISTS company_db;
USE company_db;

-- 2. Create the employees table
CREATE TABLE IF NOT EXISTS employees (
    emp_id INT PRIMARY KEY,
    emp_name VARCHAR(100) NOT NULL,
    department VARCHAR(50) NOT NULL,
    salary DECIMAL(10, 2) NOT NULL
);

-- 3. Insert sample records
INSERT INTO employees (emp_id, emp_name, department, salary) VALUES
(101, 'Alice Smith', 'Engineering', 85000.00),
(102, 'Bob Jones', 'Marketing', 62000.50),
(103, 'Carol White', 'Human Resources', 58000.00),
(104, 'David Lee', 'Engineering', 92000.00),
(105, 'Emma Watson', 'Finance', 75000.75);

-- 4. Query used in Program 2 to retrieve employee details
SELECT emp_id, emp_name, department, salary FROM employees;