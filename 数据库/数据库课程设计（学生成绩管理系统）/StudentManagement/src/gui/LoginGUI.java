package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.swing.JFrame;
import bean.Admin;
import bean.Student;
import bean.Teacher;
import frame.admin.AdminMainGUI;
import frame.student.StudentMainJFrame;
import frame.teacher.TeaMainGUI;
import handle.HandleAdm;
import handle.HandleStu;
import handle.HandleTea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;

public class LoginGUI extends JFrame implements KeyListener {
    private JTextField txtField; //文本框
    private JPasswordField pwdField; //密码框
    private JButton btn_enter; //按钮"登录"
    private JButton btn_exit;  //按钮"退出"
    private JRadioButton rBtn_Stu, rBtn_Tea, rBtn_Adn; //单选按钮 学生,教师,管理员
    private ButtonGroup group; //按钮组

    private Student student;
    private Teacher teacher;
    private Admin admin;
    private HandleStu handlestu;
    private HandleTea handletea;
    private HandleAdm handleadm;

    public LoginGUI() {
        initcomponent(); //初始化登录界面
        addEventHandler(); //事件处理
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LoginGUI frame = new LoginGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    void initcomponent() { //初始化登录界面
        //设置窗口图标
        setIconImage(Toolkit.getDefaultToolkit().getImage("./src/pictures/login2.png"));
        setTitle("登录/LOGIN"); //窗口标题
        setFont(new Font("黑体", Font.BOLD, 12));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(550, 250, 879, 528);  //窗口大小
        setResizable(false); //设置窗体大小 不可变

        //背景图片
        DrawImage bk_pic = new DrawImage();
        setContentPane(bk_pic); //设置背景图片为容器
        bk_pic.setLayout(null); //设置空布局

        JLabel lb_username = new JLabel("用户名：");
        lb_username.setBounds(356, 147, 113, 35);
        lb_username.setFont(new Font("楷体", Font.PLAIN, 18));
        ImageIcon icon1 = new ImageIcon("./src/pictures/user.jpg");
        icon1.setImage(icon1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
        lb_username.setIcon(icon1);
        bk_pic.add(lb_username);

        txtField = new JTextField();
        txtField.setFont(new Font("宋体", Font.PLAIN, 18));
        txtField.setBounds(471, 147, 206, 35);
        bk_pic.add(txtField);
        txtField.setColumns(10);

        JLabel lb_pwd = new JLabel("密码：");
        ImageIcon icon2 = new ImageIcon("./src/pictures/password2.jpg");
        icon2.setImage(icon2.getImage().getScaledInstance(20, 18, Image.SCALE_DEFAULT));
        lb_pwd.setIcon(icon2);
        lb_pwd.setBounds(356, 203, 90, 35);
        lb_pwd.setFont(new Font("楷体", Font.PLAIN, 18));
        bk_pic.add(lb_pwd);

        pwdField = new JPasswordField();
        pwdField.setFont(new Font("宋体", Font.PLAIN, 18));
        pwdField.setBounds(471, 203, 206, 35);
        pwdField.setColumns(10);
        bk_pic.add(pwdField);

        btn_enter = new JButton("登录");
        btn_enter.setForeground(Color.BLACK);
        btn_enter.setBackground(new Color(0, 191, 255));
        btn_enter.setBounds(330, 351, 113, 35);
        btn_enter.setFont(new Font("楷体", Font.PLAIN, 22));
        bk_pic.add(btn_enter);

        btn_exit = new JButton("退出");
        btn_exit.setForeground(Color.BLACK);
        btn_exit.setBackground(new Color(0, 191, 255));
        btn_exit.setBounds(564, 351, 113, 35);
        btn_exit.setFont(new Font("楷体", Font.PLAIN, 22));
        bk_pic.add(btn_exit);

        JLabel lb_Stu = new JLabel("学生");
        lb_Stu.setBounds(128, 131, 159, 50);
        lb_Stu.setFont(new Font("华文隶书", Font.PLAIN, 34));
        ImageIcon icon3 = new ImageIcon("./src/pictures/student.jpg");
        icon3.setImage(icon3.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
        lb_Stu.setIcon(icon3);
        bk_pic.add(lb_Stu);

        JLabel lb_System = new JLabel("成 绩 管 理 系 统");
        lb_System.setBounds(89, 202, 253, 35);
        lb_System.setFont(new Font("隶书", Font.PLAIN, 28));
        bk_pic.add(lb_System);

        rBtn_Stu = new JRadioButton("学生");
        rBtn_Stu.setForeground(Color.WHITE);
        rBtn_Stu.setBackground(Color.gray);
        rBtn_Stu.setBounds(330, 269, 90, 35);
        rBtn_Stu.setFont(new Font("楷体", Font.BOLD, 18));
        bk_pic.add(rBtn_Stu);

        rBtn_Tea = new JRadioButton("教师");
        rBtn_Tea.setBackground(Color.gray);
        rBtn_Tea.setForeground(Color.WHITE);
        rBtn_Tea.setBounds(458, 269, 90, 35);
        rBtn_Tea.setFont(new Font("楷体", Font.BOLD, 18));
        bk_pic.add(rBtn_Tea);

        rBtn_Adn = new JRadioButton("管理员");
        rBtn_Adn.setBackground(Color.gray);
        rBtn_Adn.setForeground(Color.WHITE);
        rBtn_Adn.setBounds(587, 269, 90, 35);
        rBtn_Adn.setFont(new Font("楷体", Font.BOLD, 18));
        bk_pic.add(rBtn_Adn);

        group = new ButtonGroup();
        group.add(rBtn_Stu);
        group.add(rBtn_Tea);
        group.add(rBtn_Adn);
    }

    void addEventHandler() { //事件处理
        //响应“登录”按钮
        btn_enter.addActionListener(new ActionListener() {
            String identity = ""; //用户登录身份

            public void actionPerformed(ActionEvent arg0) {
                Enumeration<AbstractButton> radioBtns = group.getElements();
                while (radioBtns.hasMoreElements()) {
                    AbstractButton btn = radioBtns.nextElement();
                    if (btn.isSelected()) {
                        identity = btn.getText();//获取被选中单选按钮的文本
                    }
                }
                try {
                    //处理登录,参数是用户名,密码,登录身份
                    handle_enter(txtField.getText(), new String(pwdField.getPassword()), identity);
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }
        });

        //响应键盘事件
        txtField.addKeyListener(this);
        pwdField.addKeyListener(this);
        rBtn_Stu.addKeyListener(this);
        rBtn_Tea.addKeyListener(this);
        rBtn_Adn.addKeyListener(this);
        //响应“退出”按钮
        btn_exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                handle_exit();//处理退出
            }
        });
    }

    void handle_enter(String userId, String userPwd, String identity) throws SQLException {
        boolean b1 = userId.equals("");
        boolean b2 = userPwd.equals("");
        boolean b3 = identity.equals("");

        if (b1 && b2)        //用户名和密码均为空
            JOptionPane.showMessageDialog(this, "请输入用户名和密码！",
                    "消息对话框", JOptionPane.WARNING_MESSAGE);
        if (b1 && !b2)       //用户名为空,密码非空
            JOptionPane.showMessageDialog(this, "请输入用户名！",
                    "消息对话框", JOptionPane.WARNING_MESSAGE);
        if (!b1 && b2)       //用户名非空,密码为空
            JOptionPane.showMessageDialog(this, "请输入密码！",
                    "消息对话框", JOptionPane.WARNING_MESSAGE);
        if (!b1 && !b2 && b3)  //用户名和密码非空,登录身份为空
            JOptionPane.showMessageDialog(this, "请选择您的登录身份！",
                    "消息对话框", JOptionPane.WARNING_MESSAGE);
        if (!b1 && !b2 && !b3) { //用户名,密码和登录身份均非空
            handlestu = new HandleStu();
            handletea = new HandleTea();
            handleadm = new HandleAdm();

            int result = 0;
            if (identity.equals("学生")) { //如果用户选择的登录身份是 学生
                student = new Student(userId, userPwd);
                result = handlestu.checkStu(student);//检测用户登录名,密码是否正确
            } else if (identity.equals("教师")) { //如果用户选择的登录身份是 教师
                teacher = new Teacher(userId, userPwd);
                result = handletea.checkTea(teacher);//检测用户登录名,密码是否正确
            } else if (identity.equals("管理员")) { //如果用户选择的登录身份是 管理员
                admin = new Admin(userId, userPwd);
                result = handleadm.checkAdn(admin);//检测用户登录名,密码是否正确
            }

            /*
             * -1:用户名不存在(用户名输入不正确)
             * 0:用户密码错误
             * 1:用户名密码均正确
             */

            if (result == -1) {
                JOptionPane.showMessageDialog(this, "该用户名不存在，请重新输入用户名！", "消息对话框", JOptionPane.ERROR_MESSAGE);
            } else if (result == 0) {
                JOptionPane.showMessageDialog(this, "密码输入错误，请重新输入密码！", "消息对话框", JOptionPane.ERROR_MESSAGE);
            } else if (result == 1 && identity.equals("学生")) {
                Student new_student = HandleStu.getStuInfo(student.getSno());
                StudentMainJFrame studentMainJFrame = new StudentMainJFrame(new_student);
                studentMainJFrame.setVisible(true);
                this.dispose();
            } else if (result == 1 && identity.equals("教师")) {
                TeaMainGUI teamaingui = new TeaMainGUI(teacher);//进入教师主界面
                teamaingui.setVisible(true);
                this.dispose();
            } else if (result == 1 && identity.equals("管理员")) {
                AdminMainGUI adminMainGUI = new AdminMainGUI(admin);//进入管理员主界面
                adminMainGUI.setVisible(true);
                this.dispose();
            }
        }
    }

    void handle_exit() {
        int n = JOptionPane.showConfirmDialog(this, "您确认要退出吗?", "确认对话框", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION)
            System.exit(0);
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
        String identity = "";
        if (e.getKeyChar() == '\n') {
            Enumeration<AbstractButton> radioBtns = group.getElements();
            while (radioBtns.hasMoreElements()) {
                AbstractButton btn = radioBtns.nextElement();
                if (btn.isSelected()) {
                    identity = btn.getText();//获取被选中单选按钮的文本
                }
            }
            try {
                //处理登录,参数是用户名,密码,登录身份
                handle_enter(txtField.getText(), new String(pwdField.getPassword()), identity);
            } catch (SQLException e1) {
                System.out.println(e1);
            }
        }
    }
}
