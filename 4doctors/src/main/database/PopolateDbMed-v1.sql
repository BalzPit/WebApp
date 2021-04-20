INSERT INTO doctors.Paziente VALUES ('MLNMTT96E16H816G','mattia','molinaro','email1','5f4dcc3b5aa765d61d8327deb882cf99','M','1996-05-16','San Daniele Del Fr.','Via zef,9,Forgaria Nel Friuli(UD),33030');
INSERT INTO doctors.Paziente VALUES ('VCNRRT96S13I775M','roberto', 'piva','email2','5f4dcc3b5aa765d61d8327deb882cf99','M', '1996-11-13', 'Soave', 'Via degli alpini, 29 (VR), 37030');
INSERT INTO doctors.Paziente VALUES ('MTTRSO97E67L781I','rosa', 'miotto','email3','5f4dcc3b5aa765d61d8327deb882cf99','F', '1998-07-23', 'Verona', 'Via degli boh, 33 (VR), 34876');
INSERT INTO doctors.Paziente VALUES ('GVNPVAG4RE44S3D9', 'giovanni','piva','email4','5f4dcc3b5aa765d61d8327deb882cf99', 'M', '1997-01-23' ,'Padova', 'Via Cesare Battisti, 124 (PD), 35121');
INSERT INTO doctors.Paziente VALUES ('BLLMCL94B14L736U','Marcello','Bullo','bullo.marcello@gmail.com','098f6bcd4621d373cade4e832627b4f6','M','2021-04-08','Padova','via Sografi');

INSERT INTO doctors.Medico VALUES ('SON01MED1C0G1UR0', 'andrea', 'rossi','email5','5f4dcc3b5aa765d61d8327deb882cf99', 'M', '1965-06-11', 'Venezia', 'A34VE', 'Via dei medici, 118 (VE), 34761');
INSERT INTO doctors.Medico VALUES ('F345MED1K0283759', 'giulia', 'bianchi','email6','5f4dcc3b5aa765d61d8327deb882cf99', 'F', '1979-04-06', 'Milano', 'A34VE', 'Via del corto, 143 (VE), 35676');

INSERT INTO doctors.Esenzione VALUES ('RED02', 'REDDITO');
INSERT INTO doctors.Esenzione VALUES ('CR01', 'MALATTIA CRONICA');

INSERT INTO doctors.Vaccino VALUES ('Antitetanica', 'obbligatorio, esavalente');
INSERT INTO doctors.Vaccino VALUES ('Meningite', 'non obbligatorio, monovalente');

INSERT INTO doctors.Patologia VALUES ('10A', 'polmonite','C', 'Patologia Cronica');
INSERT INTO doctors.Patologia VALUES ('23B', 'otite', 'A', null);
INSERT INTO doctors.Patologia VALUES ('53A', 'tonsillite', 'A', null);

INSERT INTO doctors.Farmaco VALUES ('1322AB0001', 'Tachipirina', 'OTC', 'Angelini', 'gocce');
INSERT INTO doctors.Farmaco VALUES ('234DF21345', 'Broncovaleas', 'SOP', 'Valeant', 'compresse 100mg');
INSERT INTO doctors.Farmaco VALUES ('JS7EH284HD', 'Acamba', 'ETICI', 'Sooft Italia S.p.a.', 'Antiglaucoma');
INSERT INTO doctors.Farmaco VALUES ('H2E7G45KN9', 'Cabaser', 'ETICI', 'Pfizer', 'Antiparkinson, inibitori della prolattina');
INSERT INTO doctors.Farmaco VALUES ('W5EGH729KF', 'Tachidol', 'ETICI', 'Angelini', 'Antipiretici, analgesici FANS');

INSERT INTO doctors.PrincipioAttivo VALUES ('AC14', 'Salbutamolo');
INSERT INTO doctors.PrincipioAttivo VALUES ('B4C1', 'Paracetamolo');

