package J26EvidencijaPolaznikaHash;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class CallableStatementDrzavaDinkoMain {
    public static void main(String[] args) {

        DataSource dataSource = createDataSource();
//        UnosNovihDrzava(dataSource);
        BrisanjeUnesenihDrzava(dataSource);
    } // end main

    private static void BrisanjeUnesenihDrzava(DataSource dataSource) {
        try (Connection connection = dataSource.getConnection()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Unesi ID od kojeg naviše brišemo sve države:");
            int idDrzave = scanner.nextInt();

            String sql = "{CALL brisiNoveDrzave(?)}";
            CallableStatement stmt = connection.prepareCall(sql);
            stmt.setInt(1,idDrzave);
            stmt.executeUpdate();

            System.out.println("Države su uspješno izbrisane!");
        } catch (SQLException e) {
            System.err.println("Greška prilikom spajanja na bazu podataka:");
            e.printStackTrace();
        }
    }

    private static void UnosNovihDrzava(DataSource dataSource) {
        ArrayList<String> drzave = new ArrayList<>();
        drzave.add("Francuska");
        drzave.add("UK");
        drzave.add("SAD");
        drzave.add("Rusija");
        drzave.add("Poljska");
        drzave.add("Japan");
        drzave.add("Kina");
        drzave.add("Indija");
        drzave.add("Vijetnam");
        drzave.add("Brazil");

        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO Drzava (Naziv) VALUES(?)";
            PreparedStatement stmt = connection.prepareStatement(sql);

            for (String drzava: drzave) {
                stmt.setString(1,drzava);
                stmt.executeUpdate();
            }

            System.out.println("Države su uspješno kreirane!");
        } catch (SQLException e) {
            System.err.println("Greška prilikom spajanja na bazu podataka:");
            e.printStackTrace();
        }
    }

    // Metoda za stvaranje DataSource objekta
    private static DataSource createDataSource() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setServerName("localhost");
        dataSource.setPortNumber(1433);
        dataSource.setDatabaseName("AdventureWorksOBP");
        dataSource.setUser("sa");
        dataSource.setPassword("SQL");
        dataSource.setEncrypt(false);
        return dataSource;
    }

} // end class