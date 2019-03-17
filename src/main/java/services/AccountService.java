package services;

import database.AccountDao;
import pojos.Account;

import java.sql.SQLException;

public class AccountService {

    private AccountDao db = new AccountDao();


    /**
     * Checks whether the inputted username and password are valid.
     *
     * @param username the inputted username
     * @param password the inputted password
     * @return true if the log in was successful, false otherwise
     */
    public boolean checkLogin(String username, String password) {
        try {
            if (db.exists(username)) {
                String expPass = db.getAccount(username).getPassword();
                return password.equals(expPass);
            }
            return false;
        } catch (SQLException e) {
            return false;
        }
    }


    /**
     * Creates an account.
     *
     * @param acc the account to be created
     * @return true if the account was successfully created, false otherwise
     */
    public boolean createAccount(Account acc) {
        try {
            if (db.exists(acc.getUsername())) {
                return false;
            }
            return db.createAccount(acc);
        } catch (SQLException e) {
            return false;
        }
    }


    /**
     * Checks whether a user exists.
     *
     * @param user the user who is checked to exist
     * @return true if the user exists, false otherwise
     */
    public boolean userExists(String user) {
        try {
            return db.exists(user);
        } catch (SQLException e) {
            return false;
        }
    }


    public void setDb(AccountDao db) {
        this.db = db;
    }

    /**
     * Returns the account information of a user.
     * @param account the account we are looking for
     * @return an Account object containing the necessary information
     */
    public Account getAccount(String account) {
        try {
            return db.getAccount(account);
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * Update the points of a user.
     * @param user the user whose points need to be updated
     * @param points the number of points to be added
     * @return true if the process was successful, false otherwise
     */
    public boolean updatePoints(String user, int points) {
        try {
            return db.updatePoints(user, points);
        } catch (SQLException e) {
            return false;
        }
    }
}


