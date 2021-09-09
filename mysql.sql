create database projetopaokentin;
use projetopaokentin;

CREATE TABLE tbl_pao (
  codigo int(10) NOT NULL AUTO_INCREMENT,
  nome varchar(150) NOT NULL,
  descricao varchar(200) NOT NULL,
  tempo_assar int(10) NOT NULL,
  PRIMARY KEY (codigo)
) ENGINE=InnoDB;

CREATE TABLE tbl_fornada (
  codigo int(10) NOT NULL AUTO_INCREMENT,
  entrada datetime NOT NULL,
  saida datetime NOT NULL,
  pao_cod int(10) NOT NULL,
  PRIMARY KEY (codigo),
  KEY fk_fornada_pao (pao_cod),
  CONSTRAINT fk_fornada_pao
  	FOREIGN KEY (pao_cod)
  	REFERENCES tbl_pao (codigo)
  	ON DELETE CASCADE
) ENGINE=InnoDB;