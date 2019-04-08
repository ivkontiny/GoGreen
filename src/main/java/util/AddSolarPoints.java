package util;

import pojos.Account;
import pojos.DefaultValue;
import services.AccountService;
import services.DefaultValueService;

import java.util.ArrayList;

public class AddSolarPoints {

    /**
     * Adds points to account for having solarpanels.
     */
    public static void addPoints() {
        AccountService as = new AccountService();
        DefaultValueService dvs = new DefaultValueService();

        ArrayList<Account> all = as.getAccounts();
        //DefaultValue value = dvs.getDefaultValue("Power saved by solar panels");
        DefaultValue.initPts();

        for (Account acc : all) {
            as.updatePoints(acc.getUsername(), DefaultValue.kwhToPoints(acc.getSavedEnergy()));
        }
    }
}
