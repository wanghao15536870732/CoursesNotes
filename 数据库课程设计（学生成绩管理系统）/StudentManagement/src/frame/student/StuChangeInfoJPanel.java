package frame.student;

import bean.Student;
import frame.admin.BaseJPanel;
import handle.HandleStu;
import util.DialogUtil;
import javax.swing.*;
import java.awt.*;

/**
 * 修改个人信息界面
 */
public class StuChangeInfoJPanel extends BaseJPanel {

    private String sno;
    private JTextField nameField;
    private JTextField snoField;
    private JComboBox<String> sexComboBox;
    private JTextField ageField;
    private JTextField deptField;
    private JTextField majorField;
    private JTextField classField;
    private JTextField telField;
    private JTextField emailField;
    private JButton sureButton;

    public StuChangeInfoJPanel(String sno){
        super("修改信息");
        setLayout(null);
        setVisible(true);
        this.sno = sno;
        initView();
        Student student = HandleStu.getStuInfo(sno);
        nameField.setText(student.getSname());
        snoField.setText(student.getSno());
        if (student.getSsex().equals("男")){
            sexComboBox.setSelectedIndex(0);
        }else {
            sexComboBox.setSelectedIndex(1);
        }
        ageField.setText(String.valueOf(student.getSage()));
        deptField.setText(student.getSdept());
        majorField.setText(student.getSdomin());
        classField.setText(student.getSclass());
        telField.setText(student.getStel());
        emailField.setText(student.getSemil());
        sureButton = new JButton("修改");
        sureButton.setFont(new Font("楷体", Font.PLAIN, 18));
        sureButton.setBounds(200,500,115,30);
        add(sureButton);
        sureButton.addActionListener(e -> {
            String name = nameField.getText();
            String sno1 = student.getSno();
            String sex = sexComboBox.getSelectedItem().toString();
            int age = Integer.parseInt(ageField.getText());
            String dept = deptField.getText();
            String major = majorField.getText();
            String sclass = classField.getText();
            String email = emailField.getText();
            String tel = telField.getText();
            if (name.equals(student.getSname()) && sno1.equals(student.getSno())
                    && sex.equals(student.getSsex()) && age == student.getSage()
                    && dept.equals(student.getSdept()) && major.equals(student.getSdomin())
                    && sclass.equals(student.getSclass()) && email.equals(student.getSemil())
                    && tel.equals(student.getStel())){
                DialogUtil.showMessage("提示信息","还没有做任何修改！");
            }else if(nameField.getText().equals("") || ageField.getText().equals("") ||
                    emailField.getText().equals("") || telField.getText().equals("")){
                DialogUtil.showWarning("提示信息","必要信息不能为空！");
            }else {
                Student newStu = new Student(sno1,name,sex,age,dept,major,sclass,tel,email);
                boolean result = HandleStu.updateStuInfo(newStu);
                if (result){
                    DialogUtil.showMessage("提示信息","信息修改成功！");
                }else {
                    DialogUtil.showMessage("提示信息","信息修改失败，检查信息是否有误！");
                }
            }
        });
    }

    public void initView(){
        JLabel lblNewLabel_1 = new JLabel("姓名：");
        lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(70, 160, 72, 18);
        add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("院系：");
        lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_2.setBounds(70, 340, 72, 18);
        add(lblNewLabel_2);

        JLabel lblNewLabel_5 = new JLabel("学号：");
        lblNewLabel_5.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_5.setBounds(70, 205, 72, 18);
        add(lblNewLabel_5);

        JLabel lblNewLabel_7 = new JLabel("性别：");
        lblNewLabel_7.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_7.setBounds(70, 250, 54, 18);
        add(lblNewLabel_7);

        JLabel lblNewLabel_9 = new JLabel("年龄：");
        lblNewLabel_9.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_9.setBounds(70, 295, 72, 18);
        add(lblNewLabel_9);

        JLabel lblNewLabel_11 = new JLabel("专业：");
        lblNewLabel_11.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_11.setBounds(70, 385, 72, 18);
        add(lblNewLabel_11);

        JLabel lblNewLabel_13 = new JLabel("班级：");
        lblNewLabel_13.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_13.setBounds(340, 160, 72, 18);
        add(lblNewLabel_13);

        JLabel lblNewLabel_15 = new JLabel("联系方式：");
        lblNewLabel_15.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_15.setBounds(340, 205, 100, 18);
        add(lblNewLabel_15);

        JLabel lblNewLabel_17 = new JLabel("电子邮箱：");
        lblNewLabel_17.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_17.setBounds(340, 250, 120, 18);
        add(lblNewLabel_17);

        nameField = new JTextField();
        nameField.setFont(new Font("楷体", Font.PLAIN, 18));
        nameField.setBounds(130, 155, 160, 28);
        add(nameField);
        nameField.setColumns(10);

        snoField = new JTextField();
        snoField.setFont(new Font("楷体", Font.PLAIN, 18));
        snoField.setBounds(130, 200, 160, 28);
        add(snoField);
        snoField.setColumns(10);
        snoField.setEnabled(false);

        sexComboBox = new JComboBox<>();
        sexComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        sexComboBox.setBounds(130, 245, 160, 28);
        sexComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"男","女"}));
        add(sexComboBox);

        ageField = new JTextField();
        ageField.setFont(new Font("楷体", Font.PLAIN, 18));
        ageField.setBounds(130, 290, 160, 28);
        add(ageField);
        ageField.setColumns(10);

        deptField = new JTextField();
        deptField.setFont(new Font("楷体", Font.PLAIN, 18));
        deptField.setBounds(130, 335, 160, 28);
        deptField.setEnabled(false);
        add(deptField);
        deptField.setColumns(10);

        majorField = new JTextField();
        majorField.setFont(new Font("楷体", Font.PLAIN, 18));
        majorField.setBounds(130, 380, 160, 28);
        majorField.setEnabled(false);
        add(majorField);
        majorField.setColumns(10);

        classField = new JTextField();
        classField.setFont(new Font("楷体", Font.PLAIN, 18));
        classField.setBounds(402, 155, 160, 28);
        classField.setEnabled(false);
        add(classField);
        classField.setColumns(10);

        telField = new JTextField();
        telField.setFont(new Font("楷体", Font.PLAIN, 18));
        telField.setBounds(432, 200, 160, 28);
        add(telField);
        telField.setColumns(10);

        emailField = new JTextField();
        emailField.setFont(new Font("楷体", Font.PLAIN, 18));
        emailField.setBounds(432, 245, 180, 28);
        add(emailField);
        emailField.setColumns(10);
    }
}
