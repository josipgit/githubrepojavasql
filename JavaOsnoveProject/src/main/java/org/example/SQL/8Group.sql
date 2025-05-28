
--8 GroupCountAvgSum

SELECT DISTINCT p.Naziv 
FROM Stavka AS s
INNER JOIN Proizvod as p
on p.IDProizvod = s.ProizvodID
WHERE s.Kolicina > 35

--1. Ispišite sve boje proizvoda i pokraj svake napišite koliko proizvoda ima tu boju.
SELECT 
Boja, COUNT(*) AS BrojProizvoda
FROM Proizvod
GROUP BY Boja


SELECT 
Boja, PotkategorijaID, COUNT(*) AS Kolicina
FROM Proizvod
GROUP BY Boja, PotkategorijaID
ORDER BY Boja, PotkategorijaID


--2. Promijenite prethodni upit tako da sortirate padajuće prema broju proizvoda.
SELECT 
Boja, COUNT(*) AS BrojProizvoda
FROM Proizvod
GROUP BY Boja
ORDER BY BrojProizvoda DESC

--3. Promijenite prethodni upit tako da isključite nedefiniranu boju.
SELECT 
Boja, COUNT(*) AS BrojProizvoda
FROM Proizvod
WHERE Boja IS NOT NULL
GROUP BY Boja
ORDER BY BrojProizvoda DESC

--4. Ispišite koliko proizvoda svake boje ima u svakoj od potkategorija. Sortirajte prema potkategoriji i prema boji.
SELECT PotkategorijaID,Boja, COUNT(*) AS BrojProizvoda 
FROM Proizvod
WHERE PotkategorijaID IS NOT NULL
GROUP BY PotkategorijaID, Boja
ORDER BY PotkategorijaID, Boja



--5. Promijenite prethodni upit tako da ispišete 10 podkategorija i boja s najviše proizvoda.
SELECT TOP 10 PotkategorijaID,Boja, COUNT(*) AS BrojProizvoda 
FROM Proizvod
WHERE PotkategorijaID IS NOT NULL
GROUP BY PotkategorijaID, Boja
ORDER BY BrojProizvoda DESC

--6. Promijenite prethodni upit tako da umjesto ID potkategorije ispišete njen naziv.
SELECT pk.Naziv, p.Boja, COUNT(*) AS BrojProizvoda 
FROM Proizvod AS p
INNER JOIN Potkategorija AS pk ON pk.IDPotkategorija=p.PotkategorijaID
GROUP BY pk.Naziv, p.Boja
ORDER BY BrojProizvoda DESC

/*
DZ:
7. Ispišite nazive svih kategorija i pokraj svake napišite koliko potkategorija je u njoj.
8. Ispišite nazive svih kategorija i pokraj svake napišite koliko proizvoda je u njoj.
9. Ispišite sve različite cijene proizvoda i napišite koliko proizvoda ima svaku cijenu.
10.Ispišite koliko je računa izdano koje godine.
11. Ispišite brojeve svih račune izdane kupcu 377 i pokraj svakog ispišite ukupnu cijenu po svim stavkama.
12. Ispišite nazive svih potkategorija koje sadržavaju više od 10 proizvoda.
13. Ispišite ukupno zarađene iznose i broj prodanih primjeraka za svaki od ikad prodanih proizvoda.
14. Ispišite ukupno zarađene iznose za svaki od proizvoda koji je prodan u više od 2000 primjeraka.
15. Ispišite ukupno zarađene iznose za svaki od proizvoda koji je prodan u više od 2.000 primjeraka ili je zaradio više od 2.000.000 dolara.

*/


--Ispišite sve boje koje imaju više od 40 proizvoda.
SELECT 
	Boja, COUNT(*) AS BrojProizvoda
FROM Proizvod
WHERE Boja IS NOT NULL
GROUP BY Boja
HAVING COUNT(*) >40

SELECT 
		Naziv,
		Boja,
		CijenaBezPDV
FROM Proizvod
WHERE CijenaBezPDV > 
	(SELECT AVG(CijenaBezPDV) 
	 FROM Proizvod)
ORDER BY 
		CijenaBezPDV DESC

--primjer Konstantni upit - vraća jednu vrijednost
SELECT Naziv,
		(
			SELECT COUNT(*) 
			FROM Proizvod
		) AS BrojProizvoda
	FROM Proizvod

