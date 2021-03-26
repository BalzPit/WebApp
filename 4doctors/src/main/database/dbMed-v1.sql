--4Doctors database SQL schema ---

DROP SCHEMA IF EXISTS Doctors CASCADE;
create schema Doctors;

DROP TYPE IF EXISTS GENDER CASCADE;
DROP TYPE IF EXISTS TIPOPATOLOGIA CASCADE;
DROP TYPE IF EXISTS TIPOFARMACO CASCADE;

CREATE TYPE GENDER AS  ENUM ('M', 'F');
CREATE TYPE TIPOPATOLOGIA AS  ENUM ('A', 'B', 'C');
CREATE TYPE TIPOFARMACO AS  ENUM ('ETICI', 'OTC', 'SOP');

-------------------------

CREATE TABLE doctors.Paziente (
  cf VARCHAR(16) PRIMARY KEY,
  nome VARCHAR(30) NOT NULL,
  cognome VARCHAR(30) NOT NULL,
  sesso GENDER NOT NULL,
  datanascita DATE NOT NULL,
  luogonascita VARCHAR(50) NOT NULL, --- nome della città (sigla provincia/nazione estera)
  indirizzoresidenza VARCHAR(50), --- via/piazza , nr.civ, Città (PROV.), cap
  allergie VARCHAR(200), 
  intolleranze VARCHAR(200)
);

CREATE TABLE doctors.Medico (
  cf VARCHAR(16) PRIMARY KEY,
  nome VARCHAR(30) NOT NULL,
  cognome VARCHAR(30) NOT NULL,
  sesso GENDER NOT NULL,
  datanascita DATE NOT NULL,
  luogonascita VARCHAR(50) NOT NULL, --- nome della città (sigla provincia/nazione estera)
  CodiceASL VARCHAR(30) NOT NULL, --- nome della città (sigla provincia)
  indirizzoresidenza VARCHAR(50) --- via/piazza , nr.civ, Città(PROV.), cap
);

CREATE TABLE doctors.Vaccino (
  nome VARCHAR(30) PRIMARY KEY,
  tipo VARCHAR(50) NOT NULL
);

