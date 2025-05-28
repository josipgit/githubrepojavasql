package org.example.J29ParcijalniIspit2;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static java.lang.System.*;

public class ParcijalniIspit2Main {
    public static void main(String[] args) {
        gc(); // ručno pokretanje garbage collectora
        out.flush(); // čišćenje output buffera radi bržeg ispisa

        Scanner scanner = new Scanner(System.in);
        String url = "jdbc:sqlserver://localhost:1433;databaseName=JavaAdv;encrypt=true;trustServerCertificate=true";
        String username = "sa";
        String password = "SQL";
        out.println();

        int izbor;
        do {
            out.println("\n\n\n\n\n\n\n\n ////////////////// START PROGRAMA: //////////////////////////////////////////////////////////// \n");
            prikaziSvePolaznike(url, username, password);
            out.println("\n");
            prikaziSveProgrameObrazovanja(url, username, password);
            out.println("\n");
            prikaziSveUpise(url, username, password);
            out.println("\n");
            prikaziIzbornik();
            izbor = scanner.nextInt();
            scanner.nextLine(); // čišćenje buffera nakon unosa broja

            switch (izbor) {
                case 1:
                    unosNovogPolaznika(scanner, url, username, password);
                    break;
                case 2:
                    unosNovogPrograma(scanner, url, username, password);
                    break;
                case 3:
                    upisPolaznikaNaProgram(scanner, url, username, password);
                    break;
                case 4:
                    prebacivanjePolaznikaNaNoviProgram(scanner, url, username, password);
                    break;
                case 5:
                    prikaziSveUpise(url, username, password);
                    break;
                case 6:
                    out.println("Program se gasi...");
                    break;
                default:
                    out.println("Nevažeći odabir. Molimo unesite broj od 1 do 6.");
            }
        } while (izbor != 6);

        scanner.close();
    }

    private static void prikaziIzbornik() {
        out.println("--------------------IZBORNIK--------------------");
        out.println("1. Unos novog polaznika");
        out.println("2. Unos novog programa obrazovanja");
        out.println("3. Upis polaznika na program obrazovanja");
        out.println("4. Prebacivanje polaznika na drugi program");
        out.println("5. Prikaz svih upisa polaznika i programa");
        out.println("6. Izlaz iz programa");
        out.println("----------------------------------------------------------");
        out.print("Odaberite opciju (1-6): ");
    }

