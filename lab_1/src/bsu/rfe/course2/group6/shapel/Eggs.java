package bsu.rfe.course2.group6.shapel;

public class Eggs extends Food implements Nutritious{
    private String number = null;
    private double calories = 0;

    public Eggs(String number, double calories) {
        super("Яйцо");
        this.number = number;
        this.calories = calories;
    }

    public Eggs(String number) {
        super("Яйцо");
        this.number = number;
    }

    public void set_number(String number) {
        this.number = number;
    }

    public String get_number() {
        return number;
    }

    private int convert_to_number(String number) {
        return switch (number.toLowerCase()) {
            case "одно" -> 1;
            case "два" -> 2;
            case "три" -> 3;
            default -> 0;
        };
    }

    public double get_calories() {
        return calories;
    }

    public void set_calories(double calories) {
        this.calories = calories;
    }

    @Override
    public void consume() {
        System.out.println(this + " съедено");
    }

    @Override
    public String toString() {
        return super.toString() + " в количестве '" + number.toUpperCase()
                + "' с калорийностью " + calculate_calories() + " ";
    }

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj)) {
            if(!(obj instanceof Eggs)) return false;
            return number.equals(((Eggs)obj).number);
        } else
            return false;
    }

    @Override
    public double calculate_calories() {
        return convert_to_number(number) * calories;
    }
}