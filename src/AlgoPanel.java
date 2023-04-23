import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

import java.util.concurrent.CountDownLatch;

public class AlgoPanel extends JPanel {

    public boolean runAlgo = false;
    private JButton[] buttons;
    private int x, y, width, height;
    private Color color;
    private Border border;
    private String algoType = null;

    public AlgoPanel(int x, int y, int width, int height, Color color, Border border) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.border = border;

        this.setBounds(x, y, width, height);
        this.setBackground(color);
        this.setBorder(border);
        this.setLayout(null);
    }

    public void setAlgoType(String algoType) {
        this.algoType = algoType;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    public void startAlgo(int[] array) {


        switch (this.algoType) {
            case "Bubble Sort":
                animateBubbleSort(array);
                break;
            case "Selection Sort":
                animateSelectionSort();
                break;
            case "Insertion Sort":
                animateInsertionSort();
                break;
        }
    }

    private void animateInsertionSort() {

    }

    private void animateSelectionSort() {
    }

    private void animateBubbleSort(int[] array) {
        paintArrayBoxes(array);

        new Thread(() -> {
            boolean swapped = true;
            int counter = 0;
            while (swapped) {
                counter++;
                swapped = false;
                for (int i = 0; i < array.length - 1; i++) {
                    if (!runAlgo)
                        return;
                    if (array[i + 1] < array[i]) {
                        try {
                            swapButtons(buttons[i], buttons[i + 1]);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
//                        changeColor(buttons[i], Color.RED);


                        // swap the elements
                        int temp = array[i + 1];
                        array[i + 1] = array[i];
                        array[i] = temp;

                        //swap the buttons
                        JButton tempButton = buttons[i + 1];
                        buttons[i + 1] = buttons[i];
                        buttons[i] = tempButton;

                        swapped = true;

                    }
                }
                buttons[buttons.length-counter].setBackground(new Color(93, 231, 30));
            }
        }).start();
    }

    private void swapButtons(JButton button, JButton button1) throws InterruptedException {
        button.setBackground(Color.RED);
        button1.setBackground(Color.RED);
        CountDownLatch latch = new CountDownLatch(2);
        System.out.println("starting");

        new Thread(() -> {
            Point bound = new Point(button.getX(), button.getY());
            for (int i = 0; i < 50; i++) {

                button.setBounds(bound.x, bound.y - i, 90, 90);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            bound = new Point(button.getX(), button.getY());
            for (int i = 0; i < 100; i++) {
                button.setBounds(bound.x + i, bound.y, 90, 90);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            bound = new Point(button.getX(), button.getY());
            for (int i = 0; i < 50; i++) {
                button.setBounds(bound.x , bound.y + i, 90, 90);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            latch.countDown();

        }).start();

        new Thread(() -> {
            Point bound = new Point(button1.getX(), button1.getY());
            for (int i = 0; i < 50; i++) {
                button1.setBounds(bound.x, bound.y + i, 90, 90);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            bound = new Point(button1.getX(), button1.getY());
            for (int i = 0; i < 100; i++) {
                button1.setBounds(bound.x - i, bound.y, 90, 90);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            bound = new Point(button1.getX(), button1.getY());

            for (int i = 0; i< 50; i++){
                button1.setBounds(bound.x, bound.y-i, 90, 90);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            latch.countDown();

        }).start();
    latch.await();
        System.out.println("end");



//        int radius = (button1.getX() - button.getX()) / 2;
//        Point mid = new Point((button1.getX() - button.getX()) / 2 + button.getX(), button1.getY());
//        JButton test = new JButton("awd");
//        test.setBounds(mid.x,mid.y, 5,5);
//        test.setVisible(true);
//        test.requestFocus();
//        this.add(test);
//        ArrayList<Point> pointArr = new ArrayList<>();
//        Point temp1 = new Point(0, radius);
//        int delta = 1 - radius;

//        do {
//            temp1.x += 1;
//            if (delta < 0) {
//                delta = delta + 2 * temp1.x + 3;
//            } else {
//                temp1.y -= 1;
//                delta = 2 * (temp1.x - temp1.y) + 5;
//            }
//            pointArr.add(new Point(temp1.x, +temp1.y));
//        } while (temp1.x < temp1.y);
//        System.out.println(button.getX() + ":" + button.getY());
//        System.out.println(button1.getX() + ":" + button1.getY());
//        System.out.println(pointArr);
//        System.out.println(mid);
//
//
//
//
//        new Thread(() -> {
//        for (Point point : pointArr) {
//            System.out.println(point.toString());
//            button.setBounds(mid.x - point.y, mid.y - point.x, 90, 90);
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }

    }

    private void paintArrayBoxes(int[] array) {
        buttons = new JButton[array.length];
        for (int i = 0; i < array.length; i++) {
            buttons[i] = new JButton(String.valueOf(array[i]));
            buttons[i].setBounds(i * 100 + 50, 250, 90, 90);
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFont(new Font("David", Font.BOLD, 20));
            buttons[i].setBackground(new Color(34, 182, 182, 187));
            this.add(buttons[i]);
//            buttons[i].setEnabled(false);
            buttons[i].setFocusPainted(false);
            buttons[i].setVisible(true);
            buttons[i].requestFocus();
        }
    }

    public void resetArray() {
        for (JButton b : buttons) {
            b.setVisible(false);
            this.remove(b);
        }
        buttons = null;
    }
}
