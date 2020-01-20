package frame.admin;

import javax.swing.*;

import handle.HandleSQL;
import handle.HandleTea;
import util.DialogUtil;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeaAddJPanel extends BaseJPanel {

    private JTextField tnoTextField;
    private JTextField TnametextField;
    private JTextField TagetextField;
    private JTextField TdutytextField;
    private JTextField TteltextField;
    private JTextField TemailtextField;

    public TeaAddJPanel(JFrame main) {
        super("添加教师");
        setLayout(null);
        JLabel label = new JLabel("\u6559\u5E08\u53F7");
        label.setFont(new Font("楷体", Font.PLAIN, 20));
        label.setBounds(55, 95, 60, 30);
        add(label);

        tnoTextField = new JTextField();
        tnoTextField.setFont(new Font("楷体", Font.PLAIN, 18));
        tnoTextField.setBounds(140, 95, 130, 30);
        add(tnoTextField);
        tnoTextField.setColumns(10);

        JLabel lblNewLabel = new JLabel("\u59D3\u540D");
        lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel.setBounds(70, 175, 40, 30);
        add(lblNewLabel);

        TnametextField = new JTextField();
        TnametextField.setFont(new Font("楷体", Font.PLAIN, 18));
        TnametextField.setBounds(140, 175, 130, 30);
        add(TnametextField);
        TnametextField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("\u6027\u522B");
        lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(70, 255, 40, 30);
        add(lblNewLabel_1);

        JComboBox<String> tSexComboBox = new JComboBox<String>();
        tSexComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"\u7537", "\u5973"}));
        tSexComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        tSexComboBox.setBounds(140, 255, 130, 30);
        add(tSexComboBox);

        JLabel lblNewLabel_2 = new JLabel("\u5E74\u9F84");
        lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(70, 335, 40, 30);
        add(lblNewLabel_2);

        TagetextField = new JTextField();
        TagetextField.setFont(new Font("楷体", Font.PLAIN, 18));
        TagetextField.setBounds(140, 335, 130, 30);
        add(TagetextField);
        TagetextField.setColumns(10);

        JLabel lblNewLabel_3 = new JLabel("\u5B66\u9662");
        lblNewLabel_3.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_3.setBounds(70, 415, 40, 30);
        add(lblNewLabel_3);

        JComboBox<String> tDeptComboBox = new JComboBox<>();
        tDeptComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"\u5927\u6570\u636E\u5B66\u9662", "\u7ECF\u6D4E\u7BA1\u7406\u5B66\u9662"}));
        tDeptComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        tDeptComboBox.setBounds(140, 415, 130, 30);
        add(tDeptComboBox);

        JLabel lblNewLabel_4 = new JLabel("\u5B66\u5386");
        lblNewLabel_4.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_4.setBounds(410, 95, 40, 30);
        add(lblNewLabel_4);

        JComboBox<String> tDegreeComboBox = new JComboBox<String>();
        tDegreeComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"\u672C\u79D1", "\u7855\u58EB", "\u535A\u58EB", "\u535A\u58EB\u540E"}));
        tDegreeComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        tDegreeComboBox.setBounds(480, 95, 130, 30);
        add(tDegreeComboBox);

        JLabel lblNewLabel_5 = new JLabel("\u804C\u79F0");
        lblNewLabel_5.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_5.setBounds(410, 175, 40, 30);
        add(lblNewLabel_5);

        JComboBox<String> tTitleComboBox = new JComboBox<>();
        tTitleComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"\u52A9\u6559", "\u8BB2\u5E08", "\u526F\u6559\u6388", "\u6559\u6388"}));
        tTitleComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        tTitleComboBox.setBounds(480, 175, 130, 30);
        add(tTitleComboBox);

        JLabel lblNewLabel_6 = new JLabel("\u804C\u52A1");
        lblNewLabel_6.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_6.setBounds(410, 255, 40, 30);
        add(lblNewLabel_6);

        TdutytextField = new JTextField();
        TdutytextField.setFont(new Font("楷体", Font.PLAIN, 18));
        TdutytextField.setBounds(480, 255, 130, 30);
        add(TdutytextField);
        TdutytextField.setColumns(10);

        JLabel lblNewLabel_7 = new JLabel("\u8054\u7CFB\u65B9\u5F0F");
        lblNewLabel_7.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_7.setBounds(370, 335, 80, 30);
        add(lblNewLabel_7);

        TteltextField = new JTextField();
        TteltextField.setFont(new Font("楷体", Font.PLAIN, 18));
        TteltextField.setBounds(480, 335, 130, 30);
        add(TteltextField);
        TteltextField.setColumns(10);

        JLabel lblNewLabel_8 = new JLabel("\u90AE\u7BB1");
        lblNewLabel_8.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_8.setBounds(410, 415, 40, 30);
        add(lblNewLabel_8);

        TemailtextField = new JTextField();
        TemailtextField.setFont(new Font("楷体", Font.PLAIN, 18));
        TemailtextField.setBounds(480, 415, 130, 30);
        add(TemailtextField);
        TemailtextField.setColumns(10);

        JButton sureButton = new JButton("\u786E\u5B9A");
        sureButton.setFont(new Font("楷体", Font.PLAIN, 20));
        sureButton.setBounds(150, 496, 113, 30);
        add(sureButton);

        sureButton.addActionListener(e -> {
            if (tnoTextField.getText().equals("") || TnametextField.getText().equals("")
                    || TagetextField.getText().equals("")){
                int n = JOptionPane.showConfirmDialog(main,
                        "确认添加?", "确认对话框", JOptionPane.YES_NO_OPTION);
                if(n == JOptionPane.YES_OPTION) {
                    //获取输入的信息
                    String Tno = tnoTextField.getText();
                    String name = TnametextField.getText();
                    String sex = (String) tSexComboBox.getSelectedItem();
                    String age = TagetextField.getText();
                    String dept = (String) tDeptComboBox.getSelectedItem();
                    String degree = (String) tDegreeComboBox.getSelectedItem();
                    String title = (String) tTitleComboBox.getSelectedItem();
                    String duty = TdutytextField.getText();
                    String tel = TteltextField.getText();
                    String email = TemailtextField.getText();
                    //////添加教师
                    boolean result = HandleTea.InsertTeacher(Tno, name, sex, age, dept, degree, title, duty, tel, email, "123456");
                    if (result) {
                        DialogUtil.showMessage("提示信息", "添加教师成功！");
                    } else {
                        DialogUtil.showMessage("提示信息", "添加教师失败，请检查是否有误！");
                    }
                }else {
                    tnoTextField.setText("");
                    TnametextField.setText("");
                    TagetextField.setText("");
                    TdutytextField.setText("");
                    TteltextField.setText("");
                    TemailtextField.setText("");
                }
            }else {
                DialogUtil.showMessage("提示信息","请完善教师信息");
            }

        });
    }
}