--primjer Korelirani upit 
SELECT TOP 5 Naziv,
	(
		SELECT SUM(s.Kolicina) 
		FROM Stavka AS s
	WHERE s.ProizvodID =p.IDProizvod
	) AS Prodano
	FROM Proizvod AS p
	ORDER BY Prodano DESC

	--konstantni
	SELECT 
		Naziv
	FROM Proizvod
	WHERE CijenaBezPDV > 
	(
		SELECT AVG(CijenaBezPDV) 
		FROM Proizvod
	)

	--korelirani
		SELECT *
	FROM Kupac AS k
	WHERE 
	(
		SELECT COUNT(*) 
		FROM Racun AS r
 		WHERE r.KupacID =	k.IDKupac
	) >= 27

	--Konstantni s operatorom IN:
	SELECT *
	FROM Proizvod AS p
	WHERE IDProizvod IN 
	(
		SELECT DISTINCT 	
		s.ProizvodID
		FROM Stavka AS s
	)

	--Korelirani s operatorom EXISTS: 
	SELECT *
	FROM Proizvod AS p
	WHERE EXISTS
	(
		SELECT * 
		FROM Stavka AS s
	WHERE s.ProizvodID =p.IDProizvod
	)

--Ispišite sve potkategorije i za svaku ispišite broj proizvoda u njoj.
  
  --upit koji ispisuje broj proizvoda po IDPotkategorije
  (SELECT COUNT(*)
  FROM Proizvod WHERE PotkategorijaID=2)

  SELECT Naziv, (SELECT COUNT(*)
  FROM Proizvod AS p WHERE p.PotkategorijaID=pk.IDPotkategorija) AS BrojProizvoda
  FROM Potkategorija AS pk
  ORDER BY BrojProizvoda DESC

  --Ispišite sve proizvode i pokraj svakog ispišite zarađeni iznos, od najboljih prema lošijim.
  (SELECT SUM(UkupnaCijena) FROM Stavka WHERE ProizvodID=776)

  SELECT Naziv, (SELECT SUM(UkupnaCijena) FROM Stavka AS s WHERE s.ProizvodID=p.IDProizvod) AS BrojProizvoda
  FROM Proizvod AS p
  ORDER BY Naziv


/*
1. Ispišite sve potkategorije i za svaku ispišite broj proizvoda u njoj.
2. Riješite prethodni zadatak pomoću spajanja.
3. Ispišite sve kategorije i za svaku ispišite broj proizvoda u njoj.
4. Ispišite sve proizvode i pokraj svakog ispišite zarađeni iznos, od najboljih prema lošijim.
5. Dohvatite sve proizvode, uz svaki proizvod ispišite prosječnu cijenu svih proizvoda te razliku prosječne cijene svih proizvoda i cijene tog proizvoda. U obzir uzmite samo proizvode s cijenom većom od nule.
6. Dohvatite imena i prezimena 5 komercijalista koji su izdali najviše računa.
7. Dohvatite imena i prezimena 5 najboljih komercijalista po broju realiziranih računa te uz svakog dohvatite i iznos prodane robe.
8. Dohvatite sve boje proizvoda. Uz svaku boju pomoću podupita dohvatite broj proizvoda u toj boji.
9. Dohvatite imena i prezimena svih kupaca iz Frankfurta i uz svakog ispišite broj računa koje je platio karticom, od višeg prema nižem.
10. Vratite sve proizvode čija je cijena pet ili više puta veća od prosjeka.
11. Vratite sve proizvode koji su prodavani, ali u količini manjoj od 5.
12. Vratite sve proizvode koji nikad nisu prodani:
* Pomoću IN ili NOT IN
* Pomoću EXISTS ili NOT EXISTS
* Pomoću spajanja
13. Vratite količinu zarađenog novca za svaku boju proizvoda.
14. Vratite količinu zarađenog novca za svaku boju proizvoda, ali samo za one boje koje su zaradile više od 20.000.000.
15. Vratiti sve proizvode koji imaju dodijeljenu potkategoriju i koji su prodani u količini većoj od 5000. Uz svaki proizvod vratiti prodanu količinu i naziv kategorije.
*/


SELECT Podaci.PotkategorijaID, Podaci.Boja, Podaci.Kolicina
FROM (
    SELECT PotkategorijaID, Boja, COUNT(*) AS Kolicina
    FROM Proizvod
    WHERE PotkategorijaID IS NOT NULL
    GROUP BY PotkategorijaID, Boja
) AS Podaci
WHERE Podaci.Kolicina > 10
ORDER BY Podaci.Kolicina DESC;


