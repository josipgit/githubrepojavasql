package J27JavaSQL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class SQLTransStmt2SavepointStavka {
    public static void main(String[] args) {
        DataSource dataSource = createDataSource();
        Savepoint savepoint = null; // kontrolna tocka
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Uspješno ste spojeni na bazu podataka");

            //try - catch blok za izvršavanje transakcije
            try (Statement stmt1 = connection.createStatement(); Statement stmt2 = connection.createStatement()) {
                connection.setAutoCommit(false); //isključivanje automatskog komita transakcije
                stmt1.executeUpdate("UPDATE Stavka SET CijenaPoKomadu=CijenaPoKomadu+10 WHERE IDStavka = 8");
                savepoint = connection.setSavepoint("KontrolnaTocka1");
                stmt2.executeUpdate("UPDATE Stavka SET CijenaPoKomadu=CijenaPoKomadu-10 WHERE IDStavka = 9");
                //komitanje transkacije
                connection.commit();
                System.out.println("Transakcija izvršena!");

            } catch (SQLException e) {
                connection.rollback();
                System.err.println("Transakcija poništena!");
            }


        } catch (SQLException e) {
            System.err.println("Greška prilikom spajanja na bazu podataka");
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
}
