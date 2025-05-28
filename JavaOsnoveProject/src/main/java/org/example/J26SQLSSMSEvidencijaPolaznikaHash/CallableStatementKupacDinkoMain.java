package J26EvidencijaPolaznikaHash;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class CallableStatementKupacDinkoMain {
    public static void main(String[] args) {
        DataSource dataSource = createDataSource();
        String imeKupca = dohvatiKupca(4);
        System.out.println("Ime kupca: " + imeKupca);
    }

    public static String dohvatiKupca(int id) {
        try(Connection connection = createDataSource().getConnection()) {
            System.out.println("Uspješno ste spojeni na bazu podataka");

            CallableStatement cs = connection.prepareCall("{call DohvatiImeKupca(?, ?)}");
            cs.setInt(1, id);
            cs.registerOutParameter(2, Types.NVARCHAR);
            cs.executeUpdate();

            return cs.getString(2);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

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
