package util;

import client.ConnectAccount;

public class DailyThread extends Thread {

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
