package frame;

import bean.UsersUtil;
import bean.Users;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ResignFrame extends MyFrame {

    private static String title = "注册";
    private static final int width = 360;
    private static final int height = 360;
    private static String userName = "";
    private static String userPassword = "";
    private static String reUserPassword = "";
    private static JTextField user_text = new JTextField();
    private static JTextField user_name = new JTextField();
    private static JPasswordField pass_text = new JPasswordField();
    private static JPasswordField re_pass_text = new JPasswordField();
    private static JRadioButton radioM,radioF; //单选框
    private static ButtonGroup group;

    ResignFrame(){
        super(title);

        int x =(int) this.getToolkit().getScreenSize().getWidth();
        int y =(int) this.getToolkit().getScreenSize().getHeight();

        this.setSize(width, height);
        this.setLocation(x / 2 - height / 2, y / 2 - width / 2);
        this.setResizable(false);

        JPanel main = new JPanel(new BorderLayout());
        JPanel title_pan = new JPanel();
        JLabel title = new JLabel("用户注册");
        title.setFont(new Font("隶书",Font.BOLD,20));
        title_pan.add(title);

        JPanel content = new JPanel(new GridLayout(5,1));
        JPanel user_pan = new JPanel();
        JLabel username = new JLabel("用 户 名：");
        username.setFont(new Font("宋体",Font.BOLD,15));
        user_text.setColumns(15);
        user_pan.add(username);
        user_pan.add(user_text);

        JPanel pass_pan = new JPanel();
        JLabel password = new JLabel("密    码：");
        password.setFont(new Font("宋体",Font.BOLD,15));
        pass_text.setColumns(15);
        pass_pan.add(password);
        pass_pan.add(pass_text);

        JPanel rePassword_pan = new JPanel();
        JLabel re_password = new JLabel("确认密码：");
        re_password.setFont(new Font("宋体",Font.BOLD,15));
        re_pass_text.setColumns(15);
        rePassword_pan.add(re_password);
        rePassword_pan.add(re_pass_text);

        JPanel name_pan = new JPanel();
        JLabel nameLabel = new JLabel("姓    名：");
        nameLabel.setFont(new Font("宋体",Font.BOLD,15));
        user_name.setColumns(15);
        name_pan.add(nameLabel);
        name_pan.add(user_name);

        radioM = new JRadioButton("男");
        radioF = new JRadioButton("女");
        group = new ButtonGroup();
        group.add(radioM);
        group.add(radioF);
        JPanel sex_pan = new JPanel();
        JLabel sexLabel = new JLabel("性    别：");
        sexLabel.setFont(new Font("宋体",Font.BOLD,15));
        sex_pan.add(sexLabel);
        sex_pan.add(radioM);
        sex_pan.add(radioF);

        content.add(user_pan);
        content.add(pass_pan);
        content.add(rePassword_pan);
        content.add(name_pan);
        content.add(sex_pan);

        JPanel btn_pan = new JPanel();
        JButton reg_btn = new JButton("注册");
        JButton cancel_btn = new JButton("取消");
        btn_pan.add(reg_btn);
        btn_pan.add(cancel_btn);

        reg_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //注册按钮
                userName = user_text.getText();
                userPassword = new String(pass_text.getPassword());
                reUserPassword = new String(re_pass_text.getPassword());
                if(userName.equals("")){
                    //用户名为空的情况
                    JOptionPane.showMessageDialog(null, "用户名不能为空！", "提示信息", JOptionPane.CLOSED_OPTION);
                    user_text.grabFocus();
                    return;
                }
                else if(userPassword.equals("")){
                    //密码为空的情况
                    JOptionPane.showMessageDialog(null, "密码不能为空！", "提示信息", JOptionPane.CLOSED_OPTION);
                    pass_text.grabFocus();
                    return;
                }
                else if(!userPassword.equals(reUserPassword)){
                    //判断两次密码一不一致
                    JOptionPane.showMessageDialog(null, "两次输入密码不一致！", "提示信息", JOptionPane.CLOSED_OPTION);
                    re_pass_text.grabFocus();
                    return;
                }
                Users u = new Users(userName,userPassword);
                UsersUtil usersUtil = new UsersUtil();
                if(usersUtil.addData(u)){
                    //如果返回true则注册成功
                    JOptionPane.showMessageDialog(null, "注册成功！", "提示信息", JOptionPane.CLOSED_OPTION);
                }
                else{
                    JOptionPane.showMessageDialog(null, "注册失败！", "提示信息", JOptionPane.CLOSED_OPTION);
                }
                ResignFrame.this.dispose();   //注册成功后将注册窗口隐藏
            }
        });
        cancel_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //取消按钮，使窗口消失
                ResignFrame.this.dispose();
            }
        });

        main.add(title_pan,BorderLayout.NORTH);
        main.add(content,BorderLayout.CENTER);
        main.add(btn_pan,BorderLayout.SOUTH);

        this.add(main);
        this.setVisible(true);

    }
}
