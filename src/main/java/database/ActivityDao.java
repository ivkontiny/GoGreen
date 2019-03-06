package database;

import pojos.Activity;
import pojos.Category;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ActivityDao extends Dao {

    public ActivityDao() {
        super();
    }

    public Activity getActivity(String description) {
        try {
            String query = "SELECT * FROM activity WHERE description = ?";
            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setString(1, description);
            ResultSet rs = ps.executeQuery();

            rs.next();
            Category cat = Category.valueOf(rs.getString(3));
            int points = rs.getInt(4);
            Date date = rs.getDate(5);
            String username = rs.getString(6);

            Activity a = new Activity(description, cat, points, date, username);
            return a;

        } catch (Exception e) {

            System.out.println(e);
            return null;
        }
    }

    public boolean createActivity(Activity act) {
        try {
            String query = "INSERT INTO activity (description, category, points, date, username) " +
                           "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement ps = this.conn.prepareStatement(query);
            ps.setString(1, act.getDescription());
            ps.setString(2, act.getCategory().name());
            ps.setInt(3, act.getPoints());
            ps.setDate(4, act.getDate());
            ps.setString(5, act.getUsername());

            ps.execute();
            return true;

        } catch (Exception e) {

            System.out.println(e);
            return false;
        }
    }
}
