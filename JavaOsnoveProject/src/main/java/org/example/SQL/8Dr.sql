

-- 8 DrKategorijaPotkategorijaCijenaStavkaRacun
--   CountLeftJoinInnerJoinAsGroupOrderYearSumHaving


--7. Ispišite nazive svih kategorija i pokraj svake napišite koliko potkategorija je u njoj.
SELECT 
    k.Naziv AS NazivKategorije,          -- Naziv kategorije (preimenovan u rezultatu radi jasnoće)
    COUNT(pk.IDPotkategorija) AS BrojPotkategorija  -- Broji koliko potkategorija pripada svakoj kategoriji
FROM Kategorija AS k                      -- Glavna tablica kategorija (alias 'k')
LEFT JOIN                                -- Lijevo spajanje kako bi uključili i kategorije bez potkategorija
    Potkategorija AS pk                  -- Tablica potkategorija (alias 'pk')
    ON k.IDKategorija = pk.KategorijaID  -- Spajanje po stranom ključu (ID kategorije)
GROUP BY 
    k.Naziv                              -- Grupiranje rezultata po nazivu kategorije
ORDER BY 
    BrojPotkategorija DESC;              -- Silazno sortiranje po broju potkategorija


--8. Ispišite nazive svih kategorija i pokraj svake napišite koliko proizvoda je u njoj.
SELECT 
    k.Naziv AS NazivKategorije,          -- Naziv glavne kategorije (koristimo alias za jasnoću)
    COUNT(p.IDProizvod) AS BrojProizvoda  -- Brojanje proizvoda po kategoriji
FROM 
    Kategorija AS k                      -- Početna tablica - glavne kategorije
INNER JOIN                               -- Unutarnje spajanje
    Potkategorija AS pk                  -- Tablica potkategorija
    ON k.IDKategorija = pk.KategorijaID  -- Spoj po ID-u kategorije
INNER JOIN                               -- Drugo unutarnje spajanje
    Proizvod AS p                        -- Tablica proizvoda
    ON pk.IDPotkategorija = p.PotkategorijaID  -- Spoj po ID-u potkategorije
GROUP BY 
    k.Naziv                              -- Grupiranje rezultata po nazivu kategorije
ORDER BY 
    BrojProizvoda DESC;                  -- Silazno sortiranje po broju proizvoda


--9. Ispišite sve različite cijene proizvoda i napišite koliko proizvoda ima svaku cijenu.
SELECT 
    CijenaBezPDV,               -- Cijena proizvoda bez PDV-a (grupa za analizu)
    COUNT(*) AS BrojProizvoda   -- Broj proizvoda po svakoj cijenovnoj grupi
FROM 
    Proizvod                    -- Iz tablice proizvoda
GROUP BY 
    CijenaBezPDV                -- Grupiranje podataka po cijenama
ORDER BY 
    CijenaBezPDV ASC;           -- Sortiranje cijena od najniže prema najvišoj


--10.Ispišite koliko je računa izdano koje godine.
SELECT 
    YEAR(DatumIzdavanja) AS Godina,  -- Dohvaća godinu iz datuma izdavanja (koristimo YEAR funkciju)
    COUNT(*) AS BrojRacuna           -- Broji ukupan broj računa po godini
FROM 
    Racun                           -- Podaci se uzimaju iz tablice računa
GROUP BY 
    YEAR(DatumIzdavanja)            -- Grupiranje rezultata po godini izdavanja
ORDER BY 
    Godina ASC;                     -- Sortiranje godina uzlazno (od najstarije prema najnovijoj)


--11. Ispišite brojeve svih račune izdane kupcu 377 i pokraj svakog ispišite ukupnu cijenu po svim stavkama.
SELECT 
    r.BrojRacuna,                     -- Broj računa iz tablice Racun
    s.IDStavka,                       -- ID stavke iz tablice Stavka
    s.ProizvodID,                     -- ID proizvoda u ovoj stavci
    s.Kolicina,                       -- Količina naručenog proizvoda
    s.CijenaPoKomadu,                 -- Jedinična cijena proizvoda
    s.PopustUPostocima,               -- Popust primijenjen na stavku (u postocima)
    s.UkupnaCijena                    -- Ukupna cijena stavke (količina × jedinična cijena)
FROM 
    Stavka AS s                       -- Podaci iz tablice stavki računa (alias 's')
    JOIN Racun AS r 
        ON s.RacunID = r.IDRacun      -- Spajanje s tablicom računa prema ID-jevima
