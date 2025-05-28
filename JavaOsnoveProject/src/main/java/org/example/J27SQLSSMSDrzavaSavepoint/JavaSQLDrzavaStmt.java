package J27JavaSQL;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import org.intellij.lang.annotations.Language;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.System.err;
import static java.lang.System.out;

public class JavaSQLDrzavaStmt {
    public static void main(String[] args) {
        DataSource dataSource = createDataSource(); // kreiramo datasource sa dolje definiranim podacima

        try (Connection connection = dataSource.getConnection()){
            out.println("Uspjesno ste spojeni na bazu podataka");

            Statement stmt = connection.createStatement();
            @Language("SQL")
            String updateDrzava = "UPDATE Drzava SET Naziv='Hrvatska' WHERE IDDrzava=1";
            @Language("SQL")
            String insertDrzava = "INSERT INTO Drzava VALUES ('Mongolija')";
            @Language("SQL")
            String deleteDrzava = "DELETE FROM Drzava WHERE Naziv = 'Mongolija'";
            int rowAffectedDrzava1 = stmt.executeUpdate("UPDATE Drzava SET Naziv='Hrvatska' WHERE IDDrzava=1");
            int rowAffectedDrzava2 = stmt.executeUpdate("INSERT INTO Drzava VALUES ('Mongolija')");
            int rowAffectedDrzava3 = stmt.executeUpdate("DELETE FROM Drzava WHERE Naziv = 'Mongolija'");
            //ovo gore je izvrsavanje upit po upit
            //ako zelimo vise upita odjednom onda treba stmt1, stmt2, ...
            out.println("Drzave su uspjesno odradene !!!!");

            int rowAffectedGrad = stmt.executeUpdate("UPDATE Grad SET Naziv='Zagreb' WHERE IDGrad=1");
            out.println("Gradovi su uspjesno odradeni !!!!");

            //String drzava = "Madagaskar";
            //String sql = String.format("INSERT INTO Drzava (Naziv) VALUES ('%s')", drzava);
            //int IDDrzava = 5;
            //String sql1 = String.format("INSERT INTO Drzava (Naziv) VALUES ('%d')", IDDrzava);

            // dohvacanje svih drzava
            ResultSet rs = stmt.executeQuery("SELECT IDDrzava, Naziv FROM Drzava");
            while (rs.next()) { // %d %s znaci da ide prvo Integer pa String
                System.out.printf("%d %s\n", rs.getInt("IDDrzava"), rs.getString("Naziv"));
            }
            rs.close();
            stmt.close();

        }

        catch (SQLException e) {
            err.println("Greska prilikom spajanja na bazu podataka");
            e.printStackTrace();
        }

    } // end main

    // ovo ispod je metoda s podacima za spajanje na bazu podataka AdventureWorksOBP
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

} // end class