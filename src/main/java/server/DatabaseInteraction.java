package server;

import java.sql.*;
import java.util.Properties;

public class DatabaseInteraction {

    private Connection conn;

    public DatabaseInteraction() {

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
            int points = rs.getInt("total_points");
            a = new Account(name, email, password, points);

            rs.close();
            st.close();

            return a;

        } catch(Exception e) {

            System.out.println(e);
        }

        return null;
    }

}
