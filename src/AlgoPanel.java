import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AlgoPanel extends JPanel {


    private Def def = new Def();
    public static boolean runAlgo = false;
    private final BubbleSort bubbleSort = new BubbleSort(def);


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

    public void startAlgo(int[] array) {
        switch (this.algoType) {
            case "Bubble Sort":
                paintArrayBoxes(array, this.width / 2 - array.length * 90 / 2, this.height/2,90,10);
                bubbleSort.start(buttons, array);
                break;
            case "Merge Sort":
                MergeSort mergeSort = new MergeSort(def);
                mergeSort.start(buttons, array);
                paintArrayBoxes(array,this.width / 2 - array.length * 60 / 2,20,60,1);
                break;
            case "Insertion Sort":
                break;
        }
    }

    private void paintArrayBoxes(int[] array, int x, int y, int size, int margin) {
        this.buttons = new JButton[array.length];
        for (int i = 0; i < array.length; i++) {
            buttons[i] = new JButton(String.valueOf(array[i]));
            buttons[i].setBounds(i *(size + margin) +x, y, size, size);
            buttons[i].setForeground(Color.WHITE);
            buttons[i].setFont(new Font("David", Font.BOLD, 20));
            buttons[i].setBackground(Def.LIGHT_BLUE);
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
