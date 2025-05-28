
-- 0 UvodUSQL

-- Kreiranje baze podataka UvodUSQL
CREATE DATABASE UvodUSQL
GO

-- Postavljanje konteksta na novokreiranu bazu
USE UvodUSQL
GO

-- Kreiranje tablice Polaznik2 s automatski inkrementirajućim primarnim ključem
CREATE TABLE Polaznik2 (
	IDPolaznik INT CONSTRAINT PK_Polaznik2 PRIMARY KEY IDENTITY, -- Primarni ključ s IDENTITY svojstvom
	Ime NVARCHAR(50), -- Ime polaznika
	Prezime NVARCHAR(50) -- Prezime polaznika
)

-- Kreiranje tablice Drzava
CREATE TABLE Drzava (
	IDDrzava INT CONSTRAINT PK_Drzava PRIMARY KEY IDENTITY, -- Primarni ključ s IDENTITY
	Naziv NVARCHAR(50) NULL -- Naziv države (može biti NULL)
)

-- Kreiranje tablice Grad s vanjskim ključem koji referencira tablicu Drzava
CREATE TABLE Grad (
	IDGrad INT CONSTRAINT PK_Grad PRIMARY KEY, -- Primarni ključ
	Naziv NVARCHAR(50), -- Naziv grada
	DrzavaID INT CONSTRAINT FK_Grad_Drzava 
		FOREIGN KEY REFERENCES Drzava(IDDrzava) -- Vanjski ključ prema tablici Drzava
)

-- Kreiranje tablice Ispit s više vanjskih ključeva
CREATE TABLE Ispit (
	IDIspit INT CONSTRAINT PK_Ispit PRIMARY KEY, -- Primarni ključ
	PolaznikID INT CONSTRAINT FK_Ispit_Polaznik 
		FOREIGN KEY REFERENCES Polaznik2(IDPolaznik) NOT NULL, -- Vanjski ključ prema Polaznik (tablica mora prethodno postojati)
	POID INT CONSTRAINT FK_Ispit_Polaznik 
		FOREIGN KEY REFERENCES PO(IDPO) NOT NULL, -- Vanjski ključ prema PO (tablica mora prethodno postojati)
	Ocjena INT NOT NULL, -- Ocjena je obavezna
	StegovnaMjera NVARCHAR(500) NULL, -- Opcionalna stegovna mjera
	Napomena NVARCHAR(200) -- Opcionalna napomena
)

-- Kreiranje tablice Osoba s CHECK ograničenjem
CREATE TABLE Osoba (
	IDOsoba INT CONSTRAINT PK_Osoba PRIMARY KEY, -- Primarni ključ
	Ime NVARCHAR(50), -- Ime osobe
	BrojCipela INT CONSTRAINT CH_Osoba_BrojCipela 
		CHECK (BrojCipela > 30 AND BrojCipela < 60) -- Broj cipela mora biti između 31 i 59
)

-- Kreiranje tablice Polaznik3 s UNIQUE ograničenjem na JMBAG
CREATE TABLE Polaznik3 (
	IDPolaznik INT CONSTRAINT PK_Polaznik3 PRIMARY KEY, -- Primarni ključ
	Ime NVARCHAR(50), -- Ime
	Prezime NVARCHAR(50), -- Prezime
	JMBAG NVARCHAR(20) CONSTRAINT UQ_Polaznik3_JMBAG 
		UNIQUE NOT NULL -- Jedinstveni i obavezni JMBAG
)

-- Kreiranje tablice Zaposlenik s default vrijednosti i bez vanjskih ključeva (verzija za UvodUSQL)
CREATE TABLE Zaposlenik (
	IDZaposlenik INT CONSTRAINT PK_Zaposl PRIMARY KEY, -- Primarni ključ
	Ime NVARCHAR(50), -- Ime zaposlenika
	Prezime NVARCHAR(50), -- Prezime zaposlenika
	Pripravnik BIT CONSTRAINT DF_Zaposlenik_Pripravnik DEFAULT (0) -- Default vrijednost za pripravnika je 0
)

-- Kreiranje nove baze podataka Vjezba03
CREATE DATABASE Vjezba03
GO

-- Postavljanje konteksta na novu bazu
USE Vjezba03
GO

-- Kreiranje tablice PoslovniPartner s IDENTITY primarnim ključem
CREATE TABLE PoslovniPartner (
	IDPoslovniPartner INT CONSTRAINT PK_PoslovniPartner PRIMARY KEY IDENTITY, -- Primarni ključ s IDENTITY
	Naziv NVARCHAR(30) -- Naziv partnera
)

-- Kreiranje tablice Zaposlenik u bazi Vjezba03 s vanjskim ključem prema PoslovniPartner
CREATE TABLE Zaposlenik (
	IDZaposlenik INT CONSTRAINT PK_Zaposl PRIMARY KEY, -- Primarni ključ
	Ime NVARCHAR(50), -- Ime zaposlenika
	Prezime NVARCHAR(50), -- Prezime zaposlenika
	Pripravnik BIT CONSTRAINT DF_Zaposlenik_Pripravnik DEFAULT (0), -- Default vrijednost za pripravnika
	PoslovniPartnerID INT CONSTRAINT FK_Zaposlenik_PoslovniPartner 
		FOREIGN KEY REFERENCES PoslovniPartner(IDPoslovniPartner) NOT NULL -- Vanjski ključ prema PoslovniPartner
)