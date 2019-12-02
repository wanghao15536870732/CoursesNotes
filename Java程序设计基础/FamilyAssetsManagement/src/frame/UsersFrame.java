package frame;

import bean.UsersUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UsersFrame extends JFrame implements ActionListener {
    public static String title = "所有注册用户信息";
    JTable table;
    JButton button;

    UsersFrame() {
        setTitle(title);
        Object name[] = {"用户名","密码","姓名","性别","出生日期","年龄"};
        UsersUtil usersUtil = new UsersUtil();
        Object data[][] = usersUtil.getAllUses();
        table = new JTable(data,name);

        button = new JButton("更新账户");
        button.addActionListener(this);
        Container container = getContentPane();
        getContentPane().add(new JScrollPane(table), BorderLayout.CENTER);
        container.add(new JLabel("修改或录入数据后，需回车确认"),BorderLayout.SOUTH);
        container.add(button,BorderLayout.SOUTH);

        setSize(600,300);
        setVisible(true);
        validate();
        //隐藏当前窗口，并释放资源
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        table.repaint();
    }
}
