

CREATE TABLE sabor(
   id          BIGINT              NOT NULL auto_increment,
   nome        VARCHAR (250)    NOT NULL ,
   PRIMARY KEY (id)
);



CREATE TABLE cobertura(
   id           BIGINT            NOT NULL auto_increment,
   nome        VARCHAR (250)    NOT NULL,
   tipo       VARCHAR (250)             ,
   PRIMARY KEY (id)
);



CREATE TABLE massa(
    id          BIGINT             NOT NULL auto_increment,
	cor        VARCHAR (250)    NOT NULL,

   PRIMARY KEY (id)
);



CREATE TABLE tema(
   id           BIGINT              NOT NULL auto_increment,
   descricao       VARCHAR (250)    NOT NULL,

   PRIMARY KEY (id)
);




CREATE TABLE bolo(
   id           BIGINT              NOT NULL auto_increment,
   nome        VARCHAR (250)    NOT NULL,
   peso       double             ,
   valor     double             ,
   cobertura_id  BIGINT               NOT NULL,
   massa_id  BIGINT              NOT NULL,
   tema_id  BIGINT             NOT NULL,
   FOREIGN KEY (cobertura_id) REFERENCES cobertura(id) on delete cascade,
   FOREIGN KEY (massa_id) REFERENCES massa(id) on delete cascade,
   FOREIGN KEY (tema_id) REFERENCES tema(id) on delete cascade ,
   PRIMARY KEY (id)
);




CREATE TABLE voto(
   id          BIGINT              NOT NULL auto_increment,
   qtd_voto     INT              ,
    bolo_id  BIGINT              NOT NULL,
   FOREIGN KEY (bolo_id) REFERENCES bolo(id),
   PRIMARY KEY (id)
);


CREATE TABLE encomenda(
   id          BIGINT            NOT NULL auto_increment,
   valor     double             ,
   entrega       date          NOT NULL,
   bolo_id  BIGINT              NOT NULL,
   FOREIGN KEY (bolo_id) REFERENCES bolo(id) on delete cascade,
   PRIMARY KEY (id)
);




DROP TABLE IF EXISTS `bolo_sabor`;

CREATE TABLE bolo_sabor(
   bolo_id         BIGINT              NOT NULL,
   sabor_id            BIGINT             NOT NULL,
   FOREIGN KEY (bolo_id) REFERENCES bolo(id) on delete cascade,
   FOREIGN KEY (sabor_id)  REFERENCES sabor(id) on delete cascade
  ,
   PRIMARY KEY ( bolo_id , sabor_id)
);
