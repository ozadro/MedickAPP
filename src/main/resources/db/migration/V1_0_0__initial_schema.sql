CREATE SCHEMA IF NOT EXISTS medickschema;

CREATE TABLE if not exists osoba (
    idosoba serial primary key  ,
    ime varchar(255) NOT NULL ,
    prezime varchar(255) NOT NULL ,
    email varchar(255) NOT NULL ,
    telefon varchar(255) NOT NULL ,
    adresastanovanja varchar(255) not null ,
    lozinka varchar(255) not null

);


CREATE TABLE if not exists pacijent (
     idpacijent serial primary key  ,
     osobaid int references osoba(idosoba) not null

);


Create table if not exists skrbnik (
     idskrbnik serial primary key,
     osobaid int references osoba(idosoba) not null
);

Create table if not exists skrbnikpacijent(
  idskrbnikpacijent serial PRIMARY KEY,
  skrbnikid int references skrbnik(idskrbnik) not null,
  pacijentid int references pacijent(idpacijent) not null


);


