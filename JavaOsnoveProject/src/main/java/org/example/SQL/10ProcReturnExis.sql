--10 CreateIndexDropStatisticsIncludeFromJoinWhereDbccBeginEndTransactionPrintRollbackSaveTran

CREATE NONCLUSTERED INDEX IX_Datum_Izdavanja
ON Racun(DatumIzdavanja);  -- Kreira se neklasterirani indeks na stupcu DatumIzdavanja radi bržeg filtriranja po datumu

GO
DROP INDEX IX_Datum_Izdavanja ON Racun  -- Uklanja se prethodno kreirani indeks s tablice Racun

GO         
SET STATISTICS IO ON;   -- Uključuje se praćenje IO statistike (broj pročitanih stranica itd.)
GO  
SELECT DatumIzdavanja FROM Racun WHERE DatumIzdavanja BETWEEN '20010702' AND '20010702 23:59:59'  
-- Izvršava se SELECT upit s filtriranjem po datumu za provjeru koliko IO operacija se izvršava
GO  
SET STATISTICS IO OFF;  -- Isključuje se prikaz IO statistike
GO 

--uključivanje ostalih stupaca
CREATE NONCLUSTERED INDEX IX_Datum_Izdavanja
ON Racun(DatumIzdavanja)
INCLUDE (BrojRacuna)  -- Kreira se indeks koji uključuje dodatni stupac BrojRacuna (korisno za "covering" upite)
GO

SELECT 
    o.name AS TableName,              -- Ime tablice
    i.name AS IndexName,              -- Ime indeksa
    i.index_id,                       -- ID indeksa
    ps.index_type_desc,              -- Opis vrste indeksa
    ps.page_count                    -- Broj stranica koje indeks koristi
FROM sys.dm_db_index_physical_stats(DB_ID(), OBJECT_ID('dbo.Racun'), NULL, NULL, 'DETAILED') AS ps
JOIN sys.indexes AS i 
    ON ps.object_id = i.object_id AND ps.index_id = i.index_id  -- Spajanje s tablicom indeksa
JOIN sys.objects AS o 
    ON i.object_id = o.object_id  -- Spajanje s tablicom objekata
WHERE o.type = 'U';  -- Filtriranje samo korisničkih tablica

DBCC IND('AdventureWorksOBP', 'Racun', -1);  
-- Pokazuje informacije o internom rasporedu stranica za tablicu Racun u bazi AdventureWorksOBP

/*
Optimizirajte upit: SELECT DatumIzdavanja FROM Racun WHERE DatumIzdavanja BETWEEN '20010702' AND '20010702 23:59:59' 
* Koliko stranica je pregledao RDBMS?
* Napravite indeks koji optimizira upit
* Koliko sad stranica pregled RDBMS?
* Uklonite indeks
*/
CREATE NONCLUSTERED INDEX IX_Datum_Izdavanja
ON Racun(DatumIzdavanja);  -- Ponovno kreiranje indeksa za optimizaciju SELECT upita

GO
DROP INDEX IX_Datum_Izdavanja ON Racun  -- Uklanjanje indeksa nakon testiranja
GO         
SET STATISTICS IO ON;  -- Uključivanje IO statistike
GO  
SELECT DatumIzdavanja FROM Racun WHERE DatumIzdavanja BETWEEN '20010702' AND '20010702 23:59:59'  
-- Ponovno izvođenje SELECT upita za usporedbu IO performansi s/bez indeksa
GO  
SET STATISTICS IO OFF;  -- Isključivanje IO statistike
GO 

SELECT IDRacun, DatumIzdavanja FROM Racun WHERE DatumIzdavanja BETWEEN '20010702' AND '20010702 23:59:59'  
-- Izvodi se upit koji uz DatumIzdavanja dohvaća i IDRacun

--Transakcije
--10 zadataka: 

--1.  Napravite tablicu Osoba. Pokrenite transakciju i umetnite 3 zapisa u Osoba. 
--    Probajte dohvatiti podatke iz tablice. 
--    Odustanite od transakcije. 
--    Probajte dohvatiti podatke iz tablice.

