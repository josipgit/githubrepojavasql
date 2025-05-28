
-- 11 Parcijalni Ispit 2

-- Kreiranje baze podataka JavaAdv
CREATE DATABASE JavaAdv
GO


-- Postavljanje konteksta na novokreiranu bazu
USE JavaAdv
GO


CREATE TABLE Polaznik (
    IDPolaznik INT IDENTITY(1,1)                     -- IDENTITY(1,1) je Auto-increment od 1 osigurava automatsko povećanje ID-eva
        CONSTRAINT PK_Polaznik PRIMARY KEY,          -- imenovano ograničenje za primarni ključ
    Ime NVARCHAR(100) NOT NULL,                       -- Obavezno ime (NOT NULL znaci da je obavezno)
    Prezime NVARCHAR(100) NOT NULL                   -- Obavezno prezime
);


CREATE TABLE ProgramObrazovanja (
    IDProgramObrazovanja INT IDENTITY(1,1)                     -- IDENTITY(1,1) je Auto-increment od 1 osigurava automatsko povećanje ID-eva
        CONSTRAINT PK_ProgramObrazovanja PRIMARY KEY,          -- imenovano ograničenje za primarni ključ
    Naziv NVARCHAR(100) NOT NULL,                       -- Obavezno
    CSVET INT NOT NULL                               -- Obavezno
);


-- Kreiranje tablice Upis s relacijom prema Polaznik i ProgramObrazovanja
CREATE TABLE Upis (
    IDUpis INT IDENTITY(1,1)                      -- Auto-increment ID
        CONSTRAINT PK_Upis PRIMARY KEY,           -- Primarni ključ
    PolaznikID INT NOT NULL,                         -- Obavezan vanjski ključ
    ProgramObrazovanjaID INT NOT NULL,               -- Obavezan vanjski ključ

    -- Vanjski ključ
    CONSTRAINT FK_Upis_Polaznik
        FOREIGN KEY (PolaznikID)                     -- Stupac koji se referencira
        REFERENCES Polaznik(IDPolaznik)              -- Povezivanje s tablicom Polaznik
        ON DELETE CASCADE                            -- Automatsko brisanje
        ON UPDATE NO ACTION,                         -- Zabrana promjene IDPolaznik

    -- Vanjski ključ
    CONSTRAINT FK_Upis_ProgramObrazovanja
        FOREIGN KEY (ProgramObrazovanjaID)                     -- Stupac koji se referencira
        REFERENCES ProgramObrazovanja(IDProgramObrazovanja)              -- Povezivanje s tablicom Polaznik
        ON DELETE CASCADE                            -- Automatsko brisanje
        ON UPDATE NO ACTION,                         -- Zabrana promjene IDPolaznik
);


CREATE OR ALTER PROCEDURE DodajPolaznika
    @Ime NVARCHAR(100),
    @Prezime NVARCHAR(100)
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        BEGIN TRANSACTION;

		-- Provjera za prazne stringove i samo razmake
		IF LEN(TRIM(@Ime)) = 0 OR LEN(TRIM(@Prezime)) = 0
		BEGIN
			THROW 50001, 'Ime i prezime ne smiju biti prazni!', 1;
			RETURN;
		END

        -- Provjera da Ime i Prezime nisu NULL
        IF @Ime IS NULL OR @Prezime IS NULL
        BEGIN
            THROW 50009, 'Ime i prezime su obavezni.', 1;
        END

        -- Provjera je li isti polaznik već upisan
        IF EXISTS (
            SELECT 1 FROM Polaznik
            WHERE Ime = @Ime AND Prezime = @Prezime
        )
        BEGIN
            THROW 50010, 'Polaznik već postoji.', 1;
        END

        -- Umetanje polaznika
        INSERT INTO Polaznik (Ime, Prezime)
        VALUES (@Ime, @Prezime);

        COMMIT;

        -- Poruka vidljiva iz Jave
        SELECT 'Polaznik uspješno unesen.' AS Poruka;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK;

        -- Opcionalno: vraćanje greške i prema Javi
        DECLARE @ErrMsg NVARCHAR(4000) = ERROR_MESSAGE();
        THROW 50011, @ErrMsg, 1;
    END CATCH
END;
GO


CREATE OR ALTER PROCEDURE DodajProgramObrazovanja
    @Naziv NVARCHAR(100),
    @CSVET INT
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        BEGIN TRANSACTION;

		-- Provjera za prazne stringove i samo razmake
		IF LEN(TRIM(@Naziv)) = 0
		BEGIN
			THROW 50001, 'Naziv i CSVET ne smiju biti prazni!', 1;
			RETURN;
		END

        -- Provjera je li već postoji program s tim nazivom
        IF EXISTS (SELECT 1 FROM ProgramObrazovanja WHERE Naziv = @Naziv)
        BEGIN
            -- Zamjena za RAISERROR
            THROW 50001, 'Program obrazovanja s tim nazivom već postoji.', 1;
            -- THROW automatski prekida izvršavanje, ne treba RETURN
        END

        -- Unos novog programa
        INSERT INTO ProgramObrazovanja (Naziv, CSVET)
        VALUES (@Naziv, @CSVET);

        COMMIT;
        -- PRINT se neće vidjeti u JDBC, možete koristiti OUTPUT parametar umjesto toga
        SELECT 'Program obrazovanja uspješno unesen.' AS Poruka;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK;

        -- Ponovno bacanje greške koja će biti uhvaćena u JDBC
        THROW;
    END CATCH
END;
GO


