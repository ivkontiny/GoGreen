package pojos;

public class DefaultValue {
    private String description;
    private Category category;
    private String unit;
    private int points;
    private double consumption;
    
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
}
