package bsu.rfe.course2.group6.shapel;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Food> breakfast = new ArrayList<>();

        for (String arg : args) {
            String[] parts = arg.split("/");

            switch (parts[0]) {
                case "Eggs" -> {
                    try {
                        String[] par = parts[1].split(",");
                        if (par.length == 2) {
                            breakfast.add(new Eggs(par[0], Double.parseDouble(par[1])));
                        } else {
                            breakfast.add(new Eggs(parts[1]));
                        }
                        breakfast.getLast().consume();
                    } catch (NumberFormatException e) {
                        System.out.println("ошибка ввода");
                        continue;
                    }
                    break;
                }
                case "Apple" -> {
                    try {
                        String[] par = parts[1].split(",");
                        if (par.length == 2) {
                            breakfast.add(new Apple(par[0], Double.parseDouble(par[1])));
                        } else {
                            breakfast.add(new Apple(parts[1]));
                        }
                        breakfast.getLast().consume();
                    } catch (NumberFormatException e) {
                        System.out.println("ошибка ввода");
                        continue;
                    }
                    break;
                }
                case "Cheese" -> {
                    try {
                        if (parts.length == 2) {
                            breakfast.add(new Cheese(Double.parseDouble(parts[1])));
                        } else {
                            breakfast.add(new Cheese());
                        }
                        breakfast.getLast().consume();
                    } catch (NumberFormatException e) {
                        continue;
                    }
                    break;
                }
                case "-sort" -> {
                    breakfast.sort(new Food_comparator());
                    System.out.println("\nЗАВТРАК ОТСОРТИРОВАН:");
                    for (Food food : breakfast) {
                        System.out.println(food);
                    }
                    break;
                }
                case "-calories" -> {
                    double sum_calories = 0;
                    for (Food food : breakfast) {
                        sum_calories += ((Nutritious) food).calculate_calories();
                    }
                    System.out.println("\nОБЩАЯ КАЛОРИЙОНСТЬ: " + sum_calories + "\n");
                }
                default -> {
                    continue;
                }
            }
        }
    }
}

