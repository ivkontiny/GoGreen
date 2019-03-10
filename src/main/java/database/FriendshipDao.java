package database;

import pojos.Friendship;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FriendshipDao extends Dao {

    public FriendshipDao() {super();}

    private AccountDao ad = new AccountDao();
    public boolean acceptRequest(Friendship friendship)
    {
        try {
            if (!requestExists(friendship)) return false;
            String query = "UPDATE friendship SET accepted = true WHERE " +
                    "sender = ?" +
                    "AND receiver = ?";
            PreparedStatement update = this.conn.prepareStatement(query);
            update.setString(1, friendship.getSender());
            update.setString(2, friendship.getReceiver());
            update.execute();
            update.close();
            return true;

        } catch(Exception e) {
            System.out.println(e);
            return false;
        }


    }

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

            ArrayList<Friendship> friendships = getFriendships(friendship.getSender());
            for(Friendship search: friendships)
            {
                if(search.getReceiver().equals(friendship.getReceiver()) ||
                search.getSender().equals(friendship.getReceiver())) return true;
            }
            return false;

        } catch (Exception e) {

            System.out.println(e);
            return false;
        }
    }
    public void removeFriendship(Friendship friendship)
    {
        try{
            String query = "DELETE FROM friendship WHERE (receiver = ? AND sender = ?) " +
                    "OR (receiver = ? AND sender = ?)";

            if(!friendshipExists(friendship)) return;
            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1, friendship.getSender());
            st.setString(2, friendship.getReceiver());
            st.setString(3, friendship.getReceiver());
            st.setString(4, friendship.getSender());
            st.execute();
            st.close();

        } catch (Exception e){
            return;
        }
    }
    public boolean requestExists(Friendship friendship)
    {
        try {

            ArrayList<Friendship> friendships = getFriendships(friendship.getSender());
            for(Friendship search: friendships)
            {
                if(search.getReceiver().equals(friendship.getReceiver())) return true;
            }
            return false;

        } catch (Exception e) {

            System.out.println(e);
            return false;
        }
    }
    public boolean sendRequest(Friendship friendship)
    {
            try {

                if(!ad.exists(friendship.getSender()) || !ad.exists(friendship.getReceiver())) return false;
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
