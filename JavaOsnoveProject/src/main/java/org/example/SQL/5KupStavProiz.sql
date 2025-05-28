
-- 5 KupacStavkaProizvod

SELECT * FROM Proizvod
SELECT * FROM Kupac WHERE Ime='Lili'
SELECT * FROM Kupac WHERE Prezime='Lee'
SELECT * FROM Kupac WHERE Ime='Ana' AND Prezime='Diaz'
SELECT * FROM Kupac WHERE GradID = 2
SELECT * FROM Kupac WHERE Ime='Ana' OR Prezime='Tamara'
SELECT * FROM Kupac WHERE (Ime='Ana' OR Prezime='Tamara') AND GradID = 2
SELECT * FROM Kupac WHERE (Ime='Ana' OR Prezime='Tamara') AND NOT GradID = 2
SELECT * FROM Stavka WHERE UkupnaCijena<2
SELECT * FROM Stavka WHERE UkupnaCijena>=23000
SELECT * FROM Stavka WHERE Kolicina >=20 AND Kolicina <=22
SELECT * FROM Stavka WHERE Kolicina between 20 and 22 --moze i malo slovo

SELECT * FROM Proizvod
SELECT * FROM Proizvod where Boja = 'Plava' or Boja = 'Crvena'
SELECT * FROM Proizvod where Boja IS NULL --boja nije definirana
SELECT * FROM Proizvod where Boja IS NULL and  CijenaBezPDV<25

/*
Dohvatiti sve ra?une iz tablice Racun:
Koji su izdani 14.7.2004.
Koji su izdani 14.7.2004. ili 15.7.2004.
Koji su izdani izme?u 14.7.2004. i 21.7.2004.
*/
SELECT * FROM Racun WHERE DatumIzdavanja='20040714'
SELECT * FROM Racun WHERE DatumIzdavanja='20040714' OR DatumIzdavanja='20040715'
SELECT * FROM Racun WHERE DatumIzdavanja BETWEEN  '20040714' AND '20040721' 
/* DZ
Dohvatiti sve kupce iz tablice Kupac:
?ija je vrijednost primarnog klju?a 10, 25, 74 ili 154
?ije ime zapo?inje slovima "Ki"
?ije prezime završava slovima "ams"
?ije prezime zapo?inje slovom "D" i prezime sadržava string "re"
*/ 

------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
------------------------------------------------------------------------------------
--DINKO JE SVE OVO ISPOD !!!!!!!

--Dohvatite sve retke iz tablice Proizvod
--Dohvatite sve retke iz tablice Drzava
--Dohvatite sve retke iz tablice Grad

SELECT * FROM Proizvod
SELECT * FROM Drzava
SELECT * FROM Grad

/*
Dohvatiti sve kupce iz tablice Kupac:
Koji se zovu Lili
Koji se prezivaju Lee
Koji se zovu Ana i prezivaju Diaz
Koji su iz Osijeka
Koji se zovu Ana ili Tamara
Koji se zovu Ana ili Tamara i iz Osijeka su
Koji se zovu Ana ili Tamara i nisu iz Osijeka
*/
SELECT * FROM Kupac WHERE Ime='Lili'
SELECT * FROM Kupac WHERE Prezime='Lee'
SELECT * FROM Kupac WHERE Ime='Ana' AND Prezime='Diaz'
SELECT * FROM Kupac WHERE Ime='Ana' AND GradID=2
SELECT * FROM Kupac WHERE Ime='Ana' OR Ime='Tamara'
SELECT * FROM Kupac WHERE (Ime='Ana' OR Ime='Tamara') AND GradID=2
SELECT * FROM Kupac WHERE (Ime='Ana' OR Ime='Tamara') AND NOT GradID=2


/*
Dohvatiti sve stavke iz tablice Stavka:
Čija je ukupna cijena manja od 2 eura
Čija je ukupna cijena veća ili jednaka 23000 eura
Čija je količina veća ili jednaka 20 i manja ili jednaka 22

*/
SELECT * FROM Stavka WHERE UkupnaCijena<2
SELECT * FROM Stavka WHERE UkupnaCijena>=23000
SELECT * FROM Stavka WHERE Kolicina>=20 AND Kolicina<=22
SELECT * FROM Stavka WHERE Kolicina BETWEEN 20 AND 22
/*
Dohvatiti sve proizvode iz tablice Proizvod:
Čija je boja plava ili crvena
Čija boja nije definirana
Čija je boja srebrna ili nije definirana
Čija je boja definirana
Čija boja nije definirana i cijena je manja od 25 eura
*/
SELECT * FROM Proizvod WHERE Boja IN('Plava', 'Crvena')
SELECT * FROM Proizvod WHERE Boja='Plava' OR Boja='Crvena'

SELECT * FROM Proizvod WHERE Boja IS NULL
SELECT * FROM Proizvod WHERE Boja='Srebrna' OR Boja IS NULL
SELECT * FROM Proizvod WHERE Boja IS NOT NULL
SELECT * FROM Proizvod WHERE Boja IS NULL AND CijenaBezPDV<25 

/*
Dohvatiti sve račune iz tablice Racun:
Koji su izdani 14.7.2004.
Koji su izdani 14.7.2004. ili 15.7.2004.
Koji su izdani između 14.7.2004. i 21.7.2004.
*/
SELECT * FROM Racun WHERE DatumIzdavanja='20040714'
SELECT * FROM Racun WHERE DatumIzdavanja='20040714' OR DatumIzdavanja='20040715'
SELECT * FROM Racun WHERE DatumIzdavanja IN ('2004-07-14', '2004-07-15')
SELECT * FROM Racun WHERE DatumIzdavanja BETWEEN'20040714' AND '20040721'

/* DZ
Dohvatiti sve kupce iz tablice Kupac:
Čija je vrijednost primarnog ključa 10, 25, 74 ili 154
Čije ime započinje slovima "Ki"
Čije prezime završava slovima "ams"
Čije prezime započinje slovom "D" i prezime sadržava string "re"
*/