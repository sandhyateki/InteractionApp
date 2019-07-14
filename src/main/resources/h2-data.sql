INSERT INTO APPLICATION VALUES('Application1', 'Test Application to run certain features in the vehicle','Y', 'Y', 'N', 'Y','Generically Signed', 'Y', 'Y', 'Y', 'Y', 'Y');
INSERT INTO APPLICATION VALUES('Application2', 'Application to modulate ambient light','N', 'N', 'Y','Y', 'Generically Signed',  'Y', 'Y', 'Y', 'Y', 'N');

INSERT INTO FUNCTIONALTYPEVALUES VALUES ('Application1', 'CUSTOM APPLICATION');
INSERT INTO FUNCTIONALTYPEVALUES VALUES ('Application2', 'LANGUAGE PACK');

insert into COMPATIBLEASSEMBLIES values (1,'SA-12-AAA','Application1');
insert into COMPATIBLEASSEMBLIES values (2,'SA-12-AAB','Application1');
insert into COMPATIBLEASSEMBLIES values (3,'SA-12-AAC','Application1');
insert into COMPATIBLEASSEMBLIES values (4,'SA-12-FAA','Application1');
insert into COMPATIBLEASSEMBLIES values (5,'SA-12-FAB','Application1');
insert into COMPATIBLEASSEMBLIES values (6,'SA-12-FAC','Application1');

insert into COMPATIBLEASSEMBLIES values (7,'VA-17-OP','Application2' );
insert into COMPATIBLEASSEMBLIES values (8,'VA-17-QP','Application2');
insert into VALUES_TABLE values (1,'Application1', 'CS');
insert into VALUES_TABLE values (2,'Application1', 'DA');
insert into VALUES_TABLE values (3,'Application1', 'DE');
insert into VALUES_TABLE values (4,'Application1', 'FR-FR');
insert into VALUES_TABLE values (5,'Application1', 'PL');
insert into VALUES_TABLE values (6,'Application1', 'PT-PT');
insert into VALUES_TABLE values (7,'Application1', 'RU');
insert into VALUES_TABLE values (8,'Application1', 'SV');
insert into VALUES_TABLE values (9,'Application1', 'TR');
insert into VALUES_TABLE values (10,'Application2','CS');
insert into VALUES_TABLE values (11,'Application2','DA');
insert into VALUES_TABLE values (12,'Application2','EN-GB');
insert into VALUES_TABLE values (13,'Application2','NL');
insert into VALUES_TABLE values (14,'Application2','NO');
insert into VALUES_TABLE values (15,'Application2','PL');
insert into VALUES_TABLE values (16,'Application2','SV');
insert into VALUES_TABLE values (17,'Application2','TR');

insert into MGMFILE values('SA-11-TT',  'TAR.GZ',  '0xC7C1DA2F', '0x61192B821A9EA47D72DD360175C7A8EE', '1664508640', '1664508640', '2016-03-21 21:20:40');
insert into MGMFILE values('VA-16-AA',  'TAR.GZ',  '0xC7C1DA2F', '0x61192B821A9EA47D72DD360175C7A8EE', '1664508640', '1664508640', '2016-03-21 21:20:40');


INSERT INTO APPLICATIONPARTDETAIL VALUES('Application1','SA-11-TT', 'True', 'SA-11-TS', '1.0','1664508640');
INSERT INTO APPLICATIONPARTDETAIL VALUES('Application2','VA-16-AA', 'True', 'VA-16-AB', '2.0','1664508640');

insert into PROTOCOL values('SA-11-TT', 'OTA', 'YES');
insert into PROTOCOL values('SA-11-TT', 'USB', 'NO');
insert into PROTOCOL values('VA-16-AA', 'USB', 'NO');

