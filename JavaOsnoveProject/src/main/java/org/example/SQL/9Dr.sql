

--Domaci rad 9

--1. Promijenite okidač tako da upisuje staru i novu vrijednost promijenjenog prezimena u Zapisnik.

-- Prvo izbrišemo postojeći trigger ako postoji
IF EXISTS (
    SELECT * FROM sys.triggers WHERE name = 'trg_AfterInsertDelete_Polaznik'
)
DROP TRIGGER trg_AfterInsertDelete_Polaznik;
GO

-- Kreiramo novi trigger koji reagira na INSERT, UPDATE, DELETE
CREATE TRIGGER trg_AfterInsertDelete_Polaznik
ON Polaznik
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @UmetnutiRedak INT = (SELECT COUNT(*) FROM inserted);
    DECLARE @ObrisaniRedak INT = (SELECT COUNT(*) FROM deleted);

    -- Ako se dogodila izmjena (UPDATE)
    IF EXISTS (SELECT * FROM inserted) AND EXISTS (SELECT * FROM deleted)
    BEGIN
        -- Upisujemo stare i nove vrijednosti prezimena (ako su se promijenile)
        INSERT INTO Zapisnik (Akcija, Detalji)
        SELECT 
            'Update',
            'Prezime promijenjeno sa "' + d.Prezime + '" na "' + i.Prezime + '" za ' + i.Ime
        FROM deleted d
        INNER JOIN inserted i ON d.IDPolaznik = i.IDPolaznik
        WHERE d.Prezime <> i.Prezime;  -- samo ako je došlo do promjene prezimena
    END

    -- Logiraj broj umetnutih i obrisanih redaka (standardno ponašanje)
    INSERT INTO Zapisnik (Akcija, Detalji) 
    VALUES (
        'Log',
        'Umetnutih redaka: ' + CAST(@UmetnutiRedak AS nvarchar) + 
        ', Obrisanih redaka: ' + CAST(@ObrisaniRedak AS nvarchar)
    );
END;

--testiranje zadatka 1:
-- Unesi novog polaznika:
INSERT INTO Polaznik (Ime, Prezime) VALUES ('Luka', 'Lukić');

-- Promijeni prezime tom polazniku:
UPDATE Polaznik SET Prezime = 'Nović' WHERE Ime = 'Luka';

-- Pogledaj sadržaj zapisnika:
SELECT * FROM Zapisnik;




--2. Napišite proceduru koja dohvaća prvih 10 redaka iz tablice Proizvod, 
--   prvih 5 redaka iz tablice KreditnaKartica i zadnja 3 retka iz tablice Racun. 
--   Pozovite proceduru. Uklonite proceduru.

--Kreiranje procedure:
-- Ako procedura već postoji, brišemo je
IF OBJECT_ID('DohvatiRazlicitePodatke') IS NOT NULL
    DROP PROCEDURE DohvatiRazlicitePodatke;
GO

-- Kreiranje procedure
CREATE PROCEDURE DohvatiRazlicitePodatke
AS
BEGIN
    SET NOCOUNT ON;

    -- Prvih 10 redaka iz tablice Proizvod
    PRINT '--- Prvih 10 Proizvoda ---';
    SELECT TOP 10 * FROM Proizvod;

    -- Prvih 5 redaka iz tablice KreditnaKartica
    PRINT '--- Prvih 5 Kreditnih Kartica ---';
    SELECT TOP 5 * FROM KreditnaKartica;

    -- Zadnja 3 retka iz tablice Racun, pretpostavljamo da IDRacun raste s vremenom
    PRINT '--- Zadnja 3 Racuna ---';
    SELECT TOP 3 * 
    FROM Racun
    ORDER BY IDRacun DESC;
END;

--testiranje zadatka 1:
-- Poziv procedure
EXEC DohvatiRazlicitePodatke;

-- Brisanje procedure nakon korištenja
DROP PROCEDURE DohvatiRazlicitePodatke;

-- Ako Racun nema IDRacun kao jedinstveni ključ koji raste, 
-- umjesto toga koristi neki drugi stupac (npr. DatumIzdavanja) 
-- za sortiranje zadnjih 3 retka:
SELECT TOP 3 * FROM Racun ORDER BY DatumIzdavanja DESC;




--3. Napišite proceduru koja prima četiri parametra potrebna za unos nove kreditne kartice. 
--   Neka procedura napravi novi zapis u KreditnaKartica. 
--   Neka procedura prije i nakon umetanja dohvati broj zapisa u tablici. 
--   Pozovite proceduru na oba načina. Uklonite proceduru.

--Kreiranje procedure: 
-- Ako već postoji procedura s ovim imenom, brišemo je
IF OBJECT_ID('DodajKreditnuKarticu', 'P') IS NOT NULL
    DROP PROCEDURE DodajKreditnuKarticu;
GO

-- Kreiranje procedure koja prima četiri ulazna parametra
CREATE PROCEDURE DodajKreditnuKarticu
    @BrojKartice NVARCHAR(30),
    @TipKartice NVARCHAR(20),
    @DatumIsteka DATE,
    @KupacID INT
AS
BEGIN
    SET NOCOUNT ON;

    -- Prikaz broja zapisa prije umetanja
    PRINT 'Broj zapisa u KreditnaKartica prije umetanja:';
    SELECT COUNT(*) AS BrojPrije FROM KreditnaKartica;

    -- Umetanje nove kartice
    INSERT INTO KreditnaKartica (BrojKartice, TipKartice, DatumIsteka, KupacID)
    VALUES (@BrojKartice, @TipKartice, @DatumIsteka, @KupacID);

    -- Prikaz broja zapisa nakon umetanja
    PRINT 'Broj zapisa u KreditnaKartica nakon umetanja:';
    SELECT COUNT(*) AS BrojPoslije FROM KreditnaKartica;
END;
GO

--Pozivanje po redoslijedu parametara: 
EXEC DodajKreditnuKarticu 
    '1234-5678-9012-3456', 
    'Visa', 
    '2026-12-31', 
    1;

--Pozivanje s imenovanim parametrima 
EXEC DodajKreditnuKarticu 
    @BrojKartice = '1111-2222-3333-4444', 
    @TipKartice = 'MasterCard', 
    @DatumIsteka = '2027-05-15', 
    @KupacID = 2;

-- Brisanje procedure kad zavrsis 
DROP PROCEDURE DodajKreditnuKarticu;




--4. Napišite proceduru koja prima tri boje i za svaku boju vraća proizvode u njoj. 
--   Pozovite proceduru i nakon toga je uklonite.
--5. Napišite proceduru koja prima kriterij po kojemu ćete filtrirati prezimena iz tablice Kupac. 
--   Neka procedura pomoću izlaznog parametra vrati broj zapisa koji zadovoljavaju zadani kriterij. 
--   Neka procedura vrati i sve zapise koji zadovoljavaju kriterij. 
--   Pozovite proceduru i ispišite vraćenu vrijednost. Uklonite proceduru.
--6. Napišite proceduru koja za zadanog komercijalistu pomoću izlaznih parametara vraća 
--   njegovo ime i prezime te ukupnu količinu izdanih računa.