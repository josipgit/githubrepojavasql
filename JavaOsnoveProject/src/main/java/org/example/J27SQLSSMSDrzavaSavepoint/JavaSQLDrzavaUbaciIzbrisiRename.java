
/*
JAVA SQl zadatak 1:
Napiši Java konzolnu aplikaciju sa sljedećim funkcionalnostima:
Korisniku se prikazuje izbornik sa sljedećim opcijama:
1 – nova država
2 - izmjena postojeće države
3 - brisanje postojeće države
4 – prikaz svih država sortiranih po nazivu
5 – kraj
Svaka opcija poziva zasebnu metodu, ne koristiti switch-case i slicno za svaku pojedinu opciju.
Opcije 1 do 4 odnose se na CRUD operacije and tablicom Drzava u bazi AdventureWorks
Odabirom opcije 2 i 3, od korisnika je potrebno tražiti ID države koje želite izmijeniti ili pobrisati
Napomena: brišite i mijenjajte samo one države koje ste Vi ubacili (one za koje je IdDrzava veći od 3)
*/

package J27JavaSQL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Scanner;

import static java.lang.System.*;

public class JavaSQLDrzavaUbaciIzbrisiRename {
    public static void main(String[] args) {

        DataSource dataSource = createDataSource(); // kreiramo datasource sa dolje definiranim podacima

        try (Connection connection = dataSource.getConnection()) {
            out.println("Uspjesno ste spojeni na bazu podataka");

            Scanner scanner = new Scanner(in).useLocale(Locale.US);

            while (true) {
                System.out.println("Odaberi opciju:");
                int odabir = scanner.nextInt();
                scanner.nextLine(); // ocisti zaostali newline iz buffera kad stisnes Enter

                if (odabir == 1) {
                    ubaciNovuDrzavu(connection, scanner);
                } else if (odabir == 2) {
                    izmijeniDrzavu(connection, scanner);
                } else if (odabir == 3) {
                    izbrisiDrzavu(connection, scanner);
                } else if (odabir == 4) {
                    prikaziSveDrzave(connection);
                } else if (odabir == 5) {
                    System.out.println("Izlazim iz programa...");
                    connection.close();
                    scanner.close();
                    System.exit(0);
                } else {
                    System.out.println("Neispravan unos. Pokušajte ponovno.");
                }
            }

        } catch (SQLException e) {
            err.println("Greska prilikom spajanja na bazu podataka");
            e.printStackTrace();
        }
    } // end main

    private static DataSource createDataSource() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("localhost");
        //ds.setPortNumber(1433);
        ds.setDatabaseName("AdventureWorksOBP");
        ds.setUser("sa");
        ds.setPassword("SQL");
        ds.setEncrypt(false);
        return ds;
    }

    private static void ubaciNovuDrzavu(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Unesite naziv nove države:");
        String naziv = scanner.nextLine();
        String sql = "INSERT INTO Drzava (Naziv) VALUES ('" + naziv + "')";
        Statement stmt = connection.createStatement();
        int rowsAffected = stmt.executeUpdate(sql);
        if (rowsAffected > 0) {
            System.out.println("Država s ID-em " + naziv + " je uspješno preimenovana!");
        } else {
            System.out.println("Greska, drzava nije ubacena !!!!.");
        }
        connection.close();
        stmt.close();
    }

    private static void izmijeniDrzavu(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Unesite ID države za izmjenu:");
        int IDDrzaveZaPreimenovanje = scanner.nextInt();
        scanner.nextLine(); // ovo obavezno da ocistis buffer od Enter

        System.out.println("Unesite novi naziv države:");
        String noviNaziv = scanner.nextLine();

//        if (IDDrzaveZaPreimenovanje <= 3) {
//            System.out.println(" Ne smijete mijenjati postojeće države sa ID <= 3 !!!! ");
//            return;
//        }

        String sql = "UPDATE Drzava SET Naziv = '" + noviNaziv + "' WHERE IDDrzava = " + IDDrzaveZaPreimenovanje;
        Statement stmt = connection.createStatement();
        int rowsAffected = stmt.executeUpdate(sql);

        if (rowsAffected > 0) {
            System.out.println("Država s ID-em " + IDDrzaveZaPreimenovanje + " je uspješno preimenovana!");
        } else {
            System.out.println("Nije pronađena država s tim ID-em.");
        }
        connection.close();
        stmt.close();
    }

    private static void izbrisiDrzavu(Connection connection, Scanner scanner) throws SQLException {
        System.out.println("Unesi ID države za brisanje:");
        int IDDrzaveZaBrisanje = scanner.nextInt();

//        if (IDDrzaveZaBrisanje <= 3) {
//            System.out.println(" Ne smijete mijenjati postojeće države sa ID <= 3 !!!! ");
//            return;
//        }

        String sql = "DELETE FROM Drzava WHERE IDDrzava = " + IDDrzaveZaBrisanje;
        Statement stmt = connection.createStatement();
        int rowsAffected = stmt.executeUpdate(sql);
        if (rowsAffected > 0) {
            System.out.println("Država s ID-em " + IDDrzaveZaBrisanje + " je uspješno obrisana!");
        } else {
            System.out.println("Nije pronađena država s tim ID-em.");
        }
        connection.close();
        stmt.close();
    }

    private static void prikaziSveDrzave(Connection connection) throws SQLException {
        String sql = "SELECT * FROM Drzava ORDER BY Naziv";  // sortirano po nazivu
        Statement stmt = connection.createStatement();
        var rs = stmt.executeQuery(sql); // koristi executeQuery za SELECT

        System.out.println("Popis svih država sortiranih po nazivu: ");

        while (rs.next()) {
            int id = rs.getInt("IDDrzava");
            String naziv = rs.getString("Naziv");
            System.out.println(id + " - " + naziv);
        }
        rs.close();
        stmt.close();
    }

} // end class
