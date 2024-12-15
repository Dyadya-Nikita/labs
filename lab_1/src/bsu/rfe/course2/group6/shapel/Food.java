package bsu.rfe.course2.group6.shapel;

import bsu.rfe.course2.group6.shapel.Nutritious;

import java.util.ArrayList;

public abstract class Food implements Consumable, Nutritious {
    String name = null;

    public Food(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Food)) return false;
        if (name == null || ((Food) obj).name == null) return false;
        return name.equals(((Food) obj).name);
    }

    public String get_name() {
        return name;
    }

    public void set_name(String name) {
        this.name = name;
    }

    public static int count_food(ArrayList<Food> breakfast, Food food_item) {
        int count = 0;
        for(Food food : breakfast) {
            if(food.equals(food_item)) {
                count++;
            }
        }
        return count;
    }
}