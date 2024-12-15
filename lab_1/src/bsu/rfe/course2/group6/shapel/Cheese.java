package bsu.rfe.course2.group6.shapel;

public class Cheese extends Food {
    private double calories = 0;

    public Cheese(double calories) {
        super("Сыр");
        this.calories = calories;
    }

    public Cheese() {
        super("Сыр");
    }

    @Override
    public String toString() {
        return super.toString() + "' с калорийностью " + calculate_calories() + " ";
    }

    public void consume() {
        System.out.println(this + " съеден");
    }

    @Override
    public double calculate_calories() {
        return calories;
    }

    public double get_calories() {
        return calories;
    }

    public void set_calories(double calories) {
        this.calories = calories;
    }
}