GO
CREATE TABLE Osoba (
IDOsoba INT PRIMARY KEY IDENTITY,   -- Primarni ključ s automatskim inkrementiranjem
Ime NVARCHAR(15),                   -- Ime osobe
Prezime NVARCHAR(35)                -- Prezime osobe
);

GO
BEGIN TRANSACTION  -- Početak transakcije
INSERT INTO Osoba (Ime, Prezime) VALUES ('Marta', 'Mirić')  -- Umetanje prve osobe
INSERT INTO Osoba (Ime, Prezime) VALUES ('Iva', 'Ivić')     -- Umetanje druge osobe
INSERT INTO Osoba (Ime, Prezime) VALUES ('Boris', 'Bilić')  -- Umetanje treće osobe

PRINT '-- Ispis osoba nakon unosa u tablicu  unutar transakcije--'
SELECT * FROM Osoba  -- Prikaz sadržaja tablice unutar transakcije (još nije potvrđena)

ROLLBACK  -- Poništavanje svih promjena unutar transakcije

PRINT '-- Ispis osoba nakon odustajanja --'
SELECT * FROM Osoba  -- Ponovno dohvaćanje – tablica bi trebala biti prazna jer je ROLLBACK izvršen

--2. Riješite zadatak 1, ali umjesto odustajanja potvrdite transakciju.
GO
BEGIN TRANSACTION  -- Početak nove transakcije
INSERT INTO Osoba (Ime, Prezime) VALUES ('Marta', 'Mirić')  -- Umetanje osobe
INSERT INTO Osoba (Ime, Prezime) VALUES ('Iva', 'Ivić')     -- Umetanje osobe
INSERT INTO Osoba (Ime, Prezime) VALUES ('Boris', 'Bilić')  -- Umetanje osobe
COMMIT  -- Potvrda transakcije – podaci ostaju u tablici


/*3. U transakciji umetnite 1 zapis u Osoba i postavite kontrolnu točku. 
Umetnite još 1 zapis. Na kraju odustanite od transakcije. Što je u tablici?*/
GO
BEGIN TRANSACTION  -- Početak transakcije
INSERT INTO Osoba (Ime, Prezime) VALUES ('Barica', 'Anić')  -- Prvi unos
SAVE TRAN KontrolnaTocka  -- Postavljanje kontrolne točke na ovo mjesto
PRINT @@TRANCOUNT  -- Ispis broja aktivnih transakcija (očekuje se 1)
INSERT INTO Osoba (Ime, Prezime) VALUES ('Marko', 'Anić')  -- Drugi unos nakon kontrolne točke
ROLLBACK TRANSACTION  -- Poništava se cijela transakcija, uključujući sve prije i poslije točke
PRINT @@TRANCOUNT  -- Provjera da više nema aktivnih transakcija

/*4. U transakciji umetnite 1 zapis u Osoba i postavite kontrolnu točku. 
Umetnite još 1 zapis. Na kraju potvrdite transakciju. Što je u tablici?
*/
GO
BEGIN TRANSACTION  -- Početak nove transakcije
INSERT INTO Osoba (Ime, Prezime) VALUES ('Barica', 'Anić')  -- Prvi unos
SAVE TRAN KontrolnaTocka  -- Postavljanje kontrolne točke
INSERT INTO Osoba (Ime, Prezime) VALUES ('Marko', 'Anić')  -- Drugi unos
COMMIT  -- Potvrda transakcije – oba unosa ostaju u tablici

SELECT * FROM Osoba  -- Prikaz svih zapisa iz tablice Osoba




/*
Domaci rad 10
5. U transakciji umetnite 1 zapis u Osoba i postavite kontrolnu točku. Umetnite još 1 zapis i postavite kontrolnu točku. Na kraju odustanite od transakcije.
6. U transakciji umetnite 1 zapis u Osoba i postavite kontrolnu točku. Umetnite još 1 zapis i postavite kontrolnu točku. Na kraju potvrdite transakciju.
7. U transakciji umetnite 1 zapis u Osoba i postavite kontrolnu točku. Umetnite još 1 zapis i vratite se na kontrolnu točku. Na kraju odustanite od transakcije.
8. U transakciji umetnite 1 zapis u Osoba i postavite kontrolnu točku. Umetnite još 1 zapis i vratite se na kontrolnu točku. Na kraju potvrdite transakciju.
*/

