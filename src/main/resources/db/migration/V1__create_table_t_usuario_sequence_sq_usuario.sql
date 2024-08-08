CREATE SEQUENCE sq_usuario
    START WITH 1
    INCREMENT BY 1 NOCACHE
    NOCYCLE;

CREATE TABLE t_usuario
(
    id    INTEGER     DEFAULT sq_usuario.NEXTVAL NOT NULL,
    nome  VARCHAR2(100) NOT NULL,
    email VARCHAR2(100) NOT NULL,
    senha VARCHAR2(20) NOT NULL,
    role  VARCHAR(20) DEFAULT 'USER'
);
