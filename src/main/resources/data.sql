DROP TABLE IF EXISTS book;
DROP TABLE IF EXISTS bookcategory;

CREATE TABLE book (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  bookcategory_id INT NOT NULL
);

CREATE TABLE bookcategory (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

INSERT INTO bookcategory (name) VALUES
  ('Technology'),
  ('Biography');

INSERT INTO book (title, bookcategory_id) VALUES
  ('Clean Code', 1),
  ('Obama', 2),
  ('Design Pattern', 1);