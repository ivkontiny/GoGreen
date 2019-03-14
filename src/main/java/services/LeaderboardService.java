package services;

import database.LeaderboardDao;
import pojos.Leaderboard;

import java.sql.SQLException;

public class LeaderboardService {

    private LeaderboardDao ld = new LeaderboardDao();

    public Leaderboard getLeaderboard(String username) throws SQLException {
        return ld.getLeaderboard(username);
    }

    public void addPoints(int toadd,String username) {
        ld.addPoints(toadd,username);
    }

    public void setDb(LeaderboardDao db)
    {
        this.ld = db;
    }

    public LeaderboardDao getDb() {
        return this.ld;
    }

}
