CREATE TABLE Fabricantes(
id int NOT NULL AUTO_INCREMENT COMMENT "Primary Key",
name varchar(255) unique,
PRIMARY KEY (id)
);

CREATE TABLE produtos (
id int NOT NULL AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100),
preco FLOAT,
descricao VARCHAR(100),
fabricante_id int,
PRIMARY KEY (fabricante_id) REFERENCES fabricantes (id)
);