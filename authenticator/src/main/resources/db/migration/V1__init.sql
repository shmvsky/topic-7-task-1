-- V1__init.sql

-- Create Account Table
CREATE TABLE account (
     id BIGSERIAL PRIMARY KEY,
     fullname VARCHAR(255) NOT NULL,
     email VARCHAR(255) NOT NULL UNIQUE,
     password VARCHAR(255) NOT NULL,
     verified BOOLEAN DEFAULT false
);

-- Create VerificationCode Table
CREATE TABLE verification_code (
       id BIGSERIAL PRIMARY KEY ,
       email VARCHAR(255) NOT NULL,
       code VARCHAR(255) NOT NULL,
       exp TIMESTAMP NOT NULL,
       CONSTRAINT fk_verification_code_email FOREIGN KEY (email) REFERENCES account(email) ON DELETE CASCADE
);
