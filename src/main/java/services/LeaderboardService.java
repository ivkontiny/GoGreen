package services;

import database.LeaderboardDao;
import pojos.Leaderboard;

public class LeaderboardService {

    LeaderboardDao db = new LeaderboardDao();

    public Leaderboard getLeaderboard(String username)
    {
        return db.getLeaderboard(username);
    }
    public LeaderboardDao getDb()
    {
        return this.db;
    }

}
