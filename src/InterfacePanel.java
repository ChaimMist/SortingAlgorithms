import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;


public class InterfacePanel extends JPanel implements ActionListener {
    private final String[] algoList = {"Bubble Sort", "Insertion Sort", "Selection Sort", "Merge Sort", "Quick Sort"};
    private JComboBox<String> algoSelector2;
    private JComboBox<String> algoSelector1;
    private int[] array;
    private final AlgoPanel algo1Panel;
    private final AlgoPanel algo2Panel;
    private final JLabel title = new JLabel("Sorting Algorithm Visualization");
    private JTextField input;

    public InterfacePanel() {
        //create Main panel
        this.setBackground(new Color(100, 20, 220));
        this.setBounds(0, 0, Def.WIN_DIM.width, Def.WIN_DIM.height);
        this.setLayout(null);

        //Create title
        this.title.setBounds(Def.WIN_DIM.width / 2 - 225, 0, 450, 200);
        this.title.setFont(new Font("Arial", Font.BOLD, 30));
        this.title.setForeground(Color.WHITE);


        //Create 4 panels
        JPanel headerPanel = createPanel(0, 0, Def.WIN_DIM.width, Def.WIN_DIM.height / 5, new Color(24, 23, 23), BorderFactory.createBevelBorder(1));
        JPanel footerPanel = createPanel(0, Def.WIN_DIM.height - Def.WIN_DIM.height / 5, Def.WIN_DIM.width, Def.WIN_DIM.height / 5, new Color(23, 22, 22), BorderFactory.createBevelBorder(1));
        algo1Panel = new AlgoPanel(0, headerPanel.getHeight(), Def.WIN_DIM.width / 2, headerPanel.getHeight() * 3, new Color(30, 13, 35), BorderFactory.createBevelBorder(1));
        algo2Panel = new AlgoPanel(algo1Panel.getWidth(), headerPanel.getHeight(), Def.WIN_DIM.width / 2, (Def.WIN_DIM.height / 5) * 3, new Color(30, 13, 35), BorderFactory.createBevelBorder(1));

        //Create 2 combo boxes
        algoSelector1 = initJComboBox(headerPanel.getWidth() - 220, headerPanel.getHeight() - 100);
        algoSelector2 = initJComboBox( 20, headerPanel.getHeight() - 100);

        //Create input text field
        input = creatInput(footerPanel.getWidth() / 2 - 200, 3 * (footerPanel.getHeight() / 6), 400, 50, new Color(255, 255, 255), "ex: 1,2,3,4,5...");

        //Add buttons to footer panel
        footerPanel.add(creatButton("Start", (footerPanel.getWidth() / 2) - 200, (footerPanel.getHeight() / 6), 195, 50, new Color(100, 200, 30)));
        footerPanel.add(creatButton("Stop", (footerPanel.getWidth() / 2) + 5, (footerPanel.getHeight() / 6), 195, 50, new Color(100, 20, 30)));
        footerPanel.add(input);
        headerPanel.add(title);

        //Add 4 panels to main panel
        this.add(headerPanel);
        this.add(algo1Panel);
        this.add(algo2Panel);
        this.add(footerPanel);

    }

    public JPanel createPanel(int x, int y, int width, int height, Color color, Border border) {
        JPanel panel = new JPanel();
        panel.setBounds(x, y, width, height);
        panel.setBackground(color);
        panel.setBorder(border);
        panel.setLayout(null);
        return panel;
    }

    public JButton creatButton(String string, int x, int y, int width, int height, Color color) {
        JButton button = new JButton(string);
        button.setBounds(x, y, width, height);
        button.setFocusPainted(false);
        button.setFont(new Font("David", Font.BOLD, 20));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.addActionListener(this);
        return button;
    }


    public int[] stringToArray(String input) {
        input = input.replaceAll("\\[", "").replaceAll("\\]", "");
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == ' ')
                continue;

            if (input.charAt(i) != ',' && (input.charAt(i) < '0' || '9' < input.charAt(i))) {
                JOptionPane.showMessageDialog(null, "Invalid input. Ex: 1,2,3,4,5...");
                return null;
            }
        }

        String[] array = input.split(",");
        int[] intArray = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            if (array[i].isEmpty() || array[i].equals(" ") || array[i].equals(",") || array[i].equals("]") || array[i].equals("[")) {
                continue;
            }
            intArray[i] = Integer.parseInt(array[i]);
        }


        if (9 < intArray.length) {
            JOptionPane.showMessageDialog(null, "Please enter up to 9 numbers.");
            return null;
        }

        return intArray;
    }


    public JTextField creatInput(int x, int y, int width, int height, Color color, String string) {
        JTextField input = new JTextField(string);
        input.setFont(new Font("David", Font.BOLD, 25));
        input.setForeground(Color.GRAY);
        input.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (input.getText().equals(string)) {
                    input.setText("");
                    input.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (input.getText().isEmpty()) {
                    input.setForeground(Color.GRAY);
                    input.setText(string);
                }
            }
        });
        input.setBounds(x, y, width, height);
        input.setHorizontalAlignment(JTextField.CENTER);
        input.setBackground(color);
        return input;

    }


    public void startSorting() {
        AlgoPanel.runAlgo = true;
        algo1Panel.setAlgoType("Bubble Sort");
        algo1Panel.startAlgo(array);

        algo2Panel.setAlgoType("Bubble Sort");
        algo2Panel.startAlgo(array);
    }

    public void stopSorting() {
        AlgoPanel.runAlgo = false;
        algo1Panel.resetArray();
        algo2Panel.resetArray();
    }

    public JComboBox<String> initJComboBox(int x, int y){
        JComboBox<String> box = new JComboBox<>(algoList);
        box.setBounds(x,y, 200, 50);
        box.setFont(new Font("David",Font.BOLD,20));
        box.setForeground(Color.WHITE);
        this.add(box);
        box.setVisible(true);
        return box;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Start": {
                array = stringToArray(input.getText());
                if (array != null)
                    startSorting();
                break;
            }
            case "Stop":
                stopSorting();
        }
    }
}
