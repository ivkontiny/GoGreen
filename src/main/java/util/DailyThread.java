package util;

import client.ConnectAccount;


public class DailyThread extends Thread {

    /**
     * Runs daily thead, updating solarPoints and resets heating.
     */
    public void run() {
        while (true) {
            AddSolarPoints.addPoints();
            ConnectAccount.resetHeating();

            try {
                this.sleep( 86400000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }


}
