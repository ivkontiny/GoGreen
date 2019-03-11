package database;

import pojos.Activity;
import pojos.Category;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityDao extends Dao {

    public ActivityDao() {
        super();
    }


    /**
     * Makes a query to the database for the activity described.
     * @param description the description of the activity
     * @return the activity details if it exists, null otherwise
     */
    public Activity getActivity(String description) {
        try {
            String query = "SELECT * FROM activity WHERE description = ?";
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setString(1, description);
            ResultSet rs = ps.executeQuery();

            Activity activity = null;
            if (rs.next()) {
                Category cat = Category.valueOf(rs.getString(3));
                int points = rs.getInt(4);
                Date date = rs.getDate(5);
                String username = rs.getString(6);
                activity = new Activity(description, cat, points, date, username);
            }
            return activity;

        } catch (SQLException e) {

            System.out.println(e);
            return null;
        }
    }

    /**
     * Creates an activity and adds it to the database.
     * @param act the activity to add
     * @return true if the insertion was successful, false otherwise
     */
    public boolean createActivity(Activity act) {
        try {
            String query = "INSERT INTO activity (description, category, points, date, username) "
                            + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setString(1, act.getDescription());
            ps.setString(2, act.getCategory().name());
            ps.setInt(3, act.getPoints());
            ps.setDate(4, act.getDate());
            ps.setString(5, act.getUsername());

            ps.execute();
            return true;

        } catch (SQLException e) {

            System.out.println(e);
            return false;
        }
    }

    /**
     * Removes an activity from the database.
     * @param act the activity to be removed
     */
    public void removeActivity(Activity act) {
        try {
            String query = "DELETE FROM activity WHERE username = ? AND description = ?";

            if (getActivity(act.getDescription()) == null) {
                return;
            }

            PreparedStatement st = this.conn.prepareStatement(query);
            st.setString(1, act.getUsername());
            st.setString(2, act.getDescription());
            st.execute();
            st.close();

        } catch (SQLException e) {
            return;
        }
    }
}
