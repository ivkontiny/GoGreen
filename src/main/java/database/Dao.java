package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class Dao {

    public Connection conn;

    /**
     * Makes the connection to the database.
     */
    public Dao() {
        try {
            String url = "jdbc:postgresql://142.93.230.132/gogreen";

            Properties props = new Properties();
            props.setProperty("user", "gogreen");
            props.setProperty("password", "gogreen123");

            this.conn = DriverManager.getConnection(url, props);

        } catch (SQLException e) {

            System.out.println(e);
        }
    }


}
