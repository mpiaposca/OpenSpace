DROP SCHEMA ordini;
CREATE SCHEMA ordini;
USE ordini;

CREATE TABLE utente (
	id_utente INTEGER AUTO_INCREMENT PRIMARY KEY,
	nome_utente VARCHAR(50),
	cognome_utente VARCHAR(50),
	email VARCHAR(90)
);

CREATE TABLE prodotto (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	nome VARCHAR(50),
	barcode VARCHAR(70),
    quantita FLOAT,
    prezzo FLOAT,
	version LONG
);

CREATE TABLE acquisto (
	id_acquisto INTEGER AUTO_INCREMENT PRIMARY KEY,
	utente INTEGER,
	data DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (utente) REFERENCES utente(id)
);

CREATE TABLE prodotto_in_acquisto (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
	acquisto INTEGER,
	prodotto INTEGER,
    quantita INTEGER,
    FOREIGN KEY (acquisto) REFERENCES acquisto(id),
    FOREIGN KEY (prodotto) REFERENCES prodotto(id)
);


