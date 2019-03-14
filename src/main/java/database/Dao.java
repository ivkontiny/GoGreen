package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public abstract class Dao {

    public Connection conn;

    String url = "jdbc:postgresql://142.93.230.132/gogreen";

    /**
     * Makes the connection to the database.
     */
    public Dao() {
        try {

            Properties props = new Properties();
            props.setProperty("user", "gogreen");
            props.setProperty("password", "gogreen123");

            this.conn = DriverManager.getConnection(this.url, props);

        } catch (SQLException e) {

            System.out.println(e);
        }
    }

    /**
     *changes the database the Dao connects to
     * @param url
     */
    public void changeDatabase(String url) {
        url = "jdbc:postgresql://142.93.230.132/" + url;
        this.url = url;
    }
}
