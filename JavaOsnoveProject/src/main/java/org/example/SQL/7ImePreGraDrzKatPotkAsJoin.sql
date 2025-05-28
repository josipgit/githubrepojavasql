
-- 7 ImePrezimeGradDrzavaKategorijaPotkategorijaAsJoin 

/* Kod izmjene podataka paziti na uvjet WHERE */
UPDATE KupacVIP SET Ime = 'Marija' WHERE Ime = 'Karen'

/* Kupcu Kim Abercrombie promijenite prebivalište u Osijek. Kojem točno kupcu treba promijeniti prebivalište? */
SELECT * FROM Kupac WHERE Ime = 'Kim' AND Prezime = 'Abercrombie'
SELECT * FROM Grad

UPDATE Kupac SET GradID=2 WHERE Ime = 'Kim' AND Prezime = 'Abercrombie'


/* Kupcima čije prezime započinje sa slovom A promijenite prebivalište u Rijeka */
SELECT * FROM Kupac WHERE Prezime LIKE 'A%'
UPDATE Kupac SET GradID=4 WHERE Prezime LIKE 'A%'


/*
1. Kupcima s ID-evima 40, 41 i 42 promijenite e-mail u nepoznato@nepoznato.com
2. Kupcu Eduardo Diaz promijenite ime u Edo
3. Svim računima izdanim 01.04.2004. za koje se ne zna komercijalist i koji nisu plaćeni kreditnom karticom upišite komentar "Dodatno provjeriti!"
*/
/*--1.--*/
SELECT * FROM Kupac WHERE IDKupac IN (40, 41, 42)
UPDATE Kupac SET Email = 'nepoznato@nepoznato.com' WHERE IDKupac IN (40, 41, 42)

/*--2.--*/
SELECT * FROM Kupac WHERE Ime = 'Eduardo' AND Prezime = 'Diaz'
UPDATE Kupac SET Ime = 'Edo' WHERE Ime = 'Eduardo' AND Prezime = 'Diaz'
SELECT * FROM Kupac WHERE Ime = 'Edo' AND Prezime = 'Diaz'

/*--3.--*/
SELECT * FROM Racun
SELECT * FROM Racun WHERE DatumIzdavanja = '20040401' AND KomercijalistID IS NULL AND KreditnaKarticaID IS NULL
UPDATE Racun SET Komentar = 'Dodatno provjeriti!' WHERE DatumIzdavanja = '20040401' AND KomercijalistID IS NULL AND KreditnaKarticaID IS NULL

--brisanje retka iz tablice KupacVIP gdje je ID jednak 2, bez uvjeta WHERE, brisu se podaci cijele tablice!
DELETE FROM KupacVIP WHERE ID = 2 

/*
Obrišite državu Njemačku. U čemu je problem i kako ga riješiti?
Obrišite sve kupce koji se prezivaju Trtimirović. Je li se dogodila pogreška? Koliko ih je obrisano? 
Obrišite račun s ID-em 75123.
*/ 
--1
DELETE FROM dbo.Drzava WHERE Naziv = 'Njemačka'
SELECT * FROM Drzava
-- potrebno je obrisati sve podatke iz tablica sa stranim kljucevima

--2
SELECT * FROM Kupac WHERE Prezime = 'Trtimirović'
DELETE FROM Kupac WHERE Prezime = 'Trtimirović'

--3
SELECT * FROM Racun WHERE IDRacun = 75123
DELETE FROM Racun WHERE IDRacun = 75123


/*DZ: 
1. Upisati u bazu činjenicu da je 18.05.2011. Robertu Mrkonjiću (robi.mrki@gmail.si) iz Ljubljane izdan račun RN18052011 za troje bijele trkaće čarape veličine M i za 2 naljepnice za bicikl. Na nijedan artikl nije bilo popusta. Kupac je platio gotovinom, a prodaju je napravio novi komercijalist Garfild Mačković.
2. Promijenite u bazi netočan podatak da je gospodin Mrkonjić iz Ljubljane; on je ustvari iz Beča.
3. Promijenite u bazi netočan podatak da je gospodin Mrkonjić kupio naljepnice; on je ustvari kupio samo čarape.
*/

