package org.warm4ik.lab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonHoverApp extends JFrame {
    public ButtonHoverApp() {
        setTitle("Кнопка: приёл - ушёл");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton button1 = new JButton("Ушёл");
        add(button1);

        JButton button2 = new JButton("Ушёл");
        add(button2);


        button1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button1.setText("Пришёл");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button1.setText("Ушёл");
            }
        });

        button2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button2.setText("Пришёл");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button2.setText("Ушёл");
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ButtonHoverApp app = new ButtonHoverApp();
            app.setVisible(true);
        });
    }
}

