import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CountDownLatch;


public class BubbleSort {
    private Def def;

    public BubbleSort(Def def) {
        this.def = def;
    }



    public void start(JButton[] buttons, int[] array) {
        new Thread(() -> {
            boolean swapped = true;
            int counter = 0;
            while (swapped) {
                counter++;
                swapped = false;
                for (int i = 0; i < array.length - counter; i++) {
                    if (!AlgoPanel.runAlgo)
                        return;
                    if (array[i + 1] < array[i]) {
                        swapButtons(buttons[i], buttons[i + 1]);
                        buttons[i].setBackground(Def.LIGHT_BLUE);
                        buttons[i + 1].setBackground(Def.LIGHT_BLUE);

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
                buttons[buttons.length - counter].setForeground(Color.BLACK);
            }
            for (int i = 0; i < buttons.length - counter; i++) {
                buttons[i].setBackground(Color.GREEN);
                buttons[i].setForeground(Color.BLACK);
            }

        }).start();
    }

    private void swapButtons(JButton button, JButton button1) {
        button.setBackground(Color.RED);
        button1.setBackground(Color.RED);
        CountDownLatch latch = new CountDownLatch(2);

        new Thread(() -> {
            def.moveUp(50, button);
            def.moveRight(100, button);
            def.moveDown(50, button);
            latch.countDown();

        }).start();

        new Thread(() -> {
            def.moveDown(50, button1);
            def.moveLeft(100, button1);
            def.moveUp(50, button1);
            latch.countDown();
        }).start();

        try {
            latch.await();
        } catch (Exception ignored) {
        }
    }

}
