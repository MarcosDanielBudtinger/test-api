CREATE TABLE CATEGORIA (
    id serial NOT NULL,
    nome character varying(50) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO CATEGORIA (nome) values ('Lazer');
INSERT INTO CATEGORIA (nome) values ('Alimentação');
INSERT INTO CATEGORIA (nome) values ('Supermercado');
INSERT INTO CATEGORIA (nome) values ('Farmácia');
INSERT INTO CATEGORIA (nome) values ('Outros');
INSERT INTO CATEGORIA (nome) values ('teste');