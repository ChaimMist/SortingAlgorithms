import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class AlgoPanel extends JPanel {


    private Def def = new Def();
    public static boolean runAlgo = false;
    private final BubbleSort bubbleSort = new BubbleSort(def);

    private JLabel algoTitle;
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
        this.algoTitle = initJLabel();

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
                algoTitle.setText("Bubble Sort");
                paintArrayBoxes(array);
                bubbleSort.start(buttons, array);
                break;
            case "Selection Sort":
                animateSelectionSort();
                break;
            case "Insertion Sort":
                animateInsertionSort();
                break;
        }
    }

    private void paintArrayBoxes(int[] array) {
        buttons = new JButton[array.length];
        int buttonsContainerWidth = array.length * 100;
        int startingX = this.width / 2 - buttonsContainerWidth / 2;

        System.out.println(startingX);
        for (int i = 0; i < array.length; i++) {
            buttons[i] = new JButton(String.valueOf(array[i]));
            buttons[i].setBounds(i * 100 + startingX, 250, 90, 90);
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
