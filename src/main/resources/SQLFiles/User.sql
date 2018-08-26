CREATE DATABASE IF NOT EXISTS houseMateDB;

USE houseMateDB;

--password not included yet used for tests for now
CREATE TABLE IF NOT EXISTS users(
  id INT NOT NULL AUTO_INCREMENT,
  username VARCHAR(200) UNIQUE NOT NULL,
  emailAddress VARCHAR(200) UNIQUE NOT NULL,
  PRIMARY KEY(id)
);