WHERE 
    r.KupacID = 377                   -- Filtrira samo račune za kupca s ID 377
ORDER BY 
    r.BrojRacuna,                     -- Sortiranje po broju računa
    s.IDStavka;                       -- Zatim po ID-u stavke


--12. Ispišite nazive svih potkategorija koje sadržavaju više od 10 proizvoda.
SELECT 
    pk.Naziv AS NazivPotkategorije,          -- Prikazujemo naziv potkategorije
    COUNT(p.IDProizvod) AS BrojProizvoda     -- Brojimo koliko proizvoda ima u toj potkategoriji
FROM Proizvod AS p
JOIN Potkategorija AS pk 
    ON p.PotkategorijaID = pk.IDPotkategorija-- Spajamo tablice prema ID potkategorije
GROUP BY pk.Naziv                            -- Grupiramo po nazivu potkategorije
HAVING COUNT(p.IDProizvod) > 10              -- Zadržavamo samo one s više od 10 proizvoda
ORDER BY BrojProizvoda DESC;                 -- Sortiramo od najvećeg prema najmanjem broju proizvoda

--13. Ispišite ukupno zarađene iznose i broj prodanih primjeraka za svaki od ikad prodanih proizvoda.
SELECT 
    p.Naziv AS NazivProizvoda,               -- Naziv proizvoda iz tablice Proizvod
    SUM(s.UkupnaCijena) AS UkupnaZarada,     -- Zbrajamo ukupnu cijenu svih prodanih stavki tog proizvoda
    SUM(s.Kolicina) AS UkupnoPrimjeraka      -- Zbrajamo količine, tj. broj prodanih komada proizvoda
FROM Stavka AS s
JOIN Proizvod AS p 
    ON s.ProizvodID = p.IDProizvod           -- Spajamo 'Stavka' i 'Proizvod' tablice prema ID-u proizvoda
GROUP BY p.Naziv                             -- Grupiramo po nazivu proizvoda
ORDER BY UkupnaZarada DESC;                  -- Sortiramo od najviše zarade prema nižoj


--14. Ispišite ukupno zarađene iznose za svaki od proizvoda koji je prodan u više od 2000 primjeraka.
SELECT 
    p.Naziv AS NazivProizvoda,               -- Prikazujemo naziv proizvoda
    SUM(s.UkupnaCijena) AS UkupnaZarada,     -- Zbrajamo ukupnu zaradu po proizvodu
    SUM(s.Kolicina) AS UkupnoProdano         -- Zbrajamo broj prodanih komada (primjeraka)
FROM Stavka AS s
JOIN Proizvod AS p 
    ON s.ProizvodID = p.IDProizvod           -- Spajamo tablice Stavka i Proizvod prema ID-u proizvoda
GROUP BY p.Naziv                             -- Grupiramo po nazivu proizvoda
HAVING SUM(s.Kolicina) > 2000                -- Prikazujemo samo one proizvode koji su prodani više od 2000 puta
ORDER BY UkupnaZarada DESC;                  -- Sortiramo po ukupnoj zaradi, od najveće prema najmanjoj


--15. Ispišite ukupno zarađene iznose za svaki od proizvoda koji je
--    prodan u više od 2.000 primjeraka ili je zaradio više od 2.000.000 dolara.
SELECT 
    p.Naziv AS NazivProizvoda,               -- Prikazujemo naziv proizvoda
    SUM(s.UkupnaCijena) AS UkupnaZarada,     -- Zbrajamo ukupnu zaradu po proizvodu
    SUM(s.Kolicina) AS UkupnoProdano         -- Zbrajamo broj prodanih komada (primjeraka)
FROM Stavka AS s
JOIN Proizvod AS p 
    ON s.ProizvodID = p.IDProizvod           -- Spajamo tablice Stavka i Proizvod prema ID-u proizvoda
GROUP BY p.Naziv                             -- Grupiramo po nazivu proizvoda
-- Sada prikazujemo samo one proizvode koji su prodani više od 2000 puta,
-- i one od kojih je zaradeno vise od 2 000 000 na prodaji
HAVING ((SUM(s.Kolicina) > 2000) OR (SUM(s.UkupnaCijena) > 2000000))
ORDER BY UkupnaZarada DESC;                  -- Sortiramo po ukupnoj zaradi, od najveće prema najmanjoj



















