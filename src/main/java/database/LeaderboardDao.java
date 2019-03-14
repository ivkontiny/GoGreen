package database;

import pojos.Friendship;
import pojos.Leaderboard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LeaderboardDao extends Dao {


    public LeaderboardDao() {
        super();
    }

    private FriendshipDao fd = new FriendshipDao();
    private AccountDao ad = new AccountDao();

    /**
     * Reset the points of a user.
     * @param username the user whose points are going to be reset
     */
    public void resetPoints(String username) {
        try {
            if (!ad.exists(username)) {
                return;
            }

            String query = "UPDATE account SET total_points = 0 WHERE username = ? ";
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1,username);
            st.execute();
            st.close();
            return;
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Get the points of a user.
     * @param username the username of the user whose points we want
     * @return the points of the user
     */
    public int getPoints(String username) {
        try {
            if (!ad.exists(username)) {
                return 0;
            }

            String query = "SELECT * FROM account WHERE username = ? ";
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1,username);
            ResultSet rs = st.executeQuery();
            int result = 0;
            if (rs.next()) {
                result = rs.getInt("total_points");
            }

            st.close();
            return result;

        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
    }

    /**
     * Adds points to a user.
     * @param toadd the points to be added
     * @param username the user we want to add points to
     */
    public void addPoints(int toadd, String username) {
        try {
            String query = "UPDATE account SET total_points= total_points + ? WHERE username= ? ";
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setInt(1, toadd);
            st.setString(2,username);
            st.executeQuery();
            st.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    /**
     * Gets the leader board of the user (himself and all of his friends).
     * @param username the user we want to create a leader board for
     * @return the leader board of the user
     */
    public Leaderboard getLeaderboard(String username) {
        ArrayList<String> usernames = new ArrayList<>();
        ArrayList<Integer> totalPoints = new ArrayList<>();
        ArrayList<Friendship> friendships = fd.getFriendships(username);
        int i = 1;
        if (!ad.exists(username)) {
            return new Leaderboard(usernames,totalPoints);
        }

        usernames.add(username);
        totalPoints.add(getPoints(username));
        for (Friendship search : friendships) {
            if (search.getStatus()) {
                if (username.equals(search.getReceiver())) {
                	if(totalPoints.get(i -1) != null && totalPoints.get(i - 1) > getPoints(search.getSender())) {
                        usernames.add(search.getSender());
                        totalPoints.add(getPoints(search.getSender()));
                        i++;
                	}
                	else {
                		for(int j = i - 1; j > 0; j--) {
                			if(totalPoints.get(j - 1) >= getPoints(search.getSender())) {
                                usernames.add(j, search.getSender());
                                totalPoints.add(j, getPoints(search.getSender()));;
                                j = 0;
                			}
                			else {
                				usernames.add(j - 1, usernames.get(j));
                				totalPoints.add(j - 1, totalPoints.get(j));
                			}
                		}
                    usernames.add(search.getSender());
                    totalPoints.add(getPoints(search.getSender()));
                	}
                } else {
                	if(totalPoints.get(i -1) != null && totalPoints.get(i - 1) > getPoints(search.getReceiver())) {
                        usernames.add(search.getReceiver());
                        totalPoints.add(getPoints(search.getReceiver()));
                        i++;
                	}
                	else {
                		for(int j = i - 1; j > 0; j--) {
                			if(totalPoints.get(j - 1) >= getPoints(search.getReceiver())) {
                                usernames.add(j, search.getReceiver());
                                totalPoints.add(j, getPoints(search.getReceiver()));;
                                j = 0;
                			}
                			else {
                				usernames.add(j - 1, usernames.get(j));
                				totalPoints.add(j - 1, totalPoints.get(j));
                			}
                		}
                }
            }
        }
        }
        Leaderboard newleaderboard = new Leaderboard(usernames, totalPoints);
        return newleaderboard;
    }
}

