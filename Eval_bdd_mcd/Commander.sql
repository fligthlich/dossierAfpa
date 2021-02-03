DROP DATABASE IF EXISTS commander;

CREATE DATABASE commander; 

USE commander;

CREATE TABLE Client(
   num_cli INT Auto_increment,
   nom_cli VARCHAR(30) NOT NULL,
   prenom_cli VARCHAR(30) NOT NULL,
   PRIMARY KEY(num_cli)
);

CREATE TABLE Commande(
   num_com INT,
   date_com DATE NOT NULL,
   MontantCommande DECIMAL(5,2) NOT NULL,
   num_cli INT NOT NULL,
   PRIMARY KEY(num_com),
   FOREIGN KEY(num_cli) REFERENCES Client(num_cli)
);

CREATE TABLE Article(
   num_art INT Auto_increment,
   designation_art VARCHAR(50),
   prix_unitaire_art INT NOT NULL,
   PRIMARY KEY(num_art)
);

CREATE TABLE SeComposeDe(
   num_com INT,
   num_art INT,
   qt_article INT NOT NULL,
   TauxTva DECIMAL(2,2) NOT NULL,
   PRIMARY KEY(num_com, num_art),
   FOREIGN KEY(num_com) REFERENCES Commande(num_com),
   FOREIGN KEY(num_art) REFERENCES Article(num_art)
);