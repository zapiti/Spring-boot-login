CREATE TABLE image(
   id          INT              NOT NULL auto_increment,
   name        VARCHAR (250)    NOT NULL,
   path        VARCHAR (2500)    NOT NULL,
    bolo_id  BIGINT        ,
   FOREIGN KEY (bolo_id) REFERENCES bolo(id),
   PRIMARY KEY (id)
);


