INSERT INTO doctors.Paziente VALUES ('MLNMTT96E16H816G','mattia','molinaro','mattia.molinaro@gmail.com',MD5('password'),'M','1996-05-16','San Daniele Del Fr.','Via zef,9,Forgaria Nel Friuli(UD),33030');
INSERT INTO doctors.Paziente VALUES ('VCNRRT96S13I775M','roberto', 'piva','roberto.piva@hotmail.com',MD5('123456789'), 'M', '1996-11-13', 'Soave', 'Via degli alpini, 29 (VR), 37030');
INSERT INTO doctors.Paziente VALUES ('MTTRSO97E67L781I','rosa', 'miotto','rosa.miotto@yahoo.it',MD5('ciaociao'), 'F', '1998-07-23', 'Verona', 'Via degli boh, 33 (VR), 34876');
INSERT INTO doctors.Paziente VALUES ('GVNPVAG4RE44S3D9', 'giovanni', 'piva','giovanni.piva@gmail.com',MD5('omegalul'), 'M', '1997-01-23' ,'Padova', 'Via Cesare Battisti, 124 (PD), 35121');
INSERT INTO doctors.Paziente VALUES ('SCCWLM97P23A001U', 'wiliam', 'scicchitano','wiliam.scicchitano@gmail.com', MD5('omegalul'), 'M', '1997-09-23' ,'Abano Terme', 'Via Trespole, 124 (PD), 35121');

INSERT INTO doctors.Medico VALUES ('SON01MED1C0G1UR0', 'andrea', 'rossi','andrea.rossi@gmail.com',MD5('password'), 'M', '1965-06-11', 'Venezia', 'A34VE', 'Via dei medici, 118 (VE), 34761');
INSERT INTO doctors.Medico VALUES ('F345MED1K0283759', 'giulia', 'bianchi','giulia.bianchi@gmail.com',MD5('123456789'), 'F', '1979-04-06', 'Milano', 'A34VE', 'Via del corto, 143 (VE), 35676');

INSERT INTO doctors.Esenzione VALUES ('RED02', 'REDDITO');
INSERT INTO doctors.Esenzione VALUES ('CR01', 'MALATTIA CRONICA');

INSERT INTO doctors.Vaccino VALUES ('Antitetanica', 'obbligatorio, esavalente');
INSERT INTO doctors.Vaccino VALUES ('Meningite', 'non obbligatorio, monovalente');

INSERT INTO doctors.Patologia VALUES ('10A', 'polmonite','C', 'Patologia Cronica');
INSERT INTO doctors.Patologia VALUES ('23B', 'otite', 'A', null);
INSERT INTO doctors.Patologia VALUES ('53A', 'tonsillite', 'A', null);

INSERT INTO doctors.Farmaco VALUES ('1322AB0001', 'Tachipirina', 'OTC', 'Angelini', 'gocce');
INSERT INTO doctors.Farmaco VALUES ('234DF21345', 'Broncovaleas', 'SOP', 'Valeant', 'compresse 100mg');

INSERT INTO doctors.PrincipioAttivo VALUES ('AC14', 'Salbutamolo');
INSERT INTO doctors.PrincipioAttivo VALUES ('B4C1', 'Paracetamolo');

INSERT INTO doctors.Esame VALUES ('H01', 'Esame del sangue');
INSERT INTO doctors.Esame VALUES ('CB2', 'Radiografia ginocchio dx');
INSERT INTO doctors.Esame VALUES ('AC23', 'Gastroscopia');

