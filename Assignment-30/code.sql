use parth;

CREATE TABLE employee (
    emp_id INT PRIMARY KEY,
    name VARCHAR(50),
    department VARCHAR(50),
    salary DOUBLE
);

CREATE TABLE patient (
    patient_id INT PRIMARY KEY,
    name VARCHAR(50),
    age INT,
    disease VARCHAR(100),
    treatment_status VARCHAR(50)
);