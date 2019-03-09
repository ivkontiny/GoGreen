package database;

import pojos.Activity;
import pojos.Category;
import pojos.Friendship;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FriendshipDao extends Dao {

    public FriendshipDao() {super();}

    public ArrayList<Friendship> getFriendships(String username)
    {
        try {
            String query = "SELECT * FROM friendship WHERE sender = ?" +
                    "OR receiver = ?";
            ArrayList<Friendship> friendships = new ArrayList<>();
            PreparedStatement request = this.conn.prepareStatement(query);
            request.setString(1, username);
            request.setString(2, username);
            ResultSet rs = request.executeQuery();
            while(rs.next())
            {
                Friendship addfriendship = new Friendship();
                addfriendship.setSender(rs.getString("sender"));
                addfriendship.setReceiver(rs.getString("receiver"));
                addfriendship.setStatus(rs.getBoolean("accepted"));
                friendships.add(addfriendship);
            }
            return friendships;

        } catch (Exception e) {

            System.out.println(e);
            return new ArrayList<Friendship>();
        }
    }

    public boolean friendshipExists(Friendship friendship)
    {
        try {
            String query = "SELECT * FROM friendship WHERE (sender = ? AND receiver = ? )" +
                    "OR (sender = ? AND receiver = ?)";
            PreparedStatement request = this.conn.prepareStatement(query);
            request.setString(1, friendship.getSender());
            request.setString(2, friendship.getReceiver());
            request.setString(3, friendship.getReceiver());
            request.setString(4, friendship.getSender());
            ResultSet rs = request.executeQuery();
            //System.out.println(rs.getString(1));
            boolean result = false;
            if(rs.next())result = true;
            rs.close();
            request.close();
            return result;

        } catch (Exception e) {

            System.out.println(e);
            return false;
        }
    }

    public boolean sendRequest(Friendship friendship)
    {
            try {
                if(friendshipExists(friendship)) return false;

                String query = "INSERT INTO friendship (sender, receiver, accepted)" +
                        " VALUES  (?, ?, ?)";

                PreparedStatement st = this.conn.prepareStatement(query);
                st.setString(1, friendship.getSender());
                st.setString(2, friendship.getReceiver());
                st.setBoolean(3, false);

                st.execute();
                st.close();
                return true;

            } catch (Exception e) {

                System.out.println("we got into dao");
                System.out.println(e);
                return false;
            }
        }
}
