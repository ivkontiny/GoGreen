package GUI;

import pojos.Category;

import java.util.ArrayList;

public class ActivityDB {

    public static class Food {
        static ArrayList<String> descriptions = new ArrayList<>();
        static ArrayList<Integer> points = new ArrayList<>();
        static Category food;

    }

    /**
     * Initializing sample points for meals.
     */
    public static void initialize() {
        Food.descriptions.add("Vegetarian meal");
        Food.descriptions.add("Biological meal");
        Food.descriptions.add("Protein meal");
        Food.points.add(100);
        Food.points.add(50);
        Food.points.add(25);
    }
}
