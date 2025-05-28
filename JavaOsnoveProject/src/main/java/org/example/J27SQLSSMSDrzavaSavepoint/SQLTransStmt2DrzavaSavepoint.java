package J27JavaSQL;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import javax.sql.DataSource;
import java.sql.*;

public class SQLTransStmt2DrzavaSavepoint {
    public static void main(String[] args) {
        DataSource dataSource = createDataSource();
        Savepoint savepoint = null;
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Uspješno ste spojeni na bazu podataka");

            //try - catch blok za izvršavanje transakcije
            try (Statement stmt1 = connection.createStatement(); Statement stmt2 = connection.createStatement()) {
                connection.setAutoCommit(false); //isključivanje automatskog komita transakcije
                stmt1.executeUpdate("INSERT INTO Drzava (Naziv) VALUES ('Nigerija')");

                //postavljanje kontrolne točke sa kojom dodavanje Nigerije ostaje u SQL bazi
                //u slucaju da stmt2 padne
                savepoint = connection.setSavepoint("kontrolnatocka1");

                stmt2.executeUpdate("UPDATE Drzava SET Naziv = 'Njemacka' WHERE IDDRzava =  ");
                //komitanje transkacije
                connection.commit();
                System.out.println("Transakcija izvršena!");

            } catch (SQLException e) {

                System.err.println("Transakcija dijelom poništena!");
                try {
                    connection.rollback(savepoint);
                    connection.commit();
                    System.out.println("Transakcija vraćena na kontrolnu točku 1!");
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
            Statement stmt = connection.createStatement();
            //Dohvaćanje svih država
            ResultSet rs = stmt.executeQuery("SELECT IDDrzava, Naziv FROM Drzava");
            while (rs.next()) {
                System.out.printf("%d %s\n", rs.getInt("IDDrzava"), rs.getString("Naziv"));
            }
            rs.close();
            stmt.close();

        } catch (SQLException e) {
            System.err.println("Greška prilikom spajanja na bazu podataka");
            e.printStackTrace();
        }

    }


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