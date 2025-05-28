package J27JavaSQL;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseMetaData1 {

    public static void prikaziMetaPodatke() {

        String connectionURL = "jdbc:sqlserver://localhost:1433;database=AdventureWorksOBP;user=sa;password=SQL;encrypt=false;";
        try (Connection connection = DriverManager.getConnection(connectionURL)) {
            //Dohvaćanje meta podataka
            DatabaseMetaData metaData = connection.getMetaData();

            //Ispis meta podataka
            System.out.println("URL baze:" + metaData.getURL());
            System.out.println("Korisničko ime: " + metaData.getUserName());
            System.out.println("Product name: " + metaData.getDatabaseProductName());
            System.out.println("Product version: " + metaData.getDatabaseProductVersion());



        } catch (SQLException e) {
            System.out.println("Error: " + connectionURL);
        }

    }


    public static void main(String[] args) {
        prikaziMetaPodatke();
    }
}
