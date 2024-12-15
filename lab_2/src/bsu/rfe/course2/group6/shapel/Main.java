package bsu.rfe.course2.group6.shapel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Main extends JFrame {
    // Размеры окна приложения в виде констант
    private static final int WIDTH = 500;
    private static final int HEIGHT = 420;
    // Текстовые поля для считывания значений переменных,
    // как компоненты, совместно используемые в различных методах
    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;

    private Double mem1, mem2, mem3;
    private Double RES;

    // Текстовое поле для отображения результата,
    // как компонент, совместно используемый в различных методах
    private JTextField textFieldResult;

    // Группа радио-кнопок для обеспечения уникальности выделения в группе
    private ButtonGroup radioButtons = new ButtonGroup();

    // Контейнер для отображения радио-кнопок
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId = 1;

    // Формула №1 для расчета
    public Double calculate1(Double x, Double y, Double z) {
        this.mem1 = x;
        this.mem2 = y;
        this.mem3 = z;
        return ((Math.pow((Math.log(z) + Math.sin(Math.PI * Math.pow(z, 2))), (double) 1 / 4)) /
                (Math.pow((Math.pow(y, 2) + Math.pow(Math.E, Math.cos(x)) + Math.sin(y)), Math.sin(x))));
    }

    // Формула №2 для расчета
    public Double calculate2(Double x, Double y, Double z) {
        this.mem1 = x;
        this.mem2 = y;
        this.mem3 = z;
        return Math.pow(y, (double) 1 / 2) * (3 * Math.pow(z, x)) / Math.pow(1 + Math.pow(y, 3), (double) 1 / 2);
    }

    // Вспомогательный метод для добавления кнопок на панель
    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Main.this.formulaId = formulaId;

                rootPane.updateUI();
            }
        });
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    // Конструктор класса
    public Main() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();

        // Отцентрировать окно приложения на экране
        setLocation((kit.getScreenSize().width - WIDTH) / 2, (kit.getScreenSize().height - HEIGHT) / 2);
        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        // Создать область с полями ввода для X, Y, Z
        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());
        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());
        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(
                BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(100));
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());

        // Создать область для вывода результата
        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 10);
        textFieldResult.setMaximumSize(
                textFieldResult.getPreferredSize());
        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        // Создать область для кнопок
        final Double[] result = new Double[1];
        final Double[] ch = {0.0};

        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double x = Double.parseDouble(textFieldX.getText());
                    Double y = Double.parseDouble(textFieldY.getText());
                    Double z = Double.parseDouble(textFieldZ.getText());

                    if (formulaId == 1) {
                        result[0] = calculate1(x, y, z);
                    } else {
                        result[0] = calculate2(x, y, z);
                    }
                    textFieldResult.setText(result[0].toString());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Main.this,
                            "Ошибка в формате записи числа", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                } catch (ArithmeticException ex) {
                    JOptionPane.showMessageDialog(Main.this,
                            ex.getMessage(), "Ошибка вычисления",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton button1 = new JButton("ПЕРЕМЕННАЯ 1");
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                ch[0] = 1.0;
            }
        });
        JButton button2 = new JButton("ПЕРЕМЕННАЯ 2");
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                ch[0] = 2.0;
            }
        });
        JButton button3 = new JButton("ПЕРЕМЕННАЯ 3");
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                ch[0] = 3.0;
            }
        });
        JButton Mpl = new JButton("M+");
        Mpl.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (ch[0] == 1.0) {
                    mem1 = mem1 + result[0];
                    textFieldX.setText(String.valueOf(mem1));
                } else if (ch[0] == 2.0) {
                    mem2 = mem2 + result[0];
                    textFieldY.setText(String.valueOf(mem2));
                } else if (ch[0] == 3.0) {
                    mem3 = mem3 + result[0];
                    textFieldZ.setText(String.valueOf(mem3));
                }
            }
        });
        JButton MC = new JButton("MC");
        MC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if (ch[0] == 1.0) {
                    mem1 = 0.0;
                    textFieldX.setText(String.valueOf(mem1));
                } else if (ch[0] == 2.0) {
                    mem2 = 0.0;
                    textFieldY.setText(String.valueOf(mem2));
                } else if (ch[0] == 3.0) {
                    mem3 = 0.0;
                    textFieldZ.setText(String.valueOf(mem3));
                }
            }
        });

        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldX.setText("0");
                textFieldY.setText("0");
                textFieldZ.setText("0");
                textFieldResult.setText("0");
            }
        });

        Box hboxButtonsPerem = Box.createHorizontalBox();
        hboxButtonsPerem.add(Box.createHorizontalGlue());
        hboxButtonsPerem.add(button1);
        hboxButtonsPerem.add(Box.createHorizontalStrut(30));
        hboxButtonsPerem.add(button2);
        hboxButtonsPerem.add(Box.createHorizontalStrut(30));
        hboxButtonsPerem.add(button3);
        hboxButtonsPerem.add(Box.createHorizontalGlue());
        hboxButtonsPerem.setBorder(
                BorderFactory.createLineBorder(Color.GREEN));

        Box hboxButtonsM = Box.createHorizontalBox();
        hboxButtonsM.add(Box.createHorizontalGlue());
        hboxButtonsM.add(Mpl);
        hboxButtonsM.add(Box.createHorizontalStrut(30));
        hboxButtonsM.add(MC);
        hboxButtonsM.add(Box.createHorizontalGlue());
        hboxButtonsM.setBorder(
                BorderFactory.createLineBorder(Color.GREEN));

        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.setBorder(
                BorderFactory.createLineBorder(Color.RED));

        // Связать области воедино в компоновке BoxLayout
        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtonsPerem);
        contentBox.add(hboxButtonsM);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Main frame = new Main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