SELECT * FROM Grad
SELECT * FROM Drzava
--primjer1
SELECT g.Naziv AS NazivGrada, d.Naziv AS NazivDrzave
FROM Grad AS g
INNER JOIN Drzava AS d
ON g.DrzavaID = d.IDDrzava

SELECT * FROM Potkategorija
SELECT * FROM Kategorija

--varijanta 1
SELECT pk.Naziv, k.Naziv
FROM Potkategorija AS pk
INNER JOIN Kategorija AS k
ON pk.KategorijaID=k.IDKategorija

--varijanta 2
SELECT k.Naziv AS NazivKategorije, p.Naziv AS NazivPotkategorije
FROM Kategorija AS k
INNER JOIN Potkategorija AS p
ON k.IDKategorija = p.KategorijaID

--dodatni uvjeti prilikom spajanja
SELECT g.Naziv AS NazivGrada, d.Naziv AS NazivDrzave
FROM Grad AS g
INNER JOIN Drzava AS d
ON g.DrzavaID = d.IDDrzava
WHERE g.Naziv LIKE 'Z%' OR g.Naziv LIKE 'S%'
ORDER BY NazivGrada DESC


--Dohvatiti imena i prezimena svih kupaca i uz svakog ispisati naziv grada.
SELECT * FROM Grad
SELECT * FROM Kupac
SELECT
	k.Ime AS ImeKupca,
	k.Prezime AS PrezimeKupca,
	g.Naziv
FROM Kupac AS k
INNER JOIN Grad AS g ON k.GradID = g.IDGrad

--Dohvatiti imena i prezimena svih kupaca i uz svakog ispisati naziv grada i države.
SELECT
	k.Ime AS ImeKupca,
	k.Prezime AS PrezimeKupca,
	g.Naziv,
	d.Naziv
FROM Kupac AS k
INNER JOIN Grad AS g ON k.GradID = g.IDGrad
INNER JOIN Drzava AS d ON g.DrzavaID = d.IDDrzava


/*DZ: 
1. Upisati u bazu činjenicu da je 18.05.2011. Robertu Mrkonjiću (robi.mrki@gmail.si) iz Ljubljane izdan račun RN18052011 za troje bijele trkaće čarape veličine M i za 2 naljepnice za bicikl. Na nijedan artikl nije bilo popusta. Kupac je platio gotovinom, a prodaju je napravio novi komercijalist Garfild Mačković.
2. Promijenite u bazi netočan podatak da je gospodin Mrkonjić iz Ljubljane; on je ustvari iz Beča.
3. Promijenite u bazi netočan podatak da je gospodin Mrkonjić kupio naljepnice; on je ustvari kupio samo čarape.
4. Za sve račune izdane 01.08.2002. i plaćene American Expressom ispisati njihove ID-eve i brojeve te ime i prezime i grad kupca, ime i prezime komercijaliste te broj i podatke o isteku kreditne kartice. Rezultate sortirati prema prezimenu kupca.
5. Ispisati nazive proizvoda koji su na nekoj stavci računa prodani u više od 35 komada. Svaki proizvod navesti samo jednom.
6. Vratite broj svih proizvoda.
7. Vratite broj proizvoda koji imaju definiranu boju.
8. Vratite najvišu cijenu proizvoda.
9. Vratite prosječnu cijenu proizvoda iz potkategorije 16.
10.Vratite datume najstarijeg i najnovijeg računa izdanog kupcu 131.
*/


SELECT  
		MIN(CijenaBezPDV) AS NajnizaCijena,
		MAX(CijenaBezPDV) AS NajvisaCijena,
		AVG(CijenaBezPDV) AS ProsjecnaCijena,
		MIN(CijenaBezPDV) + MAX(CijenaBezPDV) AS ZbrojMinMax
	FROM Proizvod
	WHERE CijenaBezPDV > 0


SELECT Ime, ISNULL(Telefon, 0) AS Telefon FROM Kupac WHERE Telefon IS NULL

--Vratite broj svih proizvoda.
SELECT COUNT(*) FROM Proizvod 
SELECT COUNT(Naziv) FROM Proizvod
SELECT COUNT(Boja) FROM Proizvod
