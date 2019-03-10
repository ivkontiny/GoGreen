package database;

import pojos.Friendship;
import pojos.Leaderboard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LeaderboardDao extends Dao {


    public LeaderboardDao() { super(); }

    private FriendshipDao fd = new FriendshipDao();
    private AccountDao ad = new AccountDao();

    public int getPoints(String username)
    {
        try {
            if(!ad.exists(username)) return 0;
            String query = "SELECT * FROM account WHERE username = ? ";
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1,username);
            ResultSet rs = st.executeQuery();
            int result = 0;
            if(rs.next())
            {
                result = rs.getInt("total_points");;
            }
            st.close();
            return result;

        } catch(Exception e){
            System.out.println(e);
            return 0;
        }
    }

    public void addPoints(int toadd, String username) {
        try {
            String query = "UPDATE account SET total_points= total_points + ? WHERE username= ? ";
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, toadd);
            st.setString(2,username);
            st.executeQuery();
            st.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public Leaderboard getLeaderboard(String username) {
        try {
            ArrayList<String> usernames = new ArrayList<>();
            ArrayList<Integer> total_points = new ArrayList<>();
            ArrayList<Friendship> friendships = fd.getFriendships(username);
            if(!ad.exists(username)) return new Leaderboard(usernames,total_points);
            usernames.add(username);
            total_points.add(getPoints(username));
            for(Friendship search : friendships)
            {
                if(search.getStatus())
                {
                    if(username.equals(search.getReceiver()))
                    {
                        usernames.add(search.getSender());
                        total_points.add(getPoints(search.getSender()));
                    }
                    else
                    {
                        usernames.add(search.getReceiver());
                        total_points.add(getPoints(search.getReceiver()));
                    }
                }
            }
            Leaderboard newleaderboard = new Leaderboard(usernames, total_points);
            return newleaderboard;

        } catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
