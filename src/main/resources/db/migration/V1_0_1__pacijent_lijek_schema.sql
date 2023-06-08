CREATE TABLE if not exists lijek (
    idlijek serial primary key  ,
    naziv varchar(255) NOT NULL ,
    proizvodac varchar(255) NULL
);

CREATE TABLE if not exists vitali (
      idvitali serial primary key ,
      glukozaukrvi varchar(255) NOT NULL ,
      krvnitlak varchar(255) NOT NULL ,
      datummjerenja date not null,
      pacijentid int references pacijent(idpacijent) not null

);

CREATE TABLE if not exists terapija (
     idterapija serial primary key ,
     lijekid int references lijek(idlijek) not null,
     pacijentid int references pacijent(idpacijent) not null,
     vitaliid int references vitali (idvitali) null,
     dozalijeka varchar(255) NOT NULL,
     ponavljanja float not null,
     prvadoza date NOT NULL,
     kolicinatableta int not null,
     kolicinadnevno int not null
);


create table if not exists podsjetnik(
    idpodsjetnik serial primary key,
    terapijaid int references terapija(idterapija) not null,
    uzet boolean not null
);


