package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import util.ConfigReader;

public abstract class Dao {

    public static String url;
	
    public Connection conn;


    /**
     * Makes the connection to the database.
     */
    public Dao() {
        ConfigReader reader = new ConfigReader("src/main/java/config.xml");
        this.url = reader.getUrl();
        try {
            Properties props = new Properties();
            props.setProperty("user", reader.getUsername());
            props.setProperty("password", reader.getPassword());

            this.conn = DriverManager.getConnection(this.url, props);

        } catch (SQLException e) {

            System.out.println(e);
        }
    }

    /**
     * Changes the database the Dao connects to.
     * @param url the path to the database
     */
    public void changeDatabase(String url) {
        try {
            this.url = "jdbc:postgresql://142.93.230.132/" + url;
            //this.url = URL;
            Properties props = new Properties();
            props.setProperty("user", "gogreen");
            props.setProperty("password", "gogreen123");
            this.conn = DriverManager.getConnection(this.url, props);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

}
