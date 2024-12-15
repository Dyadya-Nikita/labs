package bsu.rfe.course2.group6.shapel;

import javax.swing.table.AbstractTableModel;

@SuppressWarnings("serial")
public class GornerTableModel extends AbstractTableModel {
    private Double[] coefficients;
    private Double from;
    private Double to;
    private Double step;

    public GornerTableModel(Double from, Double to, Double step, Double[] coefficients) {
        this.from = from;
        this.to = to;
        this.step = step;
        this.coefficients = coefficients;
    }

    public Double getFrom() {
        return from;
    }

    public Double getTo() {
        return to;
    }

    public Double getStep() {
        return step;
    }

    public int getColumnCount() {
        return 3;
    }

    public int getRowCount() {
        return (int) Math.ceil((to - from) / step) + 1;
    }

    public Object getValueAt(int row, int col) {
        double x = from + step * row;
        if (col == 0) {
            return roundToTwoDecimalPlaces(x);
        } else {
            double result = 0.0;
            for (int i = 0; i < coefficients.length; ++i) {
                result += coefficients[i] * Math.pow(x, coefficients.length - i - 1);
            }

            // Округляем результат
            double roundedResult = roundToTwoDecimalPlaces(result);

            if (col == 1) {
                return roundedResult;
            } else {
                // Проверяем наличие двух пар подряд стоящих одинаковых цифр
                return hasTwoPairs(String.valueOf(roundedResult));
            }
        }
    }

    private double roundToTwoDecimalPlaces(double value) {
        return Math.round(value * 100.0) / 100.0;
    }

    private boolean hasTwoPairs(String value) {
        // Убираем возможный знак "-" или дробную точку
        String digits = value.replaceAll("[-.]", "");
        int pairCount = 0;

        // Проверяем последовательность цифр на наличие двух пар подряд
        for (int i = 0; i < digits.length() - 1; i++) {
            if (digits.charAt(i) == digits.charAt(i + 1)) {
                pairCount++;
                i++; // Пропускаем уже обработанную пару

                if (pairCount == 2) {
                    return true;
                }
            }
        }
        return false;
    }

    public String getColumnName(int col) {
        switch (col) {
            case 0:
                return "Значение X";
            case 1:
                return "Значение многочлена";
            default:
                return "Две пары";
        }
    }

    public Class<?> getColumnClass(int col) {
        return col == 2 ? Boolean.class : Double.class;
    }
}