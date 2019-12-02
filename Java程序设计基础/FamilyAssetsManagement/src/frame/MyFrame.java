package frame;

import javax.swing.*;

//基类，用于设置所哟有窗口的属性
public class MyFrame extends JFrame {
    MyFrame(String title){
        this.setTitle(title);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
    }
}
