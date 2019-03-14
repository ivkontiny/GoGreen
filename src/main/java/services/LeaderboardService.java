package services;

import database.LeaderboardDao;
import pojos.Leaderboard;

import java.sql.SQLException;

public class LeaderboardService {

    private LeaderboardDao ld = new LeaderboardDao();

    public Leaderboard getLeaderboard(String username) {
        try {
            return ld.getLeaderboard(username);
        } catch (SQLException e) {
            return null;
        }
    }

    public void addPoints(int toadd,String username) {
        try {
            ld.addPoints(toadd,username);
        } catch (SQLException e) {
            return;
        }
    }

    public void setDb(LeaderboardDao db)
    {
        this.ld = db;
    }

    public LeaderboardDao getDb() {
        return this.ld;
    }

}
