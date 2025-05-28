package J26EvidencijaPolaznikaHash;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class DrzavaMain {
    public static void main(String[] args) {
        ArrayList<Drzava> drzave = new ArrayList<>();
        drzave.add(new Drzava("Pakistan"));
        drzave.add(new Drzava("Japan"));
        drzave.add(new Drzava("Kina"));
        drzave.add(new Drzava("Kanada"));
        drzave.add(new Drzava("Rusija"));
        drzave.add(new Drzava("Maroko"));
        drzave.add(new Drzava("Malta"));
        drzave.add(new Drzava("Finska"));
        drzave.add(new Drzava("Portugal"));
        drzave.add(new Drzava("Albanija"));

        //**************************************************************************

//        DataSource dataSource = createDataSource();
//        try (Connection connection = dataSource.getConnection()) {
//            System.out.println("Uspješno ste spojeni na bazu podataka");
//
//            PreparedStatement stmt = connection.prepareStatement("INSERT INTO Drzava (Naziv) VALUES (?)");
//
//            for (Drzava drzava : drzave) {
//                stmt.setString(1, drzava.getImeDrzave());
//                stmt.executeUpdate();
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        //**************************************************************************

        izbrisiDrzavu();

    } // end main

    public static void izbrisiDrzavu () {
        try(Connection connection = createDataSource().getConnection()) {
            System.out.println("Uspješno ste spojeni na bazu podataka");

            CallableStatement cs = connection.prepareCall("{call IzbrisiDrzavu}");
            cs.executeUpdate();

            //return cs.getString(2);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }
    }

    private static DataSource createDataSource() {
        SQLServerDataSource ds = new SQLServerDataSource();
        ds.setServerName("localhost");
        ds.setDatabaseName("AdventureWorksOBP");
        ds.setUser("sa");
        ds.setPassword("SQL");
        ds.setEncrypt(false);
        return ds;
    }
}