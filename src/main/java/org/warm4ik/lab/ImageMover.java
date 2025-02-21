package org.warm4ik.lab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class ImageMover extends JFrame {
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int IMAGE_SIZE = 200;

    private int x = 0;
    private int y = 0;
    private int count = 0;
    private boolean isMoving = false;

    private final Timer timer;
    private final JLabel countLabel;
    private Image image;

    private int dx = 5;
    private int dy = 5;

    public ImageMover() {
        setTitle("Бегающий смайлик");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        URL imageUrl = getClass().getResource("/image.jpg");
        if (imageUrl != null) {
            image = Toolkit.getDefaultToolkit().getImage(imageUrl);
            //стандартное средство для работы с графическими ресурсами
        } else {
            System.err.println("Изображение не найдено в ресурсах.");
        }

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (image != null) {
                    g.drawImage(image, x, y, IMAGE_SIZE, IMAGE_SIZE, this);
                }
            }
        };
        panel.setLayout(null);
        setContentPane(panel);

        JButton startButton = new JButton("Старт");
        startButton.setBounds(275, 10, 100, 30);
        panel.add(startButton);

        countLabel = new JLabel("Счётчик: 0");
        countLabel.setBounds(500, 10, 100, 30);
        panel.add(countLabel);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isMoving) {
                    timer.stop();
                } else {
                    timer.start();
                }
                isMoving = !isMoving;
            }
        });

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getX() >= x && e.getX() <= x + IMAGE_SIZE &&
                        e.getY() >= y && e.getY() <= y + IMAGE_SIZE) {
                    count++;
                    countLabel.setText("Счётчик: " + count);
                    if (isMoving) {
                        timer.stop();
                        isMoving = false;
                    }
                }
            }
        });

        timer = new Timer(5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x += dx;
                y += dy;

                if (x > WINDOW_WIDTH - IMAGE_SIZE || x < 0) {
                    dx = -dx;
                }

                if (y > WINDOW_HEIGHT - IMAGE_SIZE || y < 0) {
                    dy = -dy;
                }

                panel.repaint();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ImageMover().setVisible(true);
            }
        });
    }
}
