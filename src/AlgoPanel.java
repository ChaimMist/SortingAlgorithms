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
                for (int i = 0; i < array.length - counter; i++) {
                    if (!runAlgo)
                        return;
                    if (array[i + 1] < array[i]) {
                        swapButtons(buttons[i], buttons[i + 1]);
                        buttons[i].setBackground(Definitions.LIGHT_BLUE);
                        buttons[i+1].setBackground(Definitions.LIGHT_BLUE);

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
                buttons[buttons.length - counter].setBackground(Color.GREEN);
            }
            for (int i =0; i< buttons.length-counter; i++)
                buttons[i].setBackground(Color.GREEN);

        }).start();
    }

    private void swapButtons(JButton button, JButton button1) {
        button.setBackground(Color.RED);
        button1.setBackground(Color.RED);
        CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            moveUp(50, button);
            moveRight(100, button);
            moveDown(50, button);
            latch.countDown();

        }).start();

        new Thread(() -> {
            moveDown(50, button1);
            moveLeft(100, button1);
            moveUp(50, button1);
            latch.countDown();
        }).start();

        try {
            latch.await();
        }catch (Exception ignored){}
    }


    private void moveLeft(int amount, JComponent component) {
        for (int i = 0; i < amount; i++) {
            component.setBounds(component.getX() - 1, component.getY(), 90, 90);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void moveRight(int amount, JComponent component) {
        for (int i = 0; i < amount; i++) {
            component.setBounds(component.getX() + 1, component.getY(), 90, 90);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void moveUp(int amount, JComponent component) {
        for (int i = 0; i < amount; i++) {
            component.setBounds(component.getX(), component.getY() - 1, 90, 90);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void moveDown(int amount, JComponent component) {
        for (int i = 0; i < amount; i++) {
            component.setBounds(component.getX(), component.getY() + 1, 90, 90);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


//        int radius = (button1.getX() - button.getX()) / 2;
//        Point mid = new Point((button1.getX() - button.getX()) / 2 + button.getX(), button1.getY() + button.getHeight()/2);
//        JButton test = new JButton("awd");
//        test.setBounds(mid.x, mid.y, 5, 5);
//        test.setVisible(true);
//        test.requestFocus();
//        this.add(test);
//        ArrayList<Point> pointArr = new ArrayList<>();
//        Point temp1 = new Point(0, radius);
//        int delta = 1 - radius;
//
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
//        new Thread(() -> {
//            for (Point point : pointArr) {
//                System.out.println(point.toString());
//                button.setBounds(mid.x - point.y, mid.y - point.x, 90, 90);
//                try {
//                    Thread.sleep(10);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//
//        }).start();


    private void paintArrayBoxes(int[] array) {
        buttons = new JButton[array.length];


        for (int i = 0; i < array.length; i++) {
            buttons[i] = new JButton(String.valueOf(array[i]));
            buttons[i].setBounds(i * 100 + 50, 250, 90, 90);
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFont(new Font("David", Font.BOLD, 20));
            buttons[i].setBackground(Definitions.LIGHT_BLUE);
            this.add(buttons[i]);
            buttons[i].setFocusPainted(false);
            buttons[i].setVisible(true);
            buttons[i].requestFocus();
        }
    }

    public void resetArray() {
        if (buttons.length == 0) {
            return;
        }
        for (JButton b : buttons) {
            b.setVisible(false);
            this.remove(b);
        }
        buttons = null;
    }
}
