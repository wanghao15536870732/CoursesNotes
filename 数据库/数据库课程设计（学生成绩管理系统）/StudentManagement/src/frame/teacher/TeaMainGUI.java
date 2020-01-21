package frame.teacher;

import bean.Teacher;
import frame.BaseJFrame;
import gui.LoginGUI;
import handle.HandleSQL;
import util.GradeUtil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 学生主界面
 */
public class TeaMainGUI extends BaseJFrame implements TreeSelectionListener {

    private Teacher teacher;
    private JPanel contentPane;
    private DefaultMutableTreeNode menu; //菜单
    private DefaultMutableTreeNode myInformationNode;  //我的信息
    private DefaultMutableTreeNode informationNode; //查询信息
    private DefaultMutableTreeNode changeInfoNode;  //修改信息
    private DefaultMutableTreeNode changePassNode;  //修改密码

    private DefaultMutableTreeNode studentGradeNode;  //学生成绩管理
    private DefaultMutableTreeNode addGradeNode;  //录入成绩
    private DefaultMutableTreeNode searchGradeNode;  //查询成绩
    private DefaultMutableTreeNode changeGradeNode;  //修改成绩

    private DefaultMutableTreeNode examArrangeNode; //考试管理
    private DefaultMutableTreeNode examSearchNode;// 查看考试信息

    private JTree tree; //左侧树结构
    private JPanel panel; //右侧面板
    private SimpleDateFormat sdf;
    private Timer timer;
    private JToolBar statusbar; //状态栏
    private JLabel userInfo; //用户信息
    private JLabel lbl_operator; //当前操作
    private JLabel lbl_time; //时间显示
    private JButton btn_exit; //退出按钮

    /**
     * Create the frame.
     */
    public TeaMainGUI(Teacher teacher) {//设置窗体图
        super("教师主界面", "./src/pictures/teacher.png");
        this.teacher = teacher;
        initView();
        btn_exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handle_exit();
            }
        });
    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {

        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                tree.getLastSelectedPathComponent();
        contentPane.remove(panel);
        lbl_operator.setText("[当前操作:]" + node.toString());
        contentPane.remove(panel);
        if (node == informationNode) {
            EventQueue.invokeLater(() -> {  //查看信息
                TeaInfoJPanel panel = new TeaInfoJPanel(teacher.getTno());
                replaceJPanel(panel);
            });
        } else if (node == changeInfoNode) {  //修改信息
            EventQueue.invokeLater(() -> {
                ChangeInfoJPanel panel = new ChangeInfoJPanel(teacher.getTno());
                replaceJPanel(panel);
            });
        } else if (node == changePassNode) {  //修改密码
            EventQueue.invokeLater(() -> {
                ChangePassJPanel panel = new ChangePassJPanel(teacher.getTno(),this);
                replaceJPanel(panel);
            });
        } else if (node == addGradeNode) {  //添加成绩
            EventQueue.invokeLater(() -> {
                boolean result = GradeUtil.checkGradeSystem();
                if (!result) {
                    JPanel gradeCloseJPanel = new JPanel();
                    gradeCloseJPanel.setBounds(238, 0, 1200, 650);
                    gradeCloseJPanel.setLayout(null);
                    JLabel lblNewLabel = new JLabel("成绩录入功能暂未开启！");
                    lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 40));
                    lblNewLabel.setBounds(340, 295, 440, 60);
                    gradeCloseJPanel.add(lblNewLabel);
                    replaceJPanel(gradeCloseJPanel);
                } else {
                    InsertGradeJPanel panel = new InsertGradeJPanel(teacher.getTno());
                    replaceJPanel(panel);
                }
            });
        } else if (node == searchGradeNode) {   //查询成绩
            EventQueue.invokeLater(() -> {
                SelectGradeJPanel panel = new SelectGradeJPanel(teacher.getTno(),this);
                replaceJPanel(panel);
            });
        } else if (node == changeGradeNode) {
            EventQueue.invokeLater(() -> {
                UpdateGradeJPanel panel = new UpdateGradeJPanel();
                replaceJPanel(panel);
            });
        } else if (node == examSearchNode) {
            EventQueue.invokeLater(() -> {
                SelectExamInfoJPanel panel = new SelectExamInfoJPanel(teacher.getTno());
                replaceJPanel(panel);
            });
        }
    }

    public void replaceJPanel(JPanel newPanel) {
        panel = newPanel;
        newPanel.setBounds(230, 0, 1200, 660);
        contentPane.add(newPanel);
        contentPane.revalidate();
        repaint();
    }

    private void handle_exit() {
        int n = JOptionPane.showConfirmDialog(this,
                "您确认要退出系统吗?", "确认对话框", JOptionPane.YES_NO_OPTION);
        if (n == JOptionPane.YES_OPTION) {
            this.dispose();
            LoginGUI logingui = new LoginGUI();//返回登录界面
            logingui.setVisible(true);
        }
    }


    public void initView() {
        menu = new DefaultMutableTreeNode("菜单");
        myInformationNode = new DefaultMutableTreeNode("信息管理");  //我的信息
        informationNode = new DefaultMutableTreeNode("查询个人信息");
        changeInfoNode = new DefaultMutableTreeNode("修改个人信息");
        changePassNode = new DefaultMutableTreeNode("修改密码");

        studentGradeNode = new DefaultMutableTreeNode("成绩管理");  //学生成绩管理
        addGradeNode = new DefaultMutableTreeNode("录入学生成绩");  //录入成绩
        searchGradeNode = new DefaultMutableTreeNode("查询学生成绩");  //查询成绩
        changeGradeNode = new DefaultMutableTreeNode("修改学生成绩");  //修改成绩

        examArrangeNode = new DefaultMutableTreeNode("考试管理");
        examSearchNode = new DefaultMutableTreeNode("查询考试信息");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        myInformationNode.add(informationNode);
        myInformationNode.add(changeInfoNode);
        myInformationNode.add(changePassNode);
        menu.add(myInformationNode);

        studentGradeNode.add(addGradeNode);
        studentGradeNode.add(searchGradeNode);
        studentGradeNode.add(changeGradeNode);
        menu.add(studentGradeNode);

        examArrangeNode.add(examSearchNode);
        menu.add(examArrangeNode);

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

        userInfo = new JLabel();
        userInfo.setText("[用户名:]" + teacher.getTno() + " [用户姓名:]" + HandleSQL.getTeaName(teacher.getTno()));
        userInfo.setFont(new Font("宋体", Font.PLAIN, 20));
        statusbar.add(userInfo, BorderLayout.WEST);

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
        panel.setBounds(230, 0, 900, 660);
        contentPane.add(panel);
    }
}
