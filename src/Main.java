import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        JFrame frame = new JFrame("Algorithm Visualization");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(Definitions.WIN_DIM);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);
        InterfacePanel panel = new InterfacePanel();
        frame.getContentPane().add(panel);


        frame.setVisible(true);

    }


}