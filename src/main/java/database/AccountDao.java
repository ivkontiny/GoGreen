package database;

import pojos.Account;

import java.sql.*;

public class AccountDao extends Dao {

    public AccountDao() {
        super();
    }

    public Account getAccount(String username) {
        try {
            String query = "SELECT * FROM account WHERE username=?";

            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            rs.next();
            String name = rs.getString("username");
            String email = rs.getString("email");
            String password = rs.getString("password");
            String firstname = rs.getString("first_name");
            String lastname = rs.getString("last_name");
            int points = rs.getInt("total_points");
            Account a = new Account(name, email, password, firstname, lastname);
            a.setPoints(points);

            rs.close();
            st.close();

            return a;

        } catch(Exception e) {

            System.out.println(e);
            return null;
        }

    }

    public boolean createAccount(Account acc) {
        try {
            String query = "INSERT INTO account (username, email, password," +
                    " total_points, first_name, last_name)" +
                    " VALUES  (?, ?, ?, ?, ?, ?)";

            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1, acc.getUsername());
            st.setString(2, acc.getMail());
            st.setString(3, acc.getPassword());
            st.setInt(4, acc.getPoints());
            st.setString(5, acc.getFirstName());
            st.setString(6, acc.getLastName());
            st.execute();

            st.close();

            return true;

        } catch (Exception e) {
            System.out.println("we got into dao");
            System.out.println(e);
            return false;
        }

    }
}
