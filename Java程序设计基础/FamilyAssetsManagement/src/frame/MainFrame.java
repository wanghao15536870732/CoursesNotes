package frame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends MyFrame {

    private static String title = "家庭资产管理系统";
    private static final int width = 600;
    private static final int height = 500;

    MainFrame() {
        super(title);
        int x = (int) this.getToolkit().getScreenSize().getWidth();
        int y = (int) this.getToolkit().getScreenSize().getHeight();

        this.setSize(width,height);
        this.setLocation(x/2 - width/2,y/2 - height/2);

        JMenuBar menuBar = new JMenuBar();
        JMenu new_item = new JMenu("新建");
        JMenuItem openItem = new JMenuItem("打开");
        JMenuItem resignItem = new JMenuItem("注册");
        JMenuItem exitItem = new JMenuItem("退出");
        new_item.add(openItem);
        new_item.add(resignItem);
        new_item.addSeparator();
        new_item.add(exitItem);

        JMenu check_item = new JMenu("查看");
        JMenuItem userItem = new JMenuItem("已注册用户", new ImageIcon("./src/images/user.png"));
        check_item.add(userItem);
        JMenuItem homeItem = new JMenuItem("家庭", new ImageIcon("./src/images/home.png"));
        check_item.add(homeItem);

        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsersFrame usersFrame = new UsersFrame();
            }
        });

        resignItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //弹出注册窗口
                ResignFrame resignFrame = new ResignFrame();
            }
        });

        userItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsersFrame usersFrame = new UsersFrame();
            }
        });

        homeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChartFrame chartFrame = new ChartFrame();
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        menuBar.add(new_item);
        menuBar.add(check_item);
        setJMenuBar(menuBar);
        //this.add(menuBar,BorderLayout.NORTH);
        this.setVisible(true);
    }
}
