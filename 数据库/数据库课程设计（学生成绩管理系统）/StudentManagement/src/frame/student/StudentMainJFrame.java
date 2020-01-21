package frame.student;

import bean.Student;
import com.sun.corba.se.impl.orbutil.graph.Graph;
import frame.BaseJFrame;
import gui.DrawBack;
import gui.LoginGUI;
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
public class StudentMainJFrame extends BaseJFrame implements TreeSelectionListener {

    private Student student;
    private JPanel contentPane;
    private DefaultMutableTreeNode menu; //菜单
    private DefaultMutableTreeNode myInformationNode;  //我的信息
    private DefaultMutableTreeNode informationNode; //查询信息
    private DefaultMutableTreeNode changeInfoNode;  //修改信息
    private DefaultMutableTreeNode changePassNode;  //修改密码

    private DefaultMutableTreeNode myGradeNode; //我的成绩
    private DefaultMutableTreeNode gradeNode;  //查询成绩

    private DefaultMutableTreeNode myTestNode; // 我的考试
    private DefaultMutableTreeNode testNode; //查询考试

    private DefaultMutableTreeNode myCourseNode;  //我的课程
    private DefaultMutableTreeNode courseNode;  //查询课程
    private JTree tree; //左侧树结构
    private JPanel panel; //右侧面板
    private SimpleDateFormat sdf;
    private Timer timer;
    private JToolBar statusbar; //状态栏
    private JLabel lbl_userinfo; //用户信息
    private JLabel lbl_operator; //当前操作
    private JLabel lbl_time; //时间显示
    private JButton btn_exit; //退出按钮

    /**
     * Create the frame.
     */
    public StudentMainJFrame(Student student) {
        super("学生主界面","./src/pictures/student4.png");
        this.student = student;
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
        if (node == informationNode) {  //查询信息
            EventQueue.invokeLater(() -> {
                JPanel stuInfoPanel = new StuInfoJPanel(student);
                replaceJPanel(stuInfoPanel);
            });
        } else if (node == changeInfoNode) {  //修改信息
            EventQueue.invokeLater(() -> {
                JPanel changeInfoPanel = new StuChangeInfoJPanel(student.getSno());
                replaceJPanel(changeInfoPanel);
            });
        } else if (node == changePassNode) {  //修改密码
            EventQueue.invokeLater(() -> {
                JPanel changePassPanel = new ChangePasswordJPanel(student.getSno(),this);
                replaceJPanel(changePassPanel);
            });
        } else if (node == gradeNode) {  //查询成绩
            EventQueue.invokeLater(() -> {
                JPanel gradeSearchPanel = new GradeSearchJPanel(student.getSno(),this);
                replaceJPanel(gradeSearchPanel);
            });
        } else if (node == testNode) {  //查询考试
            EventQueue.invokeLater(() -> {
                MyExamSearchJPanel panel = new MyExamSearchJPanel(student.getSno());
                replaceJPanel(panel);
            });
        } else if (node == courseNode) {  //查询课程
            EventQueue.invokeLater(new Runnable(){
                @Override
                public void run() {
                    JPanel courseSearchPanel = new CourseSearchJPanel(student.getSno());
                    courseSearchPanel.setBounds(230, 0, 1190, 660);
                    panel = courseSearchPanel;
                    contentPane.add(panel);
                    contentPane.revalidate();
                    repaint();
                }
            });
        }
    }

    public void replaceJPanel(JPanel newPanel) {
        panel = newPanel;
        newPanel.setBounds(230, 0, 1190, 660);
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
        informationNode = new DefaultMutableTreeNode("查询信息");
        ; //查询信息
        changeInfoNode = new DefaultMutableTreeNode("修改信息");
        ;  //修改信息
        changePassNode = new DefaultMutableTreeNode("修改密码");
        ;  //修改密码

        myGradeNode = new DefaultMutableTreeNode("我的成绩");
        ; //我的成绩
        gradeNode = new DefaultMutableTreeNode("查询成绩");  //查询成绩

        myTestNode = new DefaultMutableTreeNode("我的考试"); // 考试管理
        testNode = new DefaultMutableTreeNode("查询考试"); //查询考试

        myCourseNode = new DefaultMutableTreeNode("我的课程");  //我的课程
        courseNode = new DefaultMutableTreeNode("查询课程");  //查询课程

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        myInformationNode.add(informationNode);
        myInformationNode.add(changeInfoNode);
        myInformationNode.add(changePassNode);
        menu.add(myInformationNode);

        myGradeNode.add(gradeNode);
        menu.add(myGradeNode);

        myTestNode.add(testNode);
        menu.add(myTestNode);

        myCourseNode.add(courseNode);
        menu.add(myCourseNode);

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

        lbl_userinfo = new JLabel();
        lbl_userinfo.setText("[用户名:]" + student.getSno() + " [用户姓名:]" + student.getSname());
        lbl_userinfo.setFont(new Font("宋体", Font.PLAIN, 20));
        statusbar.add(lbl_userinfo, BorderLayout.WEST);

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
