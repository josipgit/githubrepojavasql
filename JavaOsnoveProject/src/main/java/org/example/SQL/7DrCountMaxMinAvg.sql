
-- 7 DrCountMaxMinAvg

--1. Upisati u bazu činjenicu da je 18.05.2011. Robertu Mrkonjiću (robi.mrki@gmail.si) 
--   iz Ljubljane izdan račun RN18052011 za troje bijele trkaće čarape veličine M i za 2 
--   naljepnice za bicikl. Na nijedan artikl nije bilo popusta. Kupac je platio gotovinom, 
--   a prodaju je napravio novi komercijalist Garfild Mačković.
INSERT INTO Drzava VALUES ('Slovenija')
INSERT INTO Grad (Naziv, DrzavaID) 
SELECT 'Ljubljana', IDDrzava FROM Drzava WHERE Naziv='Slovenija'
INSERT INTO Kupac (Ime, Prezime, Email, Telefon, GradID)
VALUES (
    'Robert',
    'Mrkonjic',
    'robi.mrki@gmail.si',
    NULL,
    (SELECT IDGrad FROM Grad WHERE Naziv = 'Ljubljana')
);
DELETE FROM Kupac WHERE Ime = 'Robert' AND Prezime = 'Mrkonjic'
SELECT * FROM Kupac WHERE Ime = 'Robert' AND Prezime LIKE 'Mr%'


--2. Promijenite u bazi netočan podatak da je gospodin Mrkonjić iz Ljubljane; 
--   on je ustvari iz Beča.
INSERT INTO Drzava VALUES ('Austrija')
INSERT INTO Grad (Naziv, DrzavaID) 
SELECT 'Bec', IDDrzava FROM Drzava WHERE Naziv='Austrija'
UPDATE Kupac SET GradID = 16 WHERE Ime = 'Robert' AND Prezime = 'Mrkonjic'
SELECT * FROM Kupac WHERE Ime = 'Robert' AND Prezime = 'Mrkonjic'


--6. Vratite broj svih proizvoda.
SELECT COUNT(*) AS total_proizvoda
FROM Proizvod;


--7. Vratite broj proizvoda koji imaju definiranu boju.
SELECT COUNT(*) AS broj_sa_bojom
FROM Proizvod
WHERE Boja IS NOT NULL;

--8. Vratite najvišu cijenu proizvoda.
SELECT MAX(CijenaBezPDV) AS najvisa_cijena
FROM Proizvod;


--9. Vratite prosječnu cijenu proizvoda iz potkategorije 16.
SELECT AVG(CijenaBezPDV) AS prosjecna_cijena_iz_pot_16
FROM Proizvod
WHERE PotkategorijaID = 16;


--10.Vratite datume najstarijeg i najnovijeg računa izdanog kupcu 131.
SELECT MIN(DatumIzdavanja) AS najstariji_racun, MAX(DatumIzdavanja) AS najnoviji_racun
FROM Racun
WHERE KupacID = 131;