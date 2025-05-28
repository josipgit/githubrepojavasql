
-- 3 Voce

-- Create the database
CREATE DATABASE VoceDB;
GO

-- Use the database
USE VoceDB;
GO

-- Create table Voce
CREATE TABLE Voce (
    IDVoce INT PRIMARY KEY IDENTITY(1,1),
    Naziv NVARCHAR(100) NOT NULL UNIQUE
);
GO

-- Create table Dobavljac
CREATE TABLE Dobavljac (
    IDDobavljac INT PRIMARY KEY IDENTITY(1,1),
    Ime NVARCHAR(50) NOT NULL,
    Prezime NVARCHAR(50) NOT NULL,
    Adresa NVARCHAR(255),
    BrojMobitela NVARCHAR(20)
);
GO

-- Create table Sorta
CREATE TABLE Sorta (
    IDSorta INT PRIMARY KEY IDENTITY(1,1),
    Naziv NVARCHAR(100) NOT NULL UNIQUE,  -- Unique constraint to prevent duplicate names
    MinimalnaKolicina DECIMAL(10,2) NOT NULL CHECK (MinimalnaKolicina >= 0),
    TrenutnaKolicina DECIMAL(10,2) NOT NULL CHECK (TrenutnaKolicina >= 0),
    NabavnaCijena DECIMAL(10,2) NOT NULL CHECK (NabavnaCijena >= 0), -- Prevent negative purchase price
    ProdajnaCijena DECIMAL(10,2) NOT NULL CHECK (ProdajnaCijena >= 0), -- Prevent negative selling price
    VoceID INT NOT NULL,
    FOREIGN KEY (VoceID) REFERENCES Voce(IDVoce) ON DELETE CASCADE
);
GO

-- Create table DobavljacSorta (Many-to-Many relationship)
CREATE TABLE DobavljacSorta (
    IDDobavljacSorta INT PRIMARY KEY IDENTITY(1,1),
    DobavljacID INT NOT NULL,
    SortaID INT NOT NULL,
    FOREIGN KEY (DobavljacID) REFERENCES Dobavljac(IDDobavljac) ON DELETE CASCADE,
    FOREIGN KEY (SortaID) REFERENCES Sorta(IDSorta) ON DELETE CASCADE
);
GO