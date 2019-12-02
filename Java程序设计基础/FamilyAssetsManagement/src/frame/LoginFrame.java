package frame;

import bean.UsersUtil;
import bean.Users;
import util.DBHelper;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends MyFrame{
    private static String title = "登录";
    private static final int width = 720; //宽度
    private static final int height = 360;  //高度
    private static String userName = "";
    private static String userPassword = "";
    private static JTextField user_text = new JTextField(); //普通输入框
    private static JPasswordField password_text = new JPasswordField(); //密码输入框

    //为空间设置边界
    private void setMargin(JPanel main,int top, int left, int bottom, int right){
        Border border = main.getBorder();
        Border margin = new EmptyBorder(top,left,bottom,right);
        main.setBorder(new CompoundBorder(border,margin));
    }

    public LoginFrame(){
        super(title);
        int x = (int) this.getToolkit().getScreenSize().getWidth(); //获得屏幕宽度
        int y = (int) this.getToolkit().getScreenSize().getHeight(); //获得屏幕高度
        this.setSize(width, height);
        this.setLocation(x/2 - width/2, y/2 - height/2);  //居中显示

        JPanel main = new JPanel(new GridLayout(1,2)); //主面板

        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.CENTER);
        JPanel login_main = new JPanel(flowLayout);

        JPanel login = new JPanel(new BorderLayout());
        //总标题
        JPanel title_main = new JPanel();
        JLabel title1 = new JLabel("家庭资产管理系统");
        title1.setFont(new Font("隶书",Font.BOLD,30));
        title_main.add(title1);

        //登录标题
        JPanel title_pan = new JPanel();
        JLabel title = new JLabel("用户登录");
        title.setFont(new Font("隶书",Font.BOLD,20));
        title_pan.add(title);

        JPanel content = new JPanel(new GridLayout(2,1));
        //用户名和输入框
        JPanel user_pan = new JPanel();
        JLabel username = new JLabel("用户名：");
        username.setFont(new Font("宋体",Font.BOLD,15));
        user_text.setColumns(15);
        user_pan.add(username);
        user_pan.add(user_text);

        //密码和输入框
        JPanel pass_pan = new JPanel();
        JLabel password = new JLabel("密  码：");
        password.setFont(new Font("宋体",Font.BOLD,15));
        password_text.setColumns(15);
        pass_pan.add(password);
        pass_pan.add(password_text);

        content.add(user_pan);
        content.add(pass_pan);

        //登陆注册按钮
        JPanel btn_pan = new JPanel();
        JButton login_btn = new JButton("登录");
        JButton resign_btn = new JButton("注册");
        JButton cancel_btn = new JButton("取消");
        btn_pan.add(login_btn);
        btn_pan.add(resign_btn);
        btn_pan.add(cancel_btn);
        setMargin(btn_pan,20,0,0,0);

        //logo
        JPanel logo_panel = new JPanel();
        JLabel label = new JLabel();
        ImageIcon imageIcon = new ImageIcon("./src/images/logo.png");
        label.setIcon(imageIcon); //设置图片
        logo_panel.add(label);

        login_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //这里进行用户名密码验证
                userName = user_text.getText();
                userPassword = new String(password_text.getPassword());
                Users user = new Users(userName,userPassword);
                UsersUtil usersUtil = new UsersUtil();
                if(usersUtil.checkData(user)){
                    MainFrame frame = new MainFrame();
                    LoginFrame.this.setVisible(false);  //登录成功就把登录窗口隐藏
                }
                else{
                    JOptionPane.showMessageDialog(null, "用户名或密码错误！", "提示信息", JOptionPane.CLOSED_OPTION);
                }
            }
        });
        resign_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //弹出注册窗口
                ResignFrame resignFrame = new ResignFrame();
            }
        });
        cancel_btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                DBHelper.closeConnect();
                //取消按钮，直接退出程序
                System.exit(0);
            }
        });

        login.add(title_pan,BorderLayout.NORTH);
        login.add(content,BorderLayout.CENTER);
        login.add(btn_pan,BorderLayout.SOUTH);

        login_main.add(title_main);
        setMargin(login,30,0,0,0); //设置边界

        login_main.add(login);

        main.add(logo_panel);
        main.add(login_main);

        this.add(main);
        this.setVisible(true);
    }
}
