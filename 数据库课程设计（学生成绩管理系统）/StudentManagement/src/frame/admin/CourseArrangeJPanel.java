package frame.admin;

import bean.Teaching;
import util.ClassUtil;
import util.CourseUtil;
import util.DialogUtil;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CourseArrangeJPanel extends BaseJPanel {
    private JTextField enoTextField;
    private JTextField ePositionField;
    private JComboBox<String> deptComboBox,majorComboBox, classComboBox;
    private JButton sureButton;
    private JLabel lblNewLabel;
    private JTextField cPeriodField;
    private JLabel lblNewLabel_1;
    private JComboBox<String> teacherComboBox;
    private JComboBox courseComboBox;
    private JLabel lblNewLabel_2;
    private JTextField timeField_1;
    private JLabel lblNewLabel_3;
    private JTextField timeField_2;
    private JLabel lblNewLabel_4;
    private JComboBox<String> comboBox;
    private JLabel lblNewLabel_5;
    private JComboBox<String> comboBox_1;

    public CourseArrangeJPanel() {
        super("安排课程");
        setLayout(null);
        initView();
        deptComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()){
                    case ItemEvent.SELECTED:
                        majorComboBox.setModel(new DefaultComboBoxModel<>(
                                ClassUtil.getMajorByDept(e.getItem().toString())
                        ));
                        break;
                }
            }
        });

        majorComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()){
                    case ItemEvent.SELECTED:
                        classComboBox.setModel(new DefaultComboBoxModel<>(
                                ClassUtil.getClassByMajor(e.getItem().toString())
                        ));
                        break;
                }
            }
        });
        sureButton.addActionListener(e -> {
            if (ePositionField.getText().equals("") || cPeriodField.getText().equals("") ||
                    timeField_1.getText().equals("") || timeField_2.getText().equals("")){
                DialogUtil.showMessage("提示信息","请输入完整信息！");
            }else{
                String CLno = classComboBox.getSelectedItem().toString();
                String Cno = ClassUtil.getCourseByCname(courseComboBox.getSelectedItem().toString());
                String Tno = ClassUtil.getTnoByCname(teacherComboBox.getSelectedItem().toString());
                String position = ePositionField.getText();
                String period = cPeriodField.getText();
                String time_1 = timeField_1.getText();
                String time_2 = timeField_2.getText();
                String cYear = comboBox.getSelectedItem().toString();
                String cTerm = comboBox_1.getSelectedItem().toString();
                Teaching teaching = new Teaching(Tno,Cno,position,period,time_1,time_2,cYear,cTerm);
                boolean result = CourseUtil.insertCourse(teaching);//////安排课程
                if (result){
                    DialogUtil.showMessage("提示信息","安排成功！");
                }else {
                    DialogUtil.showMessage("提示信息","安排失败！");
                }
            }
        });
    }

    private void initView(){
        JLabel eNoLabel = new JLabel("课程名称：");
        eNoLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        eNoLabel.setBounds(73, 211, 108, 30);
        add(eNoLabel);

        courseComboBox = new JComboBox<>();
        courseComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        courseComboBox.setBounds(182, 212, 180, 30);
        courseComboBox.setModel(new DefaultComboBoxModel<>(ClassUtil.getAllNotArrCourse()));
        add(courseComboBox);

        JLabel ePositionLabel = new JLabel("上课地点：");
        ePositionLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        ePositionLabel.setBounds(71, 290, 108, 30);
        add(ePositionLabel);

        ePositionField = new JTextField();
        ePositionField.setFont(new Font("楷体", Font.PLAIN, 18));
        ePositionField.setBounds(182, 291, 166, 30);
        add(ePositionField);
        ePositionField.setColumns(10);

        JLabel eClassLabel = new JLabel("上课班级：");
        eClassLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        eClassLabel.setBounds(296, 80, 108, 30);
        add(eClassLabel);

        sureButton = new JButton("确定");
        sureButton.setFont(new Font("楷体", Font.PLAIN, 18));
        sureButton.setBounds(350, 483, 113, 32);
        add(sureButton);

        deptComboBox = new JComboBox<String>();
        deptComboBox.setBounds(407, 80, 135, 30);
        deptComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        deptComboBox.setModel(new DefaultComboBoxModel<>(ClassUtil.getAllDept()));
        add(deptComboBox);

        majorComboBox = new JComboBox<String>();
        majorComboBox.setBounds(566, 80, 220, 30);
        majorComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        majorComboBox.setModel(new DefaultComboBoxModel<>(
                ClassUtil.getMajorByDept(deptComboBox.getSelectedItem().toString())));
        add(majorComboBox);

        classComboBox = new JComboBox<String>();
        classComboBox.setModel(new DefaultComboBoxModel<>(ClassUtil.getAllClass()));
        classComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        classComboBox.setBounds(812, 80, 125, 30);
        classComboBox.setModel(new DefaultComboBoxModel<>(
                ClassUtil.getClassByMajor(majorComboBox.getSelectedItem().toString())));
        add(classComboBox);

        lblNewLabel = new JLabel("上课周次：");
        lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel.setBounds(421, 288, 108, 30);
        add(lblNewLabel);

        cPeriodField = new JTextField();
        cPeriodField.setBounds(530, 290, 166, 30);
        add(cPeriodField);
        cPeriodField.setFont(new Font("楷体", Font.PLAIN, 20));
        cPeriodField.setColumns(10);

        lblNewLabel_1 = new JLabel("教授教师：");
        lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(421, 211, 108, 30);
        add(lblNewLabel_1);

        teacherComboBox = new JComboBox<>();
        teacherComboBox.setBounds(530, 211, 166, 30);
        teacherComboBox.setFont(new Font("楷体", Font.PLAIN, 20));
        teacherComboBox.setModel(new DefaultComboBoxModel<>(ClassUtil.getAllTeacher()));
        add(teacherComboBox);

        lblNewLabel_2 = new JLabel("上课时间1：");
        lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(59, 380, 122, 30);
        add(lblNewLabel_2);

        timeField_1 = new JTextField();
        timeField_1.setBounds(182, 380, 166, 30);
        timeField_1.setFont(new Font("楷体", Font.PLAIN, 20));
        add(timeField_1);
        timeField_1.setColumns(10);

        lblNewLabel_3 = new JLabel("上课时间2：");
        lblNewLabel_3.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_3.setBounds(407, 380, 111, 30);
        add(lblNewLabel_3);

        timeField_2 = new JTextField();
        timeField_2.setBounds(530, 380, 166, 30);
        timeField_2.setFont(new Font("楷体", Font.PLAIN, 20));
        add(timeField_2);
        timeField_2.setColumns(10);

        lblNewLabel_4 = new JLabel("\u5B66\u5E74\uFF1A");
        lblNewLabel_4.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_4.setBounds(111, 153, 70, 30);
        add(lblNewLabel_4);

        comboBox = new JComboBox<String>();
        comboBox.setFont(new Font("楷体", Font.PLAIN, 20));
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"2018-2019", "2019-2020"}));
        comboBox.setBounds(182, 154, 135, 30);
        add(comboBox);

        lblNewLabel_5 = new JLabel("\u5B66\u671F\uFF1A");
        lblNewLabel_5.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_5.setBounds(459, 152, 72, 30);
        add(lblNewLabel_5);

        comboBox_1 = new JComboBox<String>();
        comboBox_1.setModel(new DefaultComboBoxModel<>(new String[] {"\u7B2C\u4E00\u5B66\u671F", "\u7B2C\u4E8C\u5B66\u671F"}));
        comboBox_1.setFont(new Font("楷体", Font.PLAIN, 20));
        comboBox_1.setBounds(532, 153, 113, 30);
        add(comboBox_1);
    }
}
