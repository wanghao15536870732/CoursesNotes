package frame.admin;
import bean.Admin;
import data.GradeManageJPanel;
import frame.BaseJFrame;
import gui.DrawBack;
import gui.LoginGUI;
import handle.HandleSQL;
import util.FileUtil;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 管理员主界面
 */
public class AdminMainGUI extends BaseJFrame implements TreeSelectionListener {

    private Admin admin;
    private JPanel contentPane;
    private DefaultMutableTreeNode menu; //菜单
    private DefaultMutableTreeNode myInformationNode;  //我的信息
    private DefaultMutableTreeNode informationNode; //查询信息
    private DefaultMutableTreeNode changeInfoNode;  //修改信息
    private DefaultMutableTreeNode changePassNode;  //修改密码

    private DefaultMutableTreeNode gradeManageNode; //成绩管理
    private DefaultMutableTreeNode gradeArrangeNode;  //管理学生成绩
    private DefaultMutableTreeNode gradeSystemNode;  //录入系统开启关闭

    private DefaultMutableTreeNode examManageNode; // 考试管理
    private DefaultMutableTreeNode examArrangeNode; //安排考试
    private DefaultMutableTreeNode examSearchNode; //查询考试

    private DefaultMutableTreeNode courseManageNode;  //课程管理
    private DefaultMutableTreeNode courseAddNode;  //添加课程
    private DefaultMutableTreeNode courseSearchNode;  //查询课程
    private DefaultMutableTreeNode courseArrangeNode;  //安排课程

    private DefaultMutableTreeNode stuTeaManageNode;  //师生管理
    private DefaultMutableTreeNode stuArrNode;  //学生管理
    private DefaultMutableTreeNode stuAddNode;  //学生管理
    private DefaultMutableTreeNode teaArrNode;  //老师管理
    private DefaultMutableTreeNode teaAddNode;  //老师管理

    private JTree tree; //左侧树结构
    private JPanel panel; //右侧面板
    private SimpleDateFormat sdf;
    private Timer timer;
    private JToolBar statusbar; //状态栏
    private JLabel lbl_userInfo; //用户信息
    private JLabel lbl_operator; //当前操作
    private JLabel lbl_time; //时间显示
    private JButton btn_exit,btn_dbBackup; //退出按钮

    public AdminMainGUI(Admin admin) {
        super("管理员界面","./src/pictures/admin.png");
        this.admin = admin;
        initView();
        btn_exit.addActionListener(e -> handle_exit());
        btn_dbBackup.addActionListener(e -> {
            FileUtil.dbBackup(this);
        });
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                tree.getLastSelectedPathComponent();
        contentPane.remove(panel);
        lbl_operator.setText("[当前操作:]" + node.toString());
        if (node == informationNode) {  //查询信息
            EventQueue.invokeLater(() -> {
                AdminInfoJPanel panel = new AdminInfoJPanel(admin.getMno());
                replaceJPanel(panel);
            });
        }else if(node == changeInfoNode){  //修改信息
            EventQueue.invokeLater(() -> {
                AdminChangeInfoJPanel panel = new AdminChangeInfoJPanel(admin.getMno(),this);
                replaceJPanel(panel);
            });
        }else if(node == changePassNode){  //修改密码
            EventQueue.invokeLater(() -> {
                ChangePasswordJPanel panel = new ChangePasswordJPanel(admin.getMno(),this);
                replaceJPanel(panel);
            });
        }else if(node == gradeArrangeNode){  //成绩管理课程
            EventQueue.invokeLater(() ->{
                GradeSearchJPanel panel = new GradeSearchJPanel(this);
                replaceJPanel(panel);
            });
        }else if(node == gradeSystemNode){  //成绩录入管理
            EventQueue.invokeLater(() ->{
                GradeManageJPanel panel = new GradeManageJPanel();
                replaceJPanel(panel);
            });
        }else if(node == courseAddNode){  //添加课程
            EventQueue.invokeLater(() ->{
                CourseAddJPanel panel = new CourseAddJPanel();
                replaceJPanel(panel);
            });
        }else if(node == courseSearchNode){  //查询课程
            EventQueue.invokeLater(() ->{
                CourseSearchJPanel panel = new CourseSearchJPanel(this);
                replaceJPanel(panel);
            });
        }else if(node == courseArrangeNode){  //安排课程
            EventQueue.invokeLater(() ->{
                CourseArrangeJPanel panel = new CourseArrangeJPanel();
                replaceJPanel(panel);
            });
        }else if(node == examArrangeNode){ //安排考试
            EventQueue.invokeLater(() ->{
                ExamArrangeJPanel panel = new ExamArrangeJPanel();
                replaceJPanel(panel);
            });
        }else if(node == examSearchNode){  //考试查询
            ExamSearchJPanel panel = new ExamSearchJPanel();
            replaceJPanel(panel);
        }else if(node == stuArrNode){  //学生管理
            StuManageJPanel panel = new StuManageJPanel();
            replaceJPanel(panel);
        }else if(node == stuAddNode){   //添加学生
            StuAddJPanel panel = new StuAddJPanel(this);
            replaceJPanel(panel);
        }else if(node == teaArrNode){  //管理老师
            TeaManageJPanel panel = new TeaManageJPanel();
            replaceJPanel(panel);
        }else if(node == teaAddNode){  //添加老师
            TeaAddJPanel panel = new TeaAddJPanel(this);
            replaceJPanel(panel);
        }
    }

    public void replaceJPanel(JPanel newPanel) {
        panel = newPanel;
        newPanel.setBounds(230, 0, 1200, 650);
        contentPane.add(newPanel);
        contentPane.revalidate();
        repaint();
    }

