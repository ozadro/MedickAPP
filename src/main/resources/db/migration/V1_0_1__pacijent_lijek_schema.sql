CREATE TABLE if not exists lijek (
    idlijek serial primary key  ,
    naziv varchar(255) NOT NULL ,
    proizvodac varchar(255) NOT NULL
);

CREATE TABLE if not exists pacijentlijek (
     idpacijentlijek serial primary key ,
     lijekid int references lijek(idlijek) not null,
     pacijentid int references pacijent(idpacijent) not null,
     doza varchar(255) NOT NULL ,
     uzet varchar(255) not null

);

CREATE TABLE if not exists vitali (
     idvitali serial primary key ,
     glukozaukrvi varchar(255) NOT NULL ,
     krvnitlak varchar(255) NOT NULL ,
     datummjerenja date not null,
     pacijentid int references pacijent(idpacijent) not null

);