INSERT INTO doctors.Ricetta (medico, paziente, data, descrizione, numeroprestazioni, tipo, status) VALUES ('F345MED1K0283759', 'MLNMTT96E16H816G', '2018-04-21', 'Gastroscopia per certificazione intolleranze', 1, 'ESAME', 'PENDING');
INSERT INTO doctors.Ricetta (medico, paziente, data, descrizione, numeroprestazioni, tipo, status) VALUES ('SON01MED1C0G1UR0', 'MTTRSO97E67L781I', '2019-05-09', 'Tachipirina gocce', 3, 'FARMACO', 'PENDING');
INSERT INTO doctors.Ricetta (medico, paziente, data, descrizione, numeroprestazioni, tipo, status) VALUES ('SON01MED1C0G1UR0', 'VCNRRT96S13I775M', '2021-04-07', 'Okitask', 1, 'FARMACO', 'PENDING');
INSERT INTO doctors.Ricetta (medico, paziente, data, descrizione, numeroprestazioni, tipo, status) VALUES ('F345MED1K0283759', 'MTTRSO97E67L781I', '2021-04-06', 'Okitask', 1, 'FARMACO', 'REJECTED');
INSERT INTO doctors.Ricetta (medico, paziente, data, descrizione, numeroprestazioni, tipo, status) VALUES ('F345MED1K0283759', 'MTTRSO97E67L781I', '2021-03-12', 'Tachifludec', 1, 'FARMACO', 'APPROVED');
INSERT INTO doctors.Ricetta (medico, paziente, data, descrizione, numeroprestazioni, tipo, status) VALUES ('F345MED1K0283759', 'SCCWLM97P23A001U', '2021-01-07', 'Tachifludec', 1, 'FARMACO', 'PENDING');
INSERT INTO doctors.Ricetta (medico, paziente, data, descrizione, numeroprestazioni, tipo, status) VALUES ('SON01MED1C0G1UR0', 'SCCWLM97P23A001U', '2021-02-25', 'Stiproxal', 5, 'FARMACO', 'PENDING');
INSERT INTO doctors.Ricetta (medico, paziente, data, descrizione, numeroprestazioni, tipo, status) VALUES ('SON01MED1C0G1UR0', 'MLNMTT96E16H816G', '2021-03-18', 'Aftamed', 1, 'FARMACO', 'APPROVED');

INSERT INTO doctors.Terapia VALUES ('MLNMTT96E16H816G', '10A', '2018-06-07', null, '2 Areosol al giorno' );
INSERT INTO doctors.Terapia VALUES ('VCNRRT96S13I775M', '23B', '2019-02-12', '2019-02-28', '1 compressa Tachipirina 100mg');

INSERT INTO doctors.Visita VALUES ('SON01MED1C0G1UR0', 'MTTRSO97E67L781I', '2018-12-12', '17:10' , 'Probabile mononucleosi');
INSERT INTO doctors.Visita VALUES ('F345MED1K0283759', 'GVNPVAG4RE44S3D9', '2019-04-21', '16:30' , 'Pressione stabile');
INSERT INTO doctors.Visita VALUES ('F345MED1K0283759', 'MLNMTT96E16H816G', '2019-05-30', '16:30' , 'tosse');

INSERT INTO doctors.FarmaciPerTerapia VALUES ('MLNMTT96E16H816G', '10A', '2018-06-07', '234DF21345', '2', 'ogni 12 ore');
INSERT INTO doctors.FarmaciPerTerapia VALUES ('VCNRRT96S13I775M', '23B', '2019-02-12', '1322AB0001', '1', 'ogni 24 ore');

INSERT INTO doctors.FarmaciRicetta (farmaco, qta) VALUES ('234DF21345', 1);

INSERT INTO doctors.Incompatibile VALUES ('AC14','B4C1');

INSERT INTO doctors.Familiari VALUES ('VCNRRT96S13I775M', 'GVNPVAG4RE44S3D9', 'figlio');

INSERT INTO doctors.EsenzionePatologia VALUES ('10A', 'CR01');

INSERT INTO doctors.Esente VALUES ('CR01', 'MLNMTT96E16H816G');

INSERT INTO doctors.RicettaEsame VALUES ('AC23', '283723789', '2018-04-21', 'intollerante al lattosio');

INSERT INTO doctors.Vaccinazione VALUES ('Antitetanica', 'GVNPVAG4RE44S3D9', '2005-12-30', '2018-12-30');
INSERT INTO doctors.Vaccinazione VALUES ('Meningite', 'MTTRSO97E67L781I', '2019-03-21', '2022-03-21');