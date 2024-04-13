package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {
    private static Connection mysqlconfig;

    public static Connection configDB() throws SQLException {
        try {
            String url = "jdbc:mysql://localhost:3307/nextprev";
            String user = "root";
            String pass = "";
            // Use com.mysql.cj.jdbc.Driver instead of com.mysql.jdbc.Driver
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            mysqlconfig = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.err.println("Koneksi gagal" + e.getMessage());
        }
        return mysqlconfig;
    }
}