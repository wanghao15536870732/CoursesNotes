package frame.admin;

import javax.swing.*;
import java.awt.*;

public class BaseJPanel extends JPanel {
    ImageIcon back = new ImageIcon("./src/pictures/background3.jpg");
    public BaseJPanel(String title){
        JLabel lblNewLabel = new JLabel(title);
        lblNewLabel.setFont(new Font("楷体", Font.BOLD, 22));
        lblNewLabel.setBounds(50, 50, 140, 20);
        add(lblNewLabel);
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(back.getImage(), 0, 0,getWidth(), getHeight(), this);
    }
}
