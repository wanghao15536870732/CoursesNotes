package frame.student;

import bean.Student;
import frame.admin.BaseJPanel;
import handle.HandleStu;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * 个人信息界面
 */
public class StuInfoJPanel extends BaseJPanel {

    private Student student;
    private JLabel nameLabel; //姓名
    private JLabel snoLabel; //学号
    private JLabel sexLabel;  //性别
    private JLabel ageLabel; //年龄
    private JLabel collegesLabel; //学院
    private JLabel majorLabel; //专业
    private JLabel classLabel; //班级
    private JLabel telLabel; //电话号码
    private JLabel emailLabel; //电子邮箱

    public StuInfoJPanel(Student student) {
        super("基本信息");
        setLayout(null);
        setVisible(true);
        this.student = student;

        JLabel lblNewLabel_1 = new JLabel("姓名：");
        lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(70, 120, 72, 18);
        add(lblNewLabel_1);

        nameLabel = new JLabel(" ");
        nameLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        nameLabel.setBounds(130, 120, 72, 18);
        add(nameLabel);

        JLabel lblNewLabel_2 = new JLabel("学院：");
        lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_2.setBounds(70, 300, 72, 18);
        add(lblNewLabel_2);

        collegesLabel = new JLabel(" ");
        collegesLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        collegesLabel.setBounds(130, 300, 100, 18);
        add(collegesLabel);

        JLabel lblNewLabel_5 = new JLabel("学号：");
        lblNewLabel_5.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_5.setBounds(70, 165, 72, 18);
        add(lblNewLabel_5);

        snoLabel = new JLabel(" ");
        snoLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        snoLabel.setBounds(130, 165, 114, 18);
        add(snoLabel);

        JLabel lblNewLabel_7 = new JLabel("性别：");
        lblNewLabel_7.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_7.setBounds(70, 210, 54, 18);
        add(lblNewLabel_7);

        sexLabel = new JLabel(" ");
        sexLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        sexLabel.setBounds(130, 210, 41, 18);
        add(sexLabel);

        JLabel lblNewLabel_9 = new JLabel("年龄：");
        lblNewLabel_9.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_9.setBounds(70, 255, 72, 18);
        add(lblNewLabel_9);

        ageLabel = new JLabel(" ");
        ageLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        ageLabel.setBounds(130, 257, 72, 18);
        add(ageLabel);

        JLabel lblNewLabel_11 = new JLabel("专业：");
        lblNewLabel_11.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_11.setBounds(70, 345, 72, 18);
        add(lblNewLabel_11);

        majorLabel = new JLabel(" ");
        majorLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        majorLabel.setBounds(130, 347, 159, 18);
        add(majorLabel);

        JLabel lblNewLabel_13 = new JLabel("班级：");
        lblNewLabel_13.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_13.setBounds(340, 120, 72, 18);
        add(lblNewLabel_13);

        classLabel = new JLabel(" ");
        classLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        classLabel.setBounds(405, 120, 100, 18);
        add(classLabel);

        JLabel lblNewLabel_15 = new JLabel("联系方式：");
        lblNewLabel_15.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_15.setBounds(340, 165, 100, 18);
        add(lblNewLabel_15);

        telLabel = new JLabel(" ");
        telLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        telLabel.setBounds(441, 165, 114, 18);
        add(telLabel);

        JLabel lblNewLabel_17 = new JLabel("电子邮箱:");
        lblNewLabel_17.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_17.setBounds(340, 210, 100, 18);
        add(lblNewLabel_17);

        emailLabel = new JLabel(" ");
        emailLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        emailLabel.setBounds(441, 212, 170, 18);
        add(emailLabel);

        student = HandleStu.getStuInfo(student.getSno());
        nameLabel.setText(student.getSname());
        snoLabel.setText(student.getSno());
        sexLabel.setText(student.getSsex());
        ageLabel.setText(String.valueOf(student.getSage()));
        collegesLabel.setText(student.getSdept());
        majorLabel.setText(student.getSdomin());
        classLabel.setText(student.getSclass());
        telLabel.setText(student.getStel());
        emailLabel.setText(student.getSemil());
    }
}
