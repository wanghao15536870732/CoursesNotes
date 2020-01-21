package frame.teacher;

import javax.swing.*;

import frame.admin.BaseJPanel;
import handle.HandleSQL;
import util.DialogUtil;
import util.FileUtil;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class SelectGradeJPanel extends BaseJPanel {
    private String Tno;//教师编号
    private JTextField snoField;//学号
    private JComboBox<String> classComboBox;//选择班级
    private JComboBox<String> courseComboBox;//选择课程
    private JTable gradetable; //成绩表格
    private JButton btn_select; //查询按钮
    private JButton btn_ASC; //升序排列按钮
    private JButton btn_DESC; //降序排列按钮
    private JButton btn_AVG; //平均分按钮
    private JButton exportButton; //导出按钮
    private JTextField txtField_AVG;//显示平均分

    private String Sno;//获取学号
    private String Sclass;//获取班级.
    private String Cname;//获取课程名
    private Object[][] data;
    private JScrollPane pane = new JScrollPane();

    private static String selectClassGradeSQL = "select student.Sno,student.Sname,course.Cno,course.Cname,csgrade.Grade,course.Ccredit " +
            "from Student,Course,Csgrade " +
            "where Student.Sno=Csgrade.Sno and " +
            "Csgrade.Cno=Course.Cno and " +
            "Student.Sclass=? and " +
            "course.Cname=?;";
    private static String selectClassGradeSQLASC = "select student.Sno,student.Sname,course.Cno,course.Cname,csgrade.Grade,course.Ccredit " +
            "from Student,Course,Csgrade " +
            "where Student.Sno=Csgrade.Sno and " +
            "Csgrade.Cno=Course.Cno and " +
            "Student.Sclass=? and " +
            "course.Cname=? " +
            "order by Grade ASC;";
    private static String selectClassGradeSQLDESC = "select student.Sno,student.Sname,course.Cno,course.Cname,csgrade.Grade,course.Ccredit " +
            "from Student,Course,Csgrade " +
            "where Student.Sno=Csgrade.Sno and " +
            "Csgrade.Cno=Course.Cno and " +
            "Student.Sclass=? and " +
            "course.Cname=? " +
            "order by Grade DESC;";

    private static String selectClassAVGSQL = "select AVG(Grade) " +
            "from (select student.Sno,student.Sname,course.Cno,course.Cname,csgrade.Grade,course.Ccredit " +
            "from Student,Course,Csgrade " +
            "where Student.Sno=Csgrade.Sno and " +
            "Csgrade.Cno=Course.Cno and " +
            "Student.Sclass=? and " +
            "course.Cname=?) as classGrade(Sno,Sname,Cno,Cname,Grade,Ccredit);";

    SelectGradeJPanel(String Tno, JFrame main) {
        super("查询成绩");
        this.Tno = Tno;
        setLayout(null);
        initComponent();//界面设计
        addEventHandler(main);//事件处理
        setVisible(true);
    }

    private void initComponent() {//界面设计
        JLabel snolabel = new JLabel("学号:");
        snolabel.setFont(new Font("楷体", Font.PLAIN, 20));
        snolabel.setBounds(105, 105, 50, 18);
        add(snolabel);

        snoField = new JTextField();
        snoField.setFont(new Font("楷体", Font.PLAIN, 20));
        snoField.setBounds(157, 103, 115, 26);
        add(snoField);
        snoField.setColumns(10);

        classComboBox = new JComboBox<>();
        classComboBox.setFont(new Font("楷体", Font.PLAIN, 20));
        String[] classList = HandleSQL.getAllTeachingCLno(Tno);
        DefaultComboBoxModel<String> classModel = new DefaultComboBoxModel<>(classList);
        classComboBox.setModel(classModel);
        classComboBox.setBounds(348, 103, 150, 26);
        add(classComboBox);

        btn_select = new JButton("查询");
        btn_select.setForeground(Color.WHITE);
        btn_select.setBackground(new Color(0, 192, 255));
        btn_select.setFont(new Font("楷体", Font.PLAIN, 20));
        btn_select.setBounds(806, 101, 110, 30);
        add(btn_select);

        exportButton = new JButton("导出");
        exportButton.setForeground(Color.WHITE);
        exportButton.setBackground(new Color(0, 192, 255));
        exportButton.setFont(new Font("楷体", Font.PLAIN, 20));
        exportButton.setBounds(806, 40, 110, 30);
        add(exportButton);

        btn_ASC = new JButton("升序排列");
        btn_ASC.setForeground(Color.WHITE);
        btn_ASC.setBackground(new Color(0, 192, 255));
        btn_ASC.setFont(new Font("楷体", Font.PLAIN, 20));
        btn_ASC.setBounds(130, 555, 120, 32);
        add(btn_ASC);

        btn_DESC = new JButton("降序排列");
        btn_DESC.setForeground(Color.WHITE);
        btn_DESC.setBackground(new Color(0, 192, 255));
        btn_DESC.setFont(new Font("楷体", Font.PLAIN, 20));
        btn_DESC.setBounds(400, 555, 120, 32);
        add(btn_DESC);

        gradetable = new JTable();
        gradetable.setBounds(80, 165, 900, 361);
        add(gradetable);

        JLabel classLabel = new JLabel("班级:");
        classLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        classLabel.setBounds(193, 105, 60, 18);
        add(classLabel);

        JLabel lb_course = new JLabel("课程:");
        lb_course.setFont(new Font("楷体", Font.PLAIN, 20));
        lb_course.setBounds(533, 105, 50, 18);
        add(lb_course);

        courseComboBox = new JComboBox<>();
        courseComboBox.setFont(new Font("楷体", Font.PLAIN, 20));
        String[] courseLists = HandleSQL.getAllTeachingCourse(Tno);
        DefaultComboBoxModel<String> courseModel = new DefaultComboBoxModel<>(courseLists);
        courseComboBox.setModel(courseModel);
        courseComboBox.setBounds(585, 103, 190, 28);
        add(courseComboBox);

        btn_AVG = new JButton("平均分:");
        btn_AVG.setForeground(Color.WHITE);
        btn_AVG.setFont(new Font("楷体", Font.PLAIN, 20));
        btn_AVG.setBounds(670, 555, 120, 30);
        btn_AVG.setBackground(new Color(0, 192, 255));
        add(btn_AVG);

        txtField_AVG = new JTextField();
        txtField_AVG.setText("0");
        txtField_AVG.setFont(new Font("楷体", Font.PLAIN, 20));
        txtField_AVG.setBounds(830, 555, 100, 32);
        add(txtField_AVG);
        txtField_AVG.setColumns(10);
    }

    private void addEventHandler(JFrame main) {//事件处理
        btn_select.addActionListener(e -> {
            Sno = snoField.getText();//获取学号
            Sclass = classComboBox.getSelectedItem().toString();//获取班号
            Cname = courseComboBox.getSelectedItem().toString();//获取课程
             if (!Sno.equals("")) {//学号不为空，即查询学生个人成绩
                remove(gradetable);
                boolean result = HandleSQL.JudgeStu(Sno);//判断该学生是否存在
                if (result) {    //该学生存在
                    data = HandleSQL.getStuGrade(Sno);
                    Object name[] = {"学号", "姓名", "课程号", "课程名", "成绩", "学分"};
                    remove(gradetable);
                    gradetable = new JTable(data, name);
                    setTable(gradetable);
                    validate();
                } else {//该学生不存在
                    DialogUtil.showWarning("警告消息", "该学生不存在,请重新输入学号!");
                }
            } else {
                 if (classComboBox.getSelectedIndex() == 0) {
                     DialogUtil.showWarning("提示信息", "请先选择班级！");
                 } else if (courseComboBox.getSelectedIndex() == 0) {
                     DialogUtil.showWarning("提示信息", "请选择课程！");
                 }
                remove(gradetable);
                data = HandleSQL.getClassGrade(Sclass, Cname, selectClassGradeSQL);
                Object[] name = {"学号", "姓名", "课程号", "课程名", "成绩", "学分"};
                remove(gradetable);
                gradetable = new JTable(data, name);
                setTable(gradetable);
                validate();
            }
        });

        //升序排列处理
        btn_ASC.addActionListener(e -> {
            remove(gradetable);
            Sclass = classComboBox.getSelectedItem().toString();//获取课程名
            Cname = courseComboBox.getSelectedItem().toString();//获取课程
            data = HandleSQL.getClassGrade(Sclass, Cname, selectClassGradeSQLASC);
            Object[] name = {"学号", "姓名", "课程号", "课程名", "成绩", "学分"};
            remove(gradetable);
            gradetable = new JTable(data, name);
            setTable(gradetable);
            validate();
        });

        //降序排列处理
        btn_DESC.addActionListener(e -> {
            remove(gradetable);
            Sclass = classComboBox.getSelectedItem().toString();//获取课程名
            Cname = courseComboBox.getSelectedItem().toString();//获取课程
            data = HandleSQL.getClassGrade(Sclass, Cname, selectClassGradeSQLDESC);
            Object[] name = {"学号", "姓名", "课程号", "课程名", "成绩", "学分"};
            remove(gradetable);
            gradetable = new JTable(data, name);
            setTable(gradetable);
            validate();
        });

        //平均分
        btn_AVG.addActionListener(arg0 -> {
            Sclass = classComboBox.getSelectedItem().toString();//获取课程名
            String result = HandleSQL.getClassAVG(Sclass, Cname, selectClassAVGSQL);
            if (result == null) {
                txtField_AVG.setText("0.0");
            } else {
                txtField_AVG.setText(result);
            }
            txtField_AVG.setEnabled(false);
        });

        exportButton.addActionListener(e -> {
            FileUtil.tableToFile(main, gradetable);
        });
    }

    //设置表格
    public void setTable(JTable table) {
        remove(pane);
        table.setFont(new Font("楷体", Font.PLAIN, 18));
        table.setBounds(80, 165, 900, 361);
        table.setRowHeight(20);

        table.getColumnModel().getColumn(0).setPreferredWidth(5);
        table.getColumnModel().getColumn(1).setPreferredWidth(5);
        table.getColumnModel().getColumn(2).setPreferredWidth(20);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(4).setPreferredWidth(5);
        table.getColumnModel().getColumn(5).setPreferredWidth(5);

        table.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 18));
        table.getTableHeader().setBackground(Color.gray);
        table.getTableHeader().setForeground(Color.white);
        JScrollPane scrollPanel = new JScrollPane(table);
        scrollPanel.setViewportView(table);
        scrollPanel.setBounds(80, 165, 900, 361);
        pane = scrollPanel;
        add(scrollPanel);
    }
}
