package database;

import pojos.Account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDao extends Dao {

    public AccountDao() {
        super();
    }


    /** Checks whether a user exists in the database.
     *
     * @param username the user to check for
     * @return true if the user exists, false otherwise
     */
    public boolean exists(String username) throws SQLException{
        if (this.getAccount(username) == null) {
            return false;
        }
        return true;
    }

    /**
     * Makes a query to the database for a certain account.
     * @param username the user whose account we want to know
     * @return the details of the account if it exists, null otherwise
     */
    public Account getAccount(String username) throws SQLException {

        String query = "SELECT * FROM account WHERE username=?";

            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            Account account = null;
            if (rs.next()) {
                String name = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String firstname = rs.getString("first_name");
                String lastname = rs.getString("last_name");
                int points = rs.getInt("total_points");
                account = new Account(name, email, password, firstname, lastname);
                account.setPoints(points);
            }
            rs.close();
            st.close();
            return account;

    }

    /**
     * Creates an account in the database.
     * @param acc the account to be added to the database
     * @return true if the adding was successful, false otherwise
     */
    public boolean createAccount(Account acc) throws SQLException {

            if (exists(acc.getUsername())) {
                return false;
            }

            String query = "INSERT INTO account (username, email, password,"
                    + " total_points, first_name, last_name)"
                    + " VALUES  (?, ?, ?, ?, ?, ?)";

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

    }

    /**
     * Deletes an account if it exists.
     * @param account the acount to be deleted from the database
     */
    public void deleteAccount(Account account) throws SQLException {

            String query = "DELETE FROM account WHERE username = ?";

            if (!exists(account.getUsername())) {
                return;
            }

            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1, account.getUsername());
            st.execute();
            st.close();
    }
}
