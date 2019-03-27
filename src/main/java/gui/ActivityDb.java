package gui;

import client.ConnectActivity;
import pojos.Category;
import pojos.DefaultValue;

import java.util.ArrayList;

public class ActivityDb implements Runnable {


    public static class Energy {
        static ArrayList<String> descriptions = new ArrayList<>();
        static ArrayList<Integer> points = new ArrayList<>();
    }
    public static class Food {
        static ArrayList<String> descriptions = new ArrayList<>();
        static ArrayList<Integer> points = new ArrayList<>();
    }
    public  static class  Transportation {
        static ArrayList<String> descriptions = new ArrayList<>();
        static ArrayList<Integer> points = new ArrayList<>();
    }

    /**
     * Initializing sample points for meals.
     */
    public static void initialize() {
        ArrayList<String> desc = ConnectActivity.getFood(Category.food);
        for(String description : desc)
        {
            Food.descriptions.add(description);
            DefaultValue dv = ConnectActivity.getConsumption(description);
            Food.points.add(dv.getPoints());
        }
        Thread energy = new Thread(new ActivityDb());
        energy.start();
        desc = ConnectActivity.getFood(Category.energy);
        for(String description : desc)
        {
            Energy.descriptions.add(description);
            DefaultValue dv = ConnectActivity.getConsumption(description);
            Energy.points.add(dv.getPoints());
        }
    }
    public void run()
    {
        ArrayList<String> desc = ConnectActivity.getFood(Category.transportation);
        for(String description : desc)
        {
            Transportation.descriptions.add(description);
            DefaultValue dv = ConnectActivity.getConsumption(description);
            Transportation.points.add(dv.getPoints());
        }
    }
}
