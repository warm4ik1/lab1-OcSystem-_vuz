package org.warm4ik.lab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class Calculator extends JFrame {
    private final JTextField inputField;
    private final JTextField resultField;

    public Calculator() {
        setTitle("Калькулятор");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setResizable(false);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        JLabel inputLabel = new JLabel("Введите число:");
        inputPanel.add(inputLabel);

        inputField = new JTextField(10);
        inputPanel.add(inputField);
        add(inputPanel);

        JButton calculateButton = new JButton("Calculate");
        add(calculateButton);

        resultField = new JTextField();
        resultField.setEditable(false);
        add(resultField);

        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double number = Double.parseDouble(inputField.getText());

                    double result = number * number;

                    resultField.setText(String.valueOf(result));
                } catch (NumberFormatException ex) {
                    resultField.setText("Invalid input");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Calculator().setVisible(true);
            }
        });
    }
}