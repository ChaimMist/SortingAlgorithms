import javax.swing.*;
import java.awt.*;


public class Main extends JFrame {
    public static JFrame frame;
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        new Main();
    }


    public Main() throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        frame = new JFrame("Algorithm Visualization");
        frame.setSize(Def.WIN_DIM);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(950, 920));
        frame.setLayout(null);
        InterfacePanel panel = new InterfacePanel();
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }





}