INSERT INTO doctors.Esame VALUES ('H01', 'Esame del sangue');
INSERT INTO doctors.Esame VALUES ('CB2', 'Radiografia ginocchio dx');
INSERT INTO doctors.Esame VALUES ('AC23', 'Gastroscopia');
insert into doctors.esame values ('END1', 'Laringoscopia');
insert into doctors.esame values ('END2', 'Broncoscopia');
insert into doctors.esame values ('END3', 'Esofagoscopia');
insert into doctors.esame values ('END4', 'Gastroscopia');
insert into doctors.esame values ('END5', 'Endroscopia gastroduodenale');
insert into doctors.esame values ('END6', 'Colonscopia');
insert into doctors.esame values ('END7', 'Citoscopia');
insert into doctors.esame values ('END8', 'Isteroscopia');
insert into doctors.esame values ('END9', 'Artroscopia');
insert into doctors.esame values ('END10', 'Laparoscopia');
insert into doctors.esame values ('END11', 'Mediastinoscopia');
insert into doctors.esame values ('END12', 'Torascopia');
insert into doctors.esame values ('ECG1', 'Elettrocardiografia');
insert into doctors.esame values ('EEG', 'Elettroencefalografia');
insert into doctors.esame values ('BPS1', 'Biopsia');
insert into doctors.esame values ('RADGF1', 'Radiografia');
insert into doctors.esame values ('ECOGF1', 'Ecografia');
insert into doctors.esame values ('SCIGF1', 'Scintigrafia');
insert into doctors.esame values ('TC1', 'Tomografia Computerizzata');
insert into doctors.esame values ('RMI1', 'Risonanza magnetica immagini');
insert into doctors.esame values ('PET1', 'Tomografia emissione positroni');
insert into doctors.esame values ('ANGGF1', 'Angiografia');


INSERT INTO doctors.Ricetta VALUES (176429611, 'F345MED1K0283759', 'MLNMTT96E16H816G', '2018-04-21', 'Gastroscopia per certificazione intolleranze', 1, '80', 'URG', 'APPROVED');
INSERT INTO doctors.Ricetta VALUES (283723789, 'SON01MED1C0G1UR0', 'MTTRSO97E67L781I', '2019-05-09', 'Tachipirina gocce', 3, '11', null, 'APPROVED');

INSERT INTO doctors.Terapia VALUES ('MLNMTT96E16H816G', '10A', '2018-06-07', null, '2 Areosol al giorno' );
INSERT INTO doctors.Terapia VALUES ('VCNRRT96S13I775M', '23B', '2019-02-12', '2019-02-28', '1 compressa Tachipirina 100mg');

INSERT INTO doctors.Visita VALUES ('SON01MED1C0G1UR0', 'MTTRSO97E67L781I', '2018-12-12', '17:00' , 'Probabile mononucleosi');
INSERT INTO doctors.Visita VALUES ('F345MED1K0283759', 'GVNPVAG4RE44S3D9', '2019-04-21', '16:30' , 'Pressione stabile');
INSERT INTO doctors.Visita VALUES ('F345MED1K0283759', 'MLNMTT96E16H816G', '2019-05-30', '16:30' , 'tosse');

INSERT INTO doctors.FarmaciPerTerapia VALUES ('MLNMTT96E16H816G', '10A', '2018-06-07', '234DF21345', '2', 'ogni 12 ore');
INSERT INTO doctors.FarmaciPerTerapia VALUES ('VCNRRT96S13I775M', '23B', '2019-02-12', '1322AB0001', '1', 'ogni 24 ore');

INSERT INTO doctors.FarmaciRicetta VALUES ('234DF21345', 176429611, 1 );

INSERT INTO doctors.Incompatibile VALUES ('AC14','B4C1');

INSERT INTO doctors.Familiari VALUES ('VCNRRT96S13I775M', 'GVNPVAG4RE44S3D9', 'figlio');

INSERT INTO doctors.EsenzionePatologia VALUES ('10A', 'CR01');

INSERT INTO doctors.Esente VALUES ('CR01', 'MLNMTT96E16H816G');

INSERT INTO doctors.RicettaEsame VALUES ('AC23', '283723789', '2018-04-21', 'intollerante al lattosio');

INSERT INTO doctors.Vaccinazione VALUES ('Antitetanica', 'GVNPVAG4RE44S3D9', '2005-12-30', '2018-12-30');
INSERT INTO doctors.Vaccinazione VALUES ('Meningite', 'MTTRSO97E67L781I', '2019-03-21', '2022-03-21');

--INSERT INTO doctors.FarmaciRicetta (farmaco, ricetta, qta) VALUES ('234DF21345', '6cbc9dcd-7929-47da-84c1-21eab53d3131', 1);
--INSERT INTO doctors.RicettaEsame VALUES ('AC23', 'abca3ff8-dea0-46ef-8292-2c3cd55c83e3', '2018-04-21', 'intollerante al lattosio');

INSERT INTO doctors.Segue VALUES ('SON01MED1C0G1UR0', 'GVNPVAG4RE44S3D9', '1');
INSERT INTO doctors.Segue VALUES ('F345MED1K0283759', 'VCNRRT96S13I775M', '1');

INSERT INTO doctors.admin VALUES ('nico9779', 'Nicola', 'Levorato', 'nicola.levorato.2@studenti.unipd.it', md5('password'));