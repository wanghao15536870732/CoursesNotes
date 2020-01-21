package frame;

import javax.swing.*;
import java.awt.*;

public class BaseJFrame extends JFrame {
    public BaseJFrame(String title, String srcPath){
        //设置窗体图标
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(srcPath));
        this.setTitle(title); //窗体标题
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(450, 200, 1300, 720);
        this.setResizable(false);
    }
}
