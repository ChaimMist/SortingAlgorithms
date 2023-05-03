import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;

public class MergeSort {
    private Def def;

    public MergeSort(Def def) {
        this.def = def;
    }

    public void start(JButton[] buttons, int[] array) {
        new Thread(() -> {
            split(array);
        }).start();
    }

    public void split(int[] arr) {
        if (arr.length == 1) return;
        int[] left = new int[arr.length / 2];
        int[] right = new int[arr.length - left.length];
        int i = 0, j = 0;
        for (; i < arr.length; i++) {
            if (i < arr.length / 2) {
                left[i] = arr[i];
            } else {
                right[j] = arr[i];
                j++;
            }
        }
        split(left);

        split(right);
        merge(left, right, arr);
    }


    private void merge(int[] left, int[] right, int[] original) {
        int leftSize = left.length, rightSize = right.length;
        int l = 0, r = 0, i = 0;
        while (l < leftSize && r < rightSize) {
            if (left[l] < right[r]) {
                original[i] = left[l];
                l++;
            } else {
                original[i] = right[r];
                r++;
            }
            i++;
        }

        while (l < leftSize) {
            original[i] = left[l];
            l++;
            i++;
        }
        while (r < rightSize) {
            original[i] = right[r];
            r++;
            i++;
        }
        System.out.println(Arrays.toString(original));
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
