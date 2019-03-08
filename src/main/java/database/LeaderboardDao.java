package database;

import pojos.Leaderboard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LeaderboardDao extends Dao {

    public void addPoints(int toadd, String username) {
        try {
            String query = "SELECT total_points FROM account WHERE username=?";
            PreparedStatement st1 = this.conn.prepareStatement(query);
            st1.setString(1, username);
            ResultSet rs = st1.executeQuery();
            if(rs.next()) {
                int plus = rs.getInt("total_points");
                String query2 = "Update Account SET total_points=? WHERE username=?";
                PreparedStatement st = this.conn.prepareStatement(query2);
                st.setString(2, username);
                st.setInt(1, toadd + plus);
                st.executeQuery();
            }
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public Leaderboard getLeaderboard(String username) {
        try {
            ArrayList<String> usernames = new ArrayList<>();
            ArrayList<Integer> total_points = new ArrayList<>();
            String query = "SELECT * From account order BY total_points DESC";
            PreparedStatement st = this.conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                String user = rs.getString("username");
                int points = rs.getInt("total_points");
                usernames.add(user);
                total_points.add(points);
            }
            Leaderboard newleaderboard = new Leaderboard(usernames, total_points);
            return newleaderboard;
        } catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
