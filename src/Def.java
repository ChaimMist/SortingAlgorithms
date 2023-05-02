import javax.swing.*;
import java.awt.*;

public class Def {

    public static final Color LIGHT_BLUE = new Color(34, 182, 182, 187);
    public static final Dimension WIN_DIM = Toolkit.getDefaultToolkit().getScreenSize();
    public static boolean DARK_MODE = false;


    public void moveLeft(int amount, JComponent component) {
        for (int i = 0; i < amount; i++) {
            component.setBounds(component.getX() - 1, component.getY(), 90, 90);
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void moveRight(int amount, JComponent component) {
        for (int i = 0; i < amount; i++) {
            component.setBounds(component.getX() + 1, component.getY(), 90, 90);
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void moveUp(int amount, JComponent component) {
        for (int i = 0; i < amount; i++) {
            component.setBounds(component.getX(), component.getY() - 1, 90, 90);
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void moveDown(int amount, JComponent component) {
        for (int i = 0; i < amount; i++) {
            component.setBounds(component.getX(), component.getY() + 1, 90, 90);
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
