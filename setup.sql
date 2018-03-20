DROP DATABASE IF EXISTS SETUP;
CREATE SCHEMA SETUP;
USE SETUP;

CREATE TABLE Treningsøkt(
TreningsøktID int NOT NULL,
Dato date,
Tidspunkt time,
Varighet int,
Form int,
Prestasjon int,
Notat varchar(100),
TreningssenterID int,
PRIMARY KEY(TreningsøktID)
);

CREATE TABLE Øvelsegruppe(
ØvelsegruppeID int NOT NULL,
Beskrivelse varchar(100),
PRIMARY KEY(ØvelsegruppeID)
);

CREATE TABLE Øvelse(
ØvelseID int NOT NULL,
Navn varchar(30),
ØvelsegruppeID int,
PRIMARY KEY(ØvelseID)
);

CREATE TABLE Fastmontert(
FastmontertID int UNIQUE PRIMARY KEY references Øvelse(ØvelseID)
	ON UPDATE CASCADE
    ON DELETE CASCADE,
ApparatID int
);

CREATE TABLE Fri(
FriID int UNIQUE PRIMARY KEY references Øvelse(ØvelseID)
	ON UPDATE CASCADE
    ON DELETE CASCADE,
TekstligBeskrivelse varchar(100)
);

CREATE TABLE Apparat(
ApparatID int NOT NULL,
Navn varchar(30),
Beskrivelse varchar(100),
PRIMARY KEY(ApparatID)
);

CREATE TABLE Treningssenter(
TreningssenterID int NOT NULL,
Navn varchar(30),
Rangering int,
Størrelse int,
Sted varchar(100),
PRIMARY KEY(TreningssenterID)
);

CREATE TABLE ØvelseITreningsøkt(
TreningsøktID int,
ØvelseID int,
Kilo int,
Repetisjoner int,
Sett int,
PRIMARY KEY(TreningsøktID, ØvelseID)
);

CREATE TABLE ApparatITreningssenter(
TreningssenterID int,
ApparatID int,
PRIMARY KEY(TreningssenterID, ApparatID)
);

ALTER TABLE Treningsøkt
	ADD FOREIGN KEY(TreningssenterID) REFERENCES Treningssenter(TreningssenterID)
		ON UPDATE CASCADE
        ON DELETE SET NULL;
        
ALTER TABLE Øvelse
	ADD FOREIGN KEY(ØvelsegruppeID) REFERENCES Øvelsegruppe(ØvelsegruppeID)
		ON UPDATE CASCADE
        ON DELETE CASCADE;
        
ALTER TABLE Fastmontert
	ADD FOREIGN KEY(ApparatID) REFERENCES Apparat(ApparatID)
		ON UPDATE CASCADE
        ON DELETE CASCADE;
        
ALTER TABLE ØvelseITreningsøkt
	ADD FOREIGN KEY(TreningsøktID) REFERENCES Treningsøkt(TreningsøktID)
		ON UPDATE CASCADE
        ON DELETE CASCADE,
	ADD FOREIGN KEY(ØvelseID) REFERENCES Øvelse(ØvelseID)
		ON UPDATE CASCADE
        ON DELETE CASCADE;
        
ALTER TABLE ApparatITreningssenter
	ADD FOREIGN KEY(TreningssenterID) REFERENCES Treningssenter(TreningssenterID)
		ON UPDATE CASCADE
        ON DELETE CASCADE,
	ADD FOREIGN KEY(ApparatID) REFERENCES Apparat(ApparatID)
		ON UPDATE CASCADE
        ON DELETE CASCADE;
        



INSERT INTO Treningssenter VALUES(1, "Games'n'Gains", 10, 250, "Moholt");
INSERT INTO Treningsøkt VALUES(1, null, null, 60, 5, 5, "God økt! Masse gains!", 1);
INSERT INTO ØvelseGruppe VALUES(1, "Bissa");

INSERT INTO Øvelse VALUES(1,"Bicepscurls",1);
INSERT INTO ØvelseITreningsøkt VALUES(1,1, 50, 12, 3);

INSERT INTO Apparat VALUES(1,"Seated Curlz", "Make your guns fire");

INSERT INTO Fastmontert VALUES(1,1);
INSERT INTO ApparatITreningssenter VALUES(1,1);


#Denne skal feile, og gjør det 
#INSERT INTO Fastmontert VALUES(1,10,3,1); 


SELECT Navn, Beskrivelse
FROM Apparat

        