CREATE PROCEDURE UpisiPolaznikaNaProgram
    @PolaznikID INT,
    @ProgramObrazovanjaID INT
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        BEGIN TRANSACTION;

        IF NOT EXISTS (SELECT 1 FROM Polaznik WHERE IDPolaznik = @PolaznikID)
        BEGIN
            THROW 50001, 'Polaznik s tim ID-om ne postoji.', 1;
        END

        IF NOT EXISTS (SELECT 1 FROM ProgramObrazovanja WHERE IDProgramObrazovanja = @ProgramObrazovanjaID)
        BEGIN
            THROW 50002, 'Program obrazovanja s tim ID-om ne postoji.', 1;
        END

        IF EXISTS (
            SELECT 1 FROM Upis
            WHERE PolaznikID = @PolaznikID AND ProgramObrazovanjaID = @ProgramObrazovanjaID
        )
        BEGIN
            THROW 50003, 'Polaznik je već upisan na ovaj program.', 1;
        END

        INSERT INTO Upis (PolaznikID, ProgramObrazovanjaID)
        VALUES (@PolaznikID, @ProgramObrazovanjaID);

        COMMIT;
        SELECT 'Polaznik uspješno upisan na program.' AS Poruka;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK;

        THROW;  -- vrlo važno!
    END CATCH
END;
GO

GO


CREATE PROCEDURE PrebaciPolaznikaNaDrugiProgram
    @PolaznikID INT,
    @StariProgramID INT,
    @NoviProgramID INT
AS
BEGIN
    SET NOCOUNT ON;

    BEGIN TRY
        BEGIN TRANSACTION;

        -- Provjera postoji li polaznik
        IF NOT EXISTS (SELECT 1 FROM Polaznik WHERE IDPolaznik = @PolaznikID)
        BEGIN
            THROW 50020, 'Polaznik s tim ID-om ne postoji.', 1;
        END

        -- Provjera postoje li oba programa
        IF NOT EXISTS (SELECT 1 FROM ProgramObrazovanja WHERE IDProgramObrazovanja = @StariProgramID)
        BEGIN
            THROW 50021, 'Stari program obrazovanja ne postoji.', 1;
        END

        IF NOT EXISTS (SELECT 1 FROM ProgramObrazovanja WHERE IDProgramObrazovanja = @NoviProgramID)
        BEGIN
            THROW 50022, 'Novi program obrazovanja ne postoji.', 1;
        END

        -- Provjera je li polaznik upisan na stari program
        IF NOT EXISTS (
            SELECT 1 FROM Upis
            WHERE PolaznikID = @PolaznikID AND ProgramObrazovanjaID = @StariProgramID
        )
        BEGIN
            THROW 50023, 'Polaznik nije upisan na stari program.', 1;
        END

        -- Provjera da već nije upisan na novi program
        IF EXISTS (
            SELECT 1 FROM Upis
            WHERE PolaznikID = @PolaznikID AND ProgramObrazovanjaID = @NoviProgramID
        )
        BEGIN
            THROW 50024, 'Polaznik je već upisan na novi program.', 1;
        END

        -- Ažuriranje upisa
        UPDATE Upis
        SET ProgramObrazovanjaID = @NoviProgramID
        WHERE PolaznikID = @PolaznikID AND ProgramObrazovanjaID = @StariProgramID;

        COMMIT;

        -- Poruka koju Java može dohvatiti
        SELECT 'Polaznik je prebačen na novi program.' AS Poruka;
    END TRY
    BEGIN CATCH
        IF @@TRANCOUNT > 0
            ROLLBACK;

        -- Prosljeđivanje greške prema van
        DECLARE @ErrMsg NVARCHAR(4000) = ERROR_MESSAGE();
        THROW 50025, @ErrMsg, 1;
    END CATCH
END;
GO


CREATE PROCEDURE DohvatiSvePolaznike
AS
BEGIN
	SELECT * FROM Polaznik;
END;
EXEC DohvatiSvePolaznike;


CREATE PROCEDURE DohvatiSveProgrameObrazovanja
AS
BEGIN
	SELECT * FROM ProgramObrazovanja;
END;
EXEC DohvatiSveProgrameObrazovanja;


CREATE PROCEDURE DohvatiSveUpise
AS
BEGIN
	SELECT * FROM Upis;
END;
EXEC DohvatiSveUpise;


SELECT * FROM Polaznik
SELECT * FROM ProgramObrazovanja
SELECT * FROM Upis


DELETE FROM Polaznik
DELETE FROM ProgramObrazovanja
DELETE FROM Upis


DELETE FROM Polaznik WHERE IDPolaznik=14;
DELETE FROM ProgramObrazovanja WHERE IDProgramObrazovanja=8;


DROP PROCEDURE DohvatiSvePolaznike;
DROP PROCEDURE DohvatiSveProgrameObrazovanja;
DROP PROCEDURE DohvatiSveUpise;
DROP PROCEDURE UpisiPolaznikaNaProgram;
DROP PROCEDURE DodajProgramObrazovanja;
DROP PROCEDURE DodajPolaznika;
DROP PROCEDURE PrebaciPolaznikaNaDrugiProgram;


-- 1. Osnovna komanda za listanje svih procedura u bazi, ispis procedura:
SELECT name, create_date, modify_date
FROM sys.procedures
ORDER BY create_date DESC;


SELECT @@SERVERNAME AS ServerName; --vraća naziv trenutnog SQL Server instance na kojoj se izvršava
                                   --@@SERVERNAME je SQL Server sistemska varijabla (globalna promenljiva)