CREATE TABLE doctors.Pressione (
  paziente VARCHAR(16) NOT NULL,
  data DATE NOT NULL,
  ora TIME NOT NULL,
  sistolica INT NOT NULL,
  diastolica INT NOT NULL,
  PRIMARY KEY (paziente, data, ora),
  FOREIGN KEY (paziente) REFERENCES doctors.Paziente(cf) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE doctors.Esenzione (
  codice VARCHAR(16) PRIMARY KEY,
  tipo VARCHAR(30) NOT NULL
);

CREATE TABLE doctors.Patologia (
  codice VARCHAR(16) PRIMARY KEY,
  nome VARCHAR(30) NOT NULL,
  tipo TIPOPATOLOGIA NOT NULL,
  gruppo VARCHAR(30)
);

CREATE TABLE doctors.Farmaco (
  codice VARCHAR(16) PRIMARY KEY,
  nome VARCHAR(30) NOT NULL,
  classe TIPOFARMACO NOT NULL,
  azienda VARCHAR(30),
  descrizione VARCHAR(200) NOT NULL
);

CREATE TABLE doctors.PrincipioAttivo (
  codice VARCHAR(16) PRIMARY KEY,
  nome VARCHAR(30) NOT NULL
);

CREATE TABLE doctors.Esame (
  codice VARCHAR(16) PRIMARY KEY,
  nome VARCHAR(30)
);

CREATE TABLE doctors.Ricetta (
  id INT PRIMARY KEY,
  medico VARCHAR(16) NOT NULL,
  paziente VARCHAR(16) NOT NULL,
  data DATE NOT NULL,
  descrizione VARCHAR(200) NOT NULL,
  numeroprestazioni INT,
  tipo VARCHAR(2),
  priorità VARCHAR(3),
  FOREIGN KEY (medico) REFERENCES doctors.Medico(cf) ON DELETE CASCADE ON UPDATE CASCADE ,
  FOREIGN KEY (paziente) REFERENCES doctors.Paziente(cf) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE doctors.Terapia (
  paziente VARCHAR(16) NOT NULL,
  patologia VARCHAR(16) NOT NULL,
  datainizio DATE NOT NULL,
  datafine DATE,
  note VARCHAR(200),
  PRIMARY KEY (patologia, paziente, datainizio),
  FOREIGN KEY (patologia) REFERENCES doctors.Patologia(codice) ON DELETE CASCADE ON UPDATE CASCADE ,
  FOREIGN KEY (paziente) REFERENCES doctors.Paziente(cf) ON DELETE CASCADE ON UPDATE CASCADE
);

---R

CREATE TABLE doctors.Visita (
  medico VARCHAR(16) NOT NULL,
  paziente VARCHAR(16) NOT NULL,
  data DATE NOT NULL,
  ora TIME NOT NULL,
  esito VARCHAR(200),
  PRIMARY KEY (medico, paziente, data, ora),
  FOREIGN KEY (medico) REFERENCES doctors.Medico(cf) ON DELETE CASCADE ON UPDATE CASCADE ,
  FOREIGN KEY (paziente) REFERENCES doctors.Paziente(cf) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE doctors.PAFarmaco (
  farmaco VARCHAR(16) NOT NULL,
  patt VARCHAR(16) NOT NULL,
  PRIMARY KEY(farmaco,patt),
  FOREIGN KEY (farmaco) REFERENCES doctors.Farmaco(codice) ON DELETE CASCADE ON UPDATE CASCADE ,
  FOREIGN KEY (patt) REFERENCES doctors.PrincipioAttivo(codice) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE doctors.FarmaciPerTerapia(
  paziente VARCHAR(16) NOT NULL,
  patologia VARCHAR(16) NOT NULL,
  inizio DATE NOT NULL,
  farmaco VARCHAR(16) NOT NULL,
  dose VARCHAR(16),
  freq VARCHAR(16),
  PRIMARY KEY(paziente,patologia,inizio,farmaco),
  FOREIGN KEY (paziente, patologia, inizio) REFERENCES doctors.Terapia(paziente, patologia, datainizio) ON DELETE CASCADE ON UPDATE CASCADE ,
  FOREIGN KEY (farmaco) REFERENCES doctors.Farmaco(codice) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE doctors.FarmaciRicetta(
  farmaco VARCHAR(16) NOT NULL,
  ricetta INT NOT NULL,
  qta INT NOT NULL,
  PRIMARY KEY (farmaco,ricetta),
  FOREIGN KEY (farmaco) REFERENCES doctors.Farmaco(codice) ON DELETE CASCADE ON UPDATE CASCADE ,
  FOREIGN KEY (ricetta) REFERENCES doctors.Ricetta(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE doctors.Incompatibile(
  patt1 VARCHAR(16) NOT NULL,
  patt2 VARCHAR(16) NOT NULL,
  PRIMARY KEY(patt1,patt2),
  FOREIGN KEY (patt1) REFERENCES doctors.PrincipioAttivo(codice) ON DELETE CASCADE ON UPDATE CASCADE ,
  FOREIGN KEY (patt2) REFERENCES doctors.PrincipioAttivo(codice) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE doctors.Familiari(
  paziente1 VARCHAR(16) NOT NULL,
  paziente2 VARCHAR(16) NOT NULL,
  grado VARCHAR(20),
  PRIMARY KEY (paziente1, paziente2),
  FOREIGN KEY (paziente1) REFERENCES doctors.Paziente(cf) ON DELETE CASCADE ON UPDATE CASCADE ,
  FOREIGN KEY (paziente2) REFERENCES doctors.Paziente(cf) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE doctors.EsenzionePatologia(
  patologia VARCHAR(16) NOT NULL,
  esenzione VARCHAR(16) NOT NULL,
  PRIMARY KEY (patologia, esenzione),
  FOREIGN KEY (patologia) REFERENCES doctors.Patologia(codice) ON DELETE CASCADE ON UPDATE CASCADE ,
  FOREIGN KEY (esenzione) REFERENCES doctors.Esenzione(codice) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE doctors.Esente(
  esenzione VARCHAR(16) NOT NULL,
  paziente VARCHAR(16) NOT NULL,
  PRIMARY KEY (esenzione, paziente),
  FOREIGN KEY (paziente) REFERENCES doctors.Paziente(cf) ON DELETE CASCADE ON UPDATE CASCADE ,
  FOREIGN KEY (esenzione) REFERENCES doctors.Esenzione(codice) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE doctors.RicettaEsame(
  esame VARCHAR(16) NOT NULL,
  ricetta INT NOT NULL,
  data DATE,
  esito VARCHAR(300),
  PRIMARY KEY (ricetta, esame),
  FOREIGN KEY (ricetta) REFERENCES doctors.Ricetta(id) ON DELETE CASCADE ON UPDATE CASCADE ,
  FOREIGN KEY (esame) REFERENCES doctors.Esame(codice) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE doctors.Vaccinazione(
  vaccino  VARCHAR(30) NOT NULL,
  paziente VARCHAR(16) NOT NULL,
  data DATE NOT NULL,
  dcadenza DATE,
  PRIMARY KEY (paziente, vaccino, data),
  FOREIGN KEY (paziente) REFERENCES doctors.Paziente(cf) ON DELETE CASCADE ON UPDATE CASCADE ,
  FOREIGN KEY (vaccino) REFERENCES doctors.Vaccino(nome) ON DELETE CASCADE ON UPDATE CASCADE
);