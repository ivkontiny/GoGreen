package database;

import pojos.Account;

import java.sql.*;
import java.util.HashMap;
import java.util.Properties;

public class Dao {

    private Connection conn;

    private static HashMap<String, Account> users ;

    static
    {
        users = new HashMap<>();
    }

    public Dao() {

        String url = "jdbc:postgresql://142.93.230.132/gogreen";

        try {
            Properties props = new Properties();
            props.setProperty("user", "gogreen");
            props.setProperty("password", "gogreen123");

            this.conn = DriverManager.getConnection(url, props);

        } catch(Exception e) {

            System.out.println(e);
        }
    }

    public Account getAccount(String username) {
        try {
            String query = "SELECT * FROM account where username=?";

            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            Account a = null;

            rs.next();
            String name = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String firstname = rs.getString("first_name");
            String lastname = rs.getString("last_name");
            int points = rs.getInt("total_points");
            a = new Account(name, email, password, firstname, lastname);
            a.setPoints(points);

            rs.close();
            st.close();

            return a;

        } catch(Exception e) {

            System.out.println(e);
        }

        return null;
    }

    public  static HashMap<String, Account> getAllUsers() {
        return users;
    }

    public static void putUser(String key, Account value) {
        users.put(key,value);
    }

    public static boolean userExists(String key) {
        return Dao.getAllUsers().containsKey(key);
    }

    public static void removeUser(String key) {
        users.remove(key);
    }

}
