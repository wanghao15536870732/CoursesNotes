package gui;

import javax.swing.*;
import java.awt.*;

public class DrawBack extends JPanel {
    ImageIcon back = new ImageIcon("./src/pictures/tree.jpg");
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(back.getImage(), 0, 0,230, 535, this);
    }
}
