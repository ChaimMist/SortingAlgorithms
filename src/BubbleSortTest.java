
import javax.swing.*;
import org.junit.jupiter.api.Test;
import java.awt.*;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

    public class BubbleSortTest {

        private static final int[] UNSORTED_ARRAY = {3, 1, 4, 2, 5};
        private static final int[] SORTED_ARRAY = {1, 2, 3, 4, 5};

        private static final JButton[] BUTTONS = {
                new JButton("3"), new JButton("1"), new JButton("4"),
                new JButton("2"), new JButton("5")
        };

        private BubbleSort bubbleSort = new BubbleSort(new Def());

        @Test
        public void testBubbleSort() {
            bubbleSort.start(BUTTONS, UNSORTED_ARRAY);

            // wait for the sorting to finish
            try {
                Thread.sleep(10000);
            } catch (InterruptedException ignored) {}

            // check if the array is sorted
            assertArrayEquals(SORTED_ARRAY, UNSORTED_ARRAY);

            // check if the buttons are sorted
            for (int i = 0; i < BUTTONS.length; i++) {
                assertEquals(Integer.toString(SORTED_ARRAY[i]), BUTTONS[i].getText());
            }

            // check if the buttons' background colors are set to green
            for (JButton button : BUTTONS) {
                assertEquals(Color.GREEN, button.getBackground());
            }
        }
    }


