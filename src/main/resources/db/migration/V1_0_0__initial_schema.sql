CREATE SCHEMA IF NOT EXISTS medickSchema;

CREATE TABLE if not exists Osoba (
    IDOsoba serial primary key  ,
    Ime varchar(255) NOT NULL ,
    Prezime varchar(255) NOT NULL ,
    Email varchar(255) NOT NULL ,
    Telefon varchar(255) NOT NULL ,
    AdresaStanovanja varchar(255) not null ,
    Lozinka varchar(255) not null

);


CREATE TABLE if not exists Pacijent (
     IDPacijent serial primary key  ,
     OsobaID int references Osoba(IDOsoba) not null

);


Create table if not exists Skrbnik (
     IDSkrbnik serial primary key,
     OsobaID int references Osoba(IDOsoba) not null
);

Create table if not exists SkrbnikPacijent(
  IDSkrbnikPacijent serial PRIMARY KEY,
  SkrbnikID int references Skrbnik(IDSkrbnik) not null,
  PacijentID int references Pacijent(IDPacijent) not null


);


