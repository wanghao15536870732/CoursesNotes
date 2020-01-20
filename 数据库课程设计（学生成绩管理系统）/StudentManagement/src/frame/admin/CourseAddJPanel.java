package frame.admin;

import util.CourseUtil;
import util.DialogUtil;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 添加课程
 */
public class CourseAddJPanel extends BaseJPanel {

    // 输入框
    private JTextField cNoField,cNameField,cPeriodField,cCreditField;
    //按钮
    private JButton sureButton;
    private DefaultComboBoxModel cAttributeModel = new DefaultComboBoxModel<>(new String[] {"必修", "专业选修","公共选修"});

    public CourseAddJPanel() {
        super("添加课程");
        setLayout(null);
        initView();
        sureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取输入的信息
                String cno = cNoField.getText();
                String cname = cNameField.getText();

                String cattribute = cAttributeModel.getSelectedItem().toString();
                if (cno.equals("") || cname.equals("")|| cPeriodField.getText().equals("") ||
                        cCreditField.getText().equals("") || cattribute.equals("")){
                    DialogUtil.showWarning("提示信息","请输入完整的信息！");
                }else {
                    int cperiod = Integer.parseInt(cPeriodField.getText());
                    float ccredit = Float.parseFloat(cCreditField.getText());
                    boolean result = CourseUtil.InsertCourse(cno,cname,cperiod,ccredit,cattribute);//添加课程
                    if (result){
                        DialogUtil.showMessage("提示信息","添加课程成功！");
                        cNoField.setText("");
                        cNameField.setText("");
                        cPeriodField.setText("");
                        cCreditField.setText("");
                        cAttributeModel.setSelectedItem("");
                    }else {
                        DialogUtil.showMessage("提示信息","添加课程失败，请检查是否有误！");
                    }
                }

            }
        });
    }

    public void initView(){
        //课程号、课程名、课程学时、课程学分、老师编号、上课时间、上课地点
        JLabel cNoLabel = new JLabel("课程编号：");
        cNoLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        cNoLabel.setBounds(70, 100, 90, 18);
        add(cNoLabel);

        cNoField = new JTextField();
        cNoField.setFont(new Font("楷体", Font.PLAIN, 18));
        cNoField.setBounds(160, 96, 122, 28);
        add(cNoField);
        cNoField.setColumns(10);

        JLabel cNameLabel = new JLabel("课程名称：");
        cNameLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        cNameLabel.setBounds(323, 100, 120, 18);
        add(cNameLabel);

        cNameField = new JTextField();
        cNameField.setFont(new Font("楷体", Font.PLAIN, 18));
        cNameField.setBounds(417, 96, 122, 28);
        add(cNameField);
        cNameField.setColumns(10);

        JLabel cPeriodLabel = new JLabel("课程学时：");
        cPeriodLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        cPeriodLabel.setBounds(70, 155, 90, 18);
        add(cPeriodLabel);

        cPeriodField = new JTextField();
        cPeriodField.setFont(new Font("楷体", Font.PLAIN, 18));
        cPeriodField.setBounds(160, 151, 122, 28);
        add(cPeriodField);
        cPeriodField.setColumns(10);

        JLabel cCreditLabel = new JLabel("课程学分：");
        cCreditLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        cCreditLabel.setBounds(323, 155, 90, 18);
        add(cCreditLabel);

        cCreditField = new JTextField();
        cCreditField.setFont(new Font("楷体", Font.PLAIN, 18));
        cCreditField.setBounds(417, 151, 122, 28);
        add(cCreditField);
        cCreditField.setColumns(10);

        //课程属性、课程学年、课程学期
        JLabel cAttributeLabel = new JLabel("课程属性:");
        cAttributeLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        cAttributeLabel.setBounds(70, 210, 90, 18);
        add(cAttributeLabel);

        JComboBox<String> comboBox = new JComboBox();
        comboBox.setModel(cAttributeModel);
        comboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        comboBox.setBounds(160, 205, 120, 28);
        add(comboBox);

        sureButton = new JButton("添加");
        sureButton.setFont(new Font("楷体", Font.PLAIN, 18));
        sureButton.setBounds(250, 300, 113, 27);
        add(sureButton);
    }
}
