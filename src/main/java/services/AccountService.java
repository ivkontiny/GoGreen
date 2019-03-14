package services;

import database.AccountDao;
import pojos.Account;

public class AccountService {

    private AccountDao db = new AccountDao();


    /**
     * Checks whether the inputted username and password are valid.
     * @param username the inputted username
     * @param password the inputted password
     * @return true if the log in was successful, false otherwise
     */
    public boolean checkLogin(String username, String password) {
        if (db.exists(username)) {
            String expPass = db.getAccount(username).getPassword();
            return password.equals(expPass);
        }
        return false;
    }


    /**
     * Creates an account.
     * @param acc the account to be created
     * @return true if the account was successfully created, false otherwise
     */
    public boolean createAccount(Account acc) {
        if (db.exists(acc.getUsername())) {
            return false;
        }
        return db.createAccount(acc);
    }


    /**
     * Checks whether a user exists.
     * @param user the user who is checked to exist
     * @return true if the user exists, false otherwise
     */
    public boolean userExists(String user) {
        return db.exists(user);
    }


    public void setDb(AccountDao db) {
        this.db = db;
    }
}


