import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;


public class InterfacePanel extends JPanel implements ActionListener {
    private final String[] algoList = {"Bubble Sort", "Insertion Sort", "Selection Sort", "Merge Sort", "Quick Sort"};
    private final JComboBox<String> algoSelector1, algoSelector2;
    private final JButton startButton, stopButton;
    private int[] array;
    private JLabel algoTitle1, algoTitle2;
    private final AlgoPanel algo1Panel, algo2Panel;
    private final JPanel headerPanel, footerPanel;
    private final JLabel title = new JLabel("Sorting Algorithm Visualization");
    private final JTextField input;

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
        headerPanel = createPanel(0, 0, Def.WIN_DIM.width, Def.WIN_DIM.height / 5, new Color(24, 23, 23), BorderFactory.createBevelBorder(1));
        footerPanel = createPanel(0, Def.WIN_DIM.height - Def.WIN_DIM.height / 5, Def.WIN_DIM.width, Def.WIN_DIM.height / 5, new Color(23, 22, 22), BorderFactory.createBevelBorder(1));
        algo1Panel = new AlgoPanel(0, headerPanel.getHeight(), Def.WIN_DIM.width / 2, headerPanel.getHeight() * 3, new Color(30, 13, 35), BorderFactory.createBevelBorder(1));
        algo2Panel = new AlgoPanel(algo1Panel.getWidth(), headerPanel.getHeight(), Def.WIN_DIM.width / 2, (Def.WIN_DIM.height / 5) * 3, new Color(30, 13, 35), BorderFactory.createBevelBorder(1));

        //Create 2 combo boxes
        algoSelector1 = initJComboBox(20, headerPanel.getHeight() - 100);
        algoSelector2 = initJComboBox(headerPanel.getWidth() - 220, headerPanel.getHeight() - 100);

        //Create input text field
        input = creatInput(footerPanel.getWidth() / 2 - 200, 3 * (footerPanel.getHeight() / 6), 400, 50, new Color(255, 255, 255), "ex: 1,2,3,4,5...");

        //
        algoTitle1 = initJLabel(0, headerPanel.getHeight() - 50);
        algoTitle2 = initJLabel(algo2Panel.getX(), headerPanel.getHeight() - 50);


        //Add buttons to footer panel
        startButton = creatButton("Start", (footerPanel.getWidth() / 2) - 200, (footerPanel.getHeight() / 6), 195, 50, new Color(100, 200, 30));
        stopButton = creatButton("Stop", (footerPanel.getWidth() / 2) + 5, (footerPanel.getHeight() / 6), 195, 50, new Color(100, 20, 30));
        footerPanel.add(startButton);
        footerPanel.add(stopButton);
        footerPanel.add(input);
        headerPanel.add(title);

        //Add 4 panels to main panel
        this.add(headerPanel);
        this.add(algo1Panel);
        this.add(algo2Panel);
        this.add(footerPanel);

        //Add title to headerPanel
        headerPanel.add(algoTitle1);
        headerPanel.add(algoTitle2);

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
        algo1Panel.setAlgoType(algoSelector1.getSelectedItem().toString());
        algoTitle1.setText(algoSelector1.getSelectedItem().toString());
        algo1Panel.startAlgo(Arrays.copyOf(array, array.length));


        algo2Panel.setAlgoType(algoSelector2.getSelectedItem().toString());
        algoTitle2.setText(algoSelector2.getSelectedItem().toString());
        algo2Panel.startAlgo(Arrays.copyOf(array, array.length));
    }

    public void stopSorting() {
        AlgoPanel.runAlgo = false;
        algo1Panel.resetArray();
        algo2Panel.resetArray();
    }

    public JComboBox<String> initJComboBox(int x, int y) {
        JComboBox<String> box = new JComboBox<>(algoList);
        box.setBounds(x, y, 200, 50);
        box.setFont(new Font("David", Font.BOLD, 20));
        box.setForeground(Color.WHITE);
        footerPanel.add(box);
        box.setVisible(true);
        return box;
    }

    public JLabel initJLabel(int x, int y) {
        JLabel label = new JLabel();
        label.setBounds(x, y, algo2Panel.getWidth(), 50);
        label.setFont(new Font("David", Font.BOLD, 40));
        label.setForeground(Color.WHITE);
        label.setHorizontalAlignment(JTextField.CENTER);
        this.add(label);
        label.setVisible(true);
        return label;
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


    @Override
    public void validate() {
        super.validate();
        Def.WIN_DIM = new Dimension(Main.frame.getWidth(), Main.frame.getHeight());
        this.setBounds(0, 0, Def.WIN_DIM.width, Def.WIN_DIM.height);
        headerPanel.setBounds(0, 0, this.getWidth(), this.getHeight() / 5);
        footerPanel.setBounds(0, (this.getHeight() / 5) * 4, this.getWidth(), this.getHeight() / 5);
        this.title.setBounds(this.getWidth() / 2 - 225, 0, 450, 200);
        input.setBounds(footerPanel.getWidth() / 2 - 200, 3 * (footerPanel.getHeight() / 6), 400, 50);
        startButton.setBounds((footerPanel.getWidth() / 2) - 200, (footerPanel.getHeight() / 6), 195, 50);
        stopButton.setBounds((footerPanel.getWidth() / 2) + 5, (footerPanel.getHeight() / 6), 195, 50);
        algoSelector1.setBounds(20, footerPanel.getHeight() - 100, 200, 50);
        algoSelector2.setBounds(footerPanel.getWidth() - 230, footerPanel.getHeight() - 100, 200, 50);
        algo1Panel.setBounds(0, headerPanel.getHeight(), this.getWidth() / 2, (this.getHeight() / 5) * 3);
        algo2Panel.setBounds(this.getWidth() / 2, headerPanel.getHeight(), this.getWidth() / 2, (this.getHeight() / 5) * 3);

        algoTitle1.setBounds(0, headerPanel.getHeight() - 50, algo1Panel.getWidth(), 50);
        algoTitle2.setBounds(headerPanel.getWidth() / 2, headerPanel.getHeight() - 50, algo1Panel.getWidth(), 50);
        //1,2,3,3,2,1,3,44
    }
}