    private void handle_exit() {
        int n = JOptionPane.showConfirmDialog(this,
                "您确认要退出系统吗?", "确认对话框", JOptionPane.YES_NO_OPTION);
        if(n == JOptionPane.YES_OPTION) {
            this.dispose();
            LoginGUI logingui = new LoginGUI();//返回登录界面
            logingui.setVisible(true);
        }
    }

    public void initView(){
        menu = new DefaultMutableTreeNode("菜单");
        myInformationNode = new DefaultMutableTreeNode("我的信息");  //我的信息
        informationNode = new DefaultMutableTreeNode("查询信息");//查询信息
        changeInfoNode = new DefaultMutableTreeNode("修改信息");//修改信息
        changePassNode = new DefaultMutableTreeNode("修改密码");  //修改密码

        gradeManageNode = new DefaultMutableTreeNode("成绩管理"); //成绩管理
        gradeArrangeNode = new DefaultMutableTreeNode("管理学生成绩");  //管理学生成绩
        gradeSystemNode = new DefaultMutableTreeNode("成绩录入管理");  //成绩录入管理

        examManageNode = new DefaultMutableTreeNode("考试管理"); // 考试管理
        examArrangeNode = new DefaultMutableTreeNode("安排考试"); //安排考试
        examSearchNode = new DefaultMutableTreeNode("查询考试"); //查询考试

        courseManageNode = new DefaultMutableTreeNode("课程管理");  //课程管理
        courseAddNode = new DefaultMutableTreeNode("添加课程"); //添加课程
        courseSearchNode = new DefaultMutableTreeNode("查询课程");  //查询课程
        courseArrangeNode = new DefaultMutableTreeNode("安排课程");  //安排课程

        stuTeaManageNode = new DefaultMutableTreeNode("师生管理");  //师生管理
        stuArrNode = new DefaultMutableTreeNode("学生管理");  // 学生管理
        stuAddNode = new DefaultMutableTreeNode("添加学生");  // 添加学生
        teaArrNode = new DefaultMutableTreeNode("老师管理");  // 老师管理
        teaAddNode = new DefaultMutableTreeNode("添加老师");  // 添加老师

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        myInformationNode.add(informationNode);
        myInformationNode.add(changeInfoNode);
        myInformationNode.add(changePassNode);
        menu.add(myInformationNode);

        gradeManageNode.add(gradeArrangeNode);
        gradeManageNode.add(gradeSystemNode);
        menu.add(gradeManageNode);

        examManageNode.add(examArrangeNode);
        examManageNode.add(examSearchNode);
        menu.add(examManageNode);

        courseManageNode.add(courseAddNode);
        courseManageNode.add(courseSearchNode);
        courseManageNode.add(courseArrangeNode);
        menu.add(courseManageNode);

        stuTeaManageNode.add(stuAddNode);
        stuTeaManageNode.add(stuArrNode);
        stuTeaManageNode.add(teaAddNode);
        stuTeaManageNode.add(teaArrNode);
        menu.add(stuTeaManageNode);

        tree = new JTree(menu);
        tree.setFont(new Font("楷体", Font.PLAIN, 20));
        tree.setBounds(0, 0, 230, 535);
        tree.addTreeSelectionListener(this);
        contentPane.add(tree);

        statusbar = new JToolBar();
        statusbar.setFont(new Font("宋体", Font.PLAIN, 20));
        statusbar.setSize(1290, 25);
        statusbar.setLocation(0, 660);
        statusbar.setLayout(new BorderLayout());

        btn_exit = new JButton("退出");
        btn_exit.setBackground(new Color(0, 191, 255));
        btn_exit.setForeground(Color.WHITE);
        btn_exit.setFont(new Font("黑体", Font.PLAIN, 20));
        btn_exit.setBounds(20, 560, 97, 33);
        contentPane.add(btn_exit);

        btn_dbBackup = new JButton("数据库备份");
        btn_dbBackup.setBackground(new Color(0, 191, 255));
        btn_dbBackup.setForeground(Color.WHITE);
        btn_dbBackup.setFont(new Font("黑体", Font.PLAIN, 20));
        btn_dbBackup.setBounds(20, 610, 140, 33);
        contentPane.add(btn_dbBackup);

        lbl_userInfo = new JLabel();
        lbl_userInfo.setText("[用户名:]" + admin.getMno() + " [用户姓名:]" + HandleSQL.getAdnName(admin.getMno()));
        lbl_userInfo.setFont(new Font("宋体", Font.PLAIN, 20));
        statusbar.add(lbl_userInfo, BorderLayout.WEST);

        lbl_operator = new JLabel();
        lbl_operator.setHorizontalAlignment(JLabel.CENTER);
        lbl_operator.setText("[当前操作:]");
        lbl_operator.setFont(new Font("宋体", Font.PLAIN, 20));
        statusbar.add(lbl_operator, BorderLayout.CENTER);

        sdf = new SimpleDateFormat("[时间:] yyyy/MM/dd HH:mm:ss");
        lbl_time = new JLabel();
        lbl_time.setText(sdf.format(new Date()));
        lbl_time.setFont(new Font("宋体", Font.PLAIN, 20));
        statusbar.add(lbl_time, BorderLayout.EAST);

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                lbl_time.setText(sdf.format(new Date()));
            }

        }, 0, 1000);
        contentPane.add(statusbar, BorderLayout.SOUTH);

        panel = new JPanel();
        panel.setBounds(230, 0, 1200, 550);
        contentPane.add(panel);
    }
}