    private static void unosNovogPolaznika(Scanner scanner, String url, String username, String password) {
        out.println("----------------------1. UNOS NOVOG POLAZNIKA----------------------------");
        out.print("Unesite ime polaznika: ");
        String Ime = scanner.nextLine().trim();
        out.print("Unesite prezime polaznika: ");
        String Prezime = scanner.nextLine().trim();

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            CallableStatement cs = conn.prepareCall("{call DodajPolaznika(?, ?)}");
            cs.setString(1, Ime);
            cs.setString(2, Prezime);
            cs.execute();

            out.println("Polaznik uspješno dodan.");
        } catch (Exception e) {
            out.println("Greška: " + e.getMessage());
        }
    }

    private static void unosNovogPrograma(Scanner scanner, String url, String username, String password) {
        out.println("----------------------2. UNOS NOVOG PROGRAMA OBRAZOVANJA----------------------------");
        out.print("Unesite naziv programa obrazovanja: ");
        String Naziv = scanner.nextLine();
        out.print("Unesite CSVET broj (npr. 5): ");
        int CSVET = scanner.nextInt();
        scanner.nextLine(); // čišćenje buffera

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            CallableStatement cs = conn.prepareCall("{call DodajProgramObrazovanja(?, ?)}");
            cs.setString(1, Naziv);
            cs.setInt(2, CSVET);
            cs.execute();

            out.println("Program obrazovanja uspješno dodan.");
        } catch (SQLException e) {
            out.println("Greška: " + e.getMessage());
        }
    }

    private static void upisPolaznikaNaProgram(Scanner scanner, String url, String username, String password) {
        out.println("----------------------3. UPIS POLAZNIKA NA PROGRAM OBRAZOVANJA----------------------");
        out.print("Unesite ID polaznika za upis: ");
        int PolaznikID = scanner.nextInt();
        out.print("Unesite ID programa obrazovanja: ");
        int ProgramObrazovanjaID = scanner.nextInt();
        scanner.nextLine(); // čišćenje buffera

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            CallableStatement cs = conn.prepareCall("{call UpisiPolaznikaNaProgram(?, ?)}");
            cs.setInt(1, PolaznikID);
            cs.setInt(2, ProgramObrazovanjaID);
            cs.execute();

            out.println("Polaznik je uspješno upisan na program.");
        } catch (SQLException e) {
            out.println("Greška: " + e.getMessage());
        }
    }

    private static void prebacivanjePolaznikaNaNoviProgram(Scanner scanner, String url, String username, String password) {
        out.println("----------------------4. PREBACIVANJE POLAZNIKA NA DRUGI PROGRAM----------------------");
        out.print("Unesite ID polaznika: ");
        int PolaznikID = scanner.nextInt();
        out.print("Unesite ID starog programa: ");
        int StariProgramID = scanner.nextInt();
        out.print("Unesite ID novog programa: ");
        int NoviProgramID = scanner.nextInt();
        scanner.nextLine(); // čišćenje buffera

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            CallableStatement cs = conn.prepareCall("{call PrebaciPolaznikaNaDrugiProgram(?, ?, ?)}");
            cs.setInt(1, PolaznikID);
            cs.setInt(2, StariProgramID);
            cs.setInt(3, NoviProgramID);
            cs.execute();

            out.println("Polaznik je uspješno prebačen na novi program.");
        } catch (SQLException e) {
            out.println("Greška: " + e.getMessage());
        }
    }

    private static void prikaziSvePolaznike(String url, String username, String password) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            CallableStatement cs = conn.prepareCall("{call DohvatiSvePolaznike()}");
            ResultSet rs = cs.executeQuery();

            out.println("--------------------SVI POLAZNICI:--------------------");
            out.printf("%-5s %-20s %-20s%n", "ID", "Ime", "Prezime");

            while (rs.next()) {
                int id = rs.getInt("IDPolaznik");
                String ime = rs.getString("Ime");
                String prezime = rs.getString("Prezime");
                out.printf("%-5d %-20s %-20s%n", id, ime, prezime);
            }
        } catch (SQLException e) {
            out.println("Greška: " + e.getMessage());
        }
    }

    private static void prikaziSveProgrameObrazovanja(String url, String username, String password) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            CallableStatement cs = conn.prepareCall("{call DohvatiSveProgrameObrazovanja()}");
            ResultSet rs = cs.executeQuery();

            out.println("--------------------SVI PROGRAMI OBRAZOVANJA:--------------------");
            out.printf("%-5s %-30s %-10s %n", "ID", "Naziv", "CSVET");

            while (rs.next()) {
                int id = rs.getInt("IDProgramObrazovanja");
                String naziv = rs.getString("Naziv");
                int csvet = rs.getInt("CSVET");
                out.printf("%-5d %-30s %-10d%n", id, naziv, csvet);
            }
        } catch (SQLException e) {
            out.println("Greška: " + e.getMessage());
        }
    }

    private static void prikaziSveUpise(String url, String username, String password) {
        out.println("--------------------SVI POLAZNICI I NJIHOVI PROGRAMI OBRAZOVANJA--------------------");

        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            CallableStatement cs = conn.prepareCall("{call DohvatiSveUpise()}");
            ResultSet rs = cs.executeQuery();

            // Ispis zaglavlja
            out.printf("%-20s %-20s %-20s %n",
                    "IDUpis", "PolaznikID", "ProgramObrazovanjaID");

            boolean imaRezultata = false;
            while (rs.next()) {
                imaRezultata = true;
                int IDUpis = rs.getInt("IDUpis");
                int PolaznikID = rs.getInt("PolaznikID");
                int ProgramObrazovanjaID = rs.getInt("ProgramObrazovanjaID");

                out.printf("%-20d %-20d %-20d %n",
                        IDUpis, PolaznikID, ProgramObrazovanjaID);
            }

            if (!imaRezultata) {
                out.println("Nema upisanih polaznika.");
            }

        } catch (SQLException e) {
            out.println("Greška: " + e.getMessage());
        }
    }

} // end class
