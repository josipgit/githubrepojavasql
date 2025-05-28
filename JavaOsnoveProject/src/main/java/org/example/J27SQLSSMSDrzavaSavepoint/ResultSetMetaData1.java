package J27JavaSQL;

import java.sql.*;

public class ResultSetMetaData1 {
    public static void main(String[] args) {

        String connectionURL = "jdbc:sqlserver://localhost:1433;database=AdventureWorksOBP;encrypt=false;";
        String username = "sa";
        String password = "SQL";
        String query = "SELECT * FROM Kupac";

        try (Connection connection = DriverManager.getConnection(connectionURL, username, password);
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            System.out.println("=== ResultSet MetaData ===");
            System.out.println("Broj stupaca: " + columnCount);
            //Ispis informacija o stupcima
            System.out.println("=== Informacije o stupcima ===");
            System.out.printf("%s %s %s%n ", "Naziv stupca", "Tip podataka", "Nullable");
            for (int i = 1; i <= columnCount; i++) {
                System.out.printf("%s %s %s%n ", metaData.getColumnName(i), metaData.getColumnTypeName(i),
                        metaData.isNullable(i) == ResultSetMetaData.columnNullable ? "Yes" : "No");
            }

        } catch (SQLException e) {
            System.out.println("Error: " + connectionURL);
        }


    }
}