--5. U transakciji umetnite 1 zapis u Osoba i postavite kontrolnu točku. 
--   Umetnite još 1 zapis i postavite kontrolnu točku. Na kraju odustanite od transakcije.

GO
BEGIN TRANSACTION  -- Početak transakcije
INSERT INTO Osoba (Ime, Prezime) VALUES ('Ana', 'Kovač')  -- Prvi unos
SAVE TRAN KontrolnaTocka1  -- Postavlja se prva kontrolna točka nakon prvog unosa

INSERT INTO Osoba (Ime, Prezime) VALUES ('Ivan', 'Kovač')  -- Drugi unos
SAVE TRAN KontrolnaTocka2  -- Postavlja se druga kontrolna točka nakon drugog unosa

ROLLBACK TRANSACTION  -- Otkazuje se cijela transakcija, podaci se ne spremaju u bazu
SELECT * FROM Osoba  -- Provjera: zapisi 'Ana' i 'Ivan' NEĆE biti prisutni

--6. U transakciji umetnite 1 zapis u Osoba i postavite kontrolnu točku. 
--   Umetnite još 1 zapis i postavite kontrolnu točku. Na kraju potvrdite transakciju.

GO
BEGIN TRANSACTION  -- Početak transakcije
INSERT INTO Osoba (Ime, Prezime) VALUES ('Lucija', 'Marić')  -- Prvi unos
SAVE TRAN KontrolnaTocka1  -- Prva kontrolna točka

INSERT INTO Osoba (Ime, Prezime) VALUES ('Ante', 'Marić')  -- Drugi unos
SAVE TRAN KontrolnaTocka2  -- Druga kontrolna točka

COMMIT  -- Potvrda cijele transakcije, oba zapisa se trajno spremaju
SELECT * FROM Osoba  -- Provjera: 'Lucija' i 'Ante' će biti prisutni u tablici

--7. U transakciji umetnite 1 zapis u Osoba i postavite kontrolnu točku. 
--   Umetnite još 1 zapis i vratite se na kontrolnu točku. Na kraju odustanite od transakcije.

GO
BEGIN TRANSACTION  -- Početak transakcije
INSERT INTO Osoba (Ime, Prezime) VALUES ('Nikola', 'Novak')  -- Prvi unos
SAVE TRAN KontrolnaTocka  -- Spremanje stanja nakon prvog unosa

INSERT INTO Osoba (Ime, Prezime) VALUES ('Petra', 'Novak')  -- Drugi unos koji će biti poništen
ROLLBACK TRAN KontrolnaTocka  -- Povratak na točku – briše se drugi unos, ali prvi ostaje unutar transakcije

ROLLBACK TRANSACTION  -- Poništava se i ostatak transakcije (dakle i prvi unos)
SELECT * FROM Osoba  -- Provjera: ni 'Nikola' ni 'Petra' NEĆE biti prisutni

--8. U transakciji umetnite 1 zapis u Osoba i postavite kontrolnu točku. 
--   Umetnite još 1 zapis i vratite se na kontrolnu točku. Na kraju potvrdite transakciju.

GO
BEGIN TRANSACTION  -- Početak transakcije
INSERT INTO Osoba (Ime, Prezime) VALUES ('Josipa', 'Tomić')  -- Prvi unos
SAVE TRAN KontrolnaTocka  -- Postavljanje kontrolne točke

INSERT INTO Osoba (Ime, Prezime) VALUES ('Krešimir', 'Tomić')  -- Drugi unos koji će biti poništen
ROLLBACK TRAN KontrolnaTocka  -- Vraćamo se na stanje nakon prvog unosa (drugi se briše)

COMMIT  -- Potvrda transakcije – prvi unos ('Josipa') ostaje, drugi je poništen
SELECT * FROM Osoba  -- Provjera: prisutan će biti samo zapis 'Josipa'