package bsu.rfe.course2.group6.shapel;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class GornerTableCellRender implements TableCellRenderer {
    private JPanel panel = new JPanel();
    private JLabel label = new JLabel();
    private String needle = null;
    private DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance();

    public GornerTableCellRender() {
        formatter.setMaximumFractionDigits(5);
        formatter.setGroupingUsed(false);
        DecimalFormatSymbols dottedDouble = formatter.getDecimalFormatSymbols();
        dottedDouble.setDecimalSeparator('.');
        formatter.setDecimalFormatSymbols(dottedDouble);
        panel.add(label);
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        String formattedDouble = formatter.format(value);
        label.setText(formattedDouble);

        try {
            // Преобразуем значение в double
            double doubleValue = Double.parseDouble(formattedDouble);
            // Получаем целую часть числа
            int intPart = (int) Math.floor(doubleValue);

            // Проверяем четность целой части
            if (intPart % 2 == 1) {
                panel.setBackground(Color.GREEN); // Цвет для четных чисел
            } else {
                panel.setBackground(Color.YELLOW); // Цвет для нечетных чисел
            }
        } catch (NumberFormatException e) {
            // Если значение не удалось преобразовать в число, оставляем белый фон
            panel.setBackground(Color.WHITE);
        }

        return panel;
    }

    public void setNeedle(String needle) {
        this.needle = needle;
    }
}
