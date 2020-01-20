package frame.admin;

import java.awt.Font;
import javax.swing.*;
import handle.HandleStu;
import util.ClassUtil;
import util.DialogUtil;

public class StuAddJPanel extends BaseJPanel {

    public StuAddJPanel(JFrame main) {
        super("添加学生");
        setLayout(null);
        JLabel label = new JLabel("\u5B66\u53F7");
        label.setFont(new Font("楷体", Font.PLAIN, 20));
        label.setBounds(70, 125, 40, 30);
        add(label);

        JTextField SnotextField = new JTextField();
        SnotextField.setFont(new Font("楷体", Font.PLAIN, 18));
        SnotextField.setBounds(140, 125, 130, 30);
        add(SnotextField);
        SnotextField.setColumns(10);

        JLabel lblNewLabel = new JLabel("\u59D3\u540D");
        lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel.setBounds(70, 205, 40, 30);
        add(lblNewLabel);

        JTextField nameTextField = new JTextField();
        nameTextField.setFont(new Font("楷体", Font.PLAIN, 18));
        nameTextField.setBounds(140, 205, 130, 30);
        add(nameTextField);
        nameTextField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("\u6027\u522B");
        lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(70, 285, 40, 30);
        add(lblNewLabel_1);

        JComboBox<String> sexcomboBox = new JComboBox<>();
        sexcomboBox.setModel(new DefaultComboBoxModel<>(new String[]{"\u7537", "\u5973"}));
        sexcomboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        sexcomboBox.setBounds(140, 285, 130, 30);
        add(sexcomboBox);

        JLabel lblNewLabel_2 = new JLabel("\u5E74\u9F84");
        lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(70, 365, 40, 30);
        add(lblNewLabel_2);

        JTextField ageTextField = new JTextField();
        ageTextField.setFont(new Font("楷体", Font.PLAIN, 18));
        ageTextField.setBounds(140, 365, 130, 30);
        add(ageTextField);
        ageTextField.setColumns(10);

        JLabel lblNewLabel_7 = new JLabel("\u8054\u7CFB\u65B9\u5F0F");
        lblNewLabel_7.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_7.setBounds(370, 205, 80, 30);
        add(lblNewLabel_7);

        JTextField telTextField = new JTextField();
        telTextField.setFont(new Font("楷体", Font.PLAIN, 18));
        telTextField.setBounds(480, 205, 130, 30);
        add(telTextField);
        telTextField.setColumns(10);

        JLabel lblNewLabel_8 = new JLabel("\u90AE\u7BB1");
        lblNewLabel_8.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_8.setBounds(410, 285, 40, 30);
        add(lblNewLabel_8);

        JTextField emailTextField = new JTextField();
        emailTextField.setFont(new Font("楷体", Font.PLAIN, 18));
        emailTextField.setBounds(480, 285, 130, 30);
        add(emailTextField);
        emailTextField.setColumns(10);

        JButton SureButton = new JButton("\u786E\u5B9A");
        SureButton.setFont(new Font("楷体", Font.PLAIN, 20));
        SureButton.setBounds(320, 480, 113, 30);
        add(SureButton);

        JLabel label_1 = new JLabel("\u73ED\u7EA7");
        label_1.setFont(new Font("楷体", Font.PLAIN, 20));
        label_1.setBounds(410, 125, 40, 30);
        add(label_1);

        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        comboBox.setBounds(480, 125, 130, 30);
        comboBox.setModel(new DefaultComboBoxModel<>(ClassUtil.getAllClass()));
        add(comboBox);

        SureButton.addActionListener(e -> {
            if (SnotextField.getText().equals("") || nameTextField.getText().equals("")
                    || comboBox.getSelectedItem().toString().equals("") || ageTextField.getText().equals("")){
                DialogUtil.showMessage("提示信息","请完善学生信息");
            }else {
                int n = JOptionPane.showConfirmDialog(main,
                        "确认添加?", "确认对话框", JOptionPane.YES_NO_OPTION);
                if(n == JOptionPane.YES_OPTION) {
                    //获取输入的信息
                    String Sno = SnotextField.getText();
                    String Sname = nameTextField.getText();
                    String Ssex = (String) sexcomboBox.getSelectedItem();
                    String Sage = ageTextField.getText();
                    String Sclass = comboBox.getSelectedItem().toString();
                    String Stel = telTextField.getText();
                    String Semail = emailTextField.getText();
                    //////添加学生
                    boolean result = HandleStu.InsertStudent(Sno, Sname, Ssex, Sage, Sclass, "123456", Stel, Semail);
                    if (result) {
                        DialogUtil.showMessage("提示信息", "添加学生成功！");
                    } else {
                        DialogUtil.showMessage("提示信息", "添加学生失败，请检查是否有误！");
                    }
                }else {
                    SnotextField.setText("");
                    nameTextField.setText("");
                    ageTextField.setText("");
                    telTextField.setText("");
                    emailTextField.setText("");
                }
            }
        });
    }
}
