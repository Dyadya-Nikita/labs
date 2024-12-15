package bsu.rfe.course2.group6.shapel;

public class Apple extends Food implements Nutritious{
    private String size = null;
    private double calories = 0;

    public Apple(String size, double calories) {
        super("Яблоко");
        this.size=size;
        this.calories=calories;
    }

    public Apple(String size) {
        super("Яблоко");
        this.size=size;
    }

    public void set_size(String size) {
        this.size=size;
    }

    public String get_size() {
        return size;
    }

    public void set_calories(double calories) {
        this.calories=calories;
    }

    public double get_calories() {
        return calories;
    }

    @Override
    public void consume() {
        System.out.println(this + " съедено");
    }

    @Override
    public String toString() {
        return super.toString() + " размера '" + size.toUpperCase()
                + "' с калорийностью " + calculate_calories() + " ";
    }

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj)) {
            if(!(obj instanceof Apple)) return false;
            return size.equals(((Apple)obj).size);
        } else
            return false;
    }

    @Override
    public double calculate_calories() {
        return calories;
    }
}
