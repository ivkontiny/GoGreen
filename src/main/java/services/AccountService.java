package services;

import database.AccountDao;
import pojos.Account;

public class AccountService {

    AccountDao db = new AccountDao();

    public boolean checkLogin(String username, String password) {
        if (db.exists(username)) {
            String expPass = db.getAccount(username).getPassword();
            return (password.equals(expPass));
        }
        return false;
    }

    public boolean createAccount(Account acc) {
        if (db.exists(acc.getUsername())) {
            return false;
        }
        return db.createAccount(acc);
    }
}
