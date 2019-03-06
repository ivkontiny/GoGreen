package pojos;

import java.sql.Date;

public class Activity {

    String description;
    Category category;
    int points;
    Date date;
    String username;

    public Activity(String description, Category category, int points, Date date, String username) {
        this.description = description;
        this.category = category;
        this.points = points;
        this.date = date;
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
