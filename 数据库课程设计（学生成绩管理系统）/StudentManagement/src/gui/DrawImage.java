package gui;

import javax.swing.*;
import java.awt.*;

/**
 * 设置背景图
 */
public class DrawImage extends JPanel {
    ImageIcon back = new ImageIcon("./src/pictures/background.jpg");
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(back.getImage(), 0, 0,getWidth(), getHeight(), this);
    }
}
