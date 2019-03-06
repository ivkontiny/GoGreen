package Services;

import database.Dao;
import pojos.Account;

public class LoginService {

    Dao db = new Dao();

    public boolean checkLogin(String username, String password) {
        String expPass = db.getAccount(username).getPassword();
        return (password.equals(expPass));
    }
}
