package pojos;

public class DefaultValue {
    private String description;
    private Category category;
    private String unit;
    private int points;
    private double consumption;

    public DefaultValue() {

    }
    /**
     * Constructs a DefaultValue.
     * @param desc Description of the activity
     * @param cat  The category of the activity
     * @param unit The unit in which the value of the activity is meassured
     * @param points The amount of points the activity gives
     * @param cons The amount thats consumed
     */
    public DefaultValue(String desc, Category cat, String unit, int points, double cons) {
        this.description = desc;
        this.category = cat;
        this.unit = unit;
        this.points = points;
        this.consumption = cons;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category cat) {
        this.category = cat;
    }


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }


    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }


    public double getConsumption() {
        return consumption;
    }

    public void setConsumption(double cons) {
        this.consumption = cons;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DefaultValue) {
            DefaultValue that = (DefaultValue) obj;

            if (description.equals(that.description)
                && category == that.category
                && unit.equals(that.unit)
                && points == that.points
                && consumption == that.consumption) {
                return true;
            }
        }

        return false;
    }

    public static double converter(int points) {
        System.out.println(((double)points*0.0001)/5.0);
        return ((double)points*0.0001)/5.0;
    }


    public static int kwhToPoints(int kwh, int pts250Kwh) {
        double points = ((double) kwh / 250.0) * pts250Kwh;
        int realPoints = (int) points;

        if (points - (double) realPoints >= 0.5) {
            ++ realPoints;
        }

        return realPoints;
    }
}
