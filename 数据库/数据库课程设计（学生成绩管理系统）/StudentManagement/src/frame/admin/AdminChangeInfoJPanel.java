package frame.admin;
import handle.HandleAdm;
import util.DialogUtil;
import java.awt.Font;
import javax.swing.*;

public class AdminChangeInfoJPanel extends BaseJPanel {
    private JTextField emailTextField;

    public AdminChangeInfoJPanel(String Mno,JFrame main) {
        super("修改信息");
        setLayout(null);
        JLabel MnoLabel = new JLabel("\u7BA1\u7406\u5458\u53F7\uFF1A");
        MnoLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        MnoLabel.setBounds(124, 106, 107, 30);
        add(MnoLabel);

        JTextField MnotextField = new JTextField();
        MnotextField.setFont(new Font("楷体", Font.PLAIN, 18));
        MnotextField.setBounds(230, 106, 130, 30);
        MnotextField.setEnabled(false);
        add(MnotextField);
        MnotextField.setColumns(10);

        JLabel NameLabel = new JLabel("\u59D3\u540D\uFF1A");
        NameLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        NameLabel.setBounds(165, 176, 66, 30);
        add(NameLabel);

        JTextField NametextField = new JTextField();
        NametextField.setFont(new Font("楷体", Font.PLAIN, 18));
        NametextField.setBounds(230, 176, 130, 30);
        add(NametextField);
        NametextField.setColumns(10);

        JLabel label = new JLabel("\u6027\u522B\uFF1A");
        label.setFont(new Font("楷体", Font.PLAIN, 18));
        label.setBounds(165, 246, 66, 30);
        add(label);

        JLabel label_1 = new JLabel("\u5E74\u9F84\uFF1A");
        label_1.setFont(new Font("楷体", Font.PLAIN, 18));
        label_1.setBounds(165, 316, 66, 30);
        add(label_1);

        JLabel label_2 = new JLabel("\u8054\u7CFB\u65B9\u5F0F\uFF1A");
        label_2.setFont(new Font("楷体", Font.PLAIN, 18));
        label_2.setBounds(124, 386, 107, 30);
        add(label_2);

        JTextField AgetextField = new JTextField();
        AgetextField.setFont(new Font("楷体", Font.PLAIN, 18));
        AgetextField.setBounds(230, 316, 130, 30);
        add(AgetextField);
        AgetextField.setColumns(10);

        JTextField TeltextField = new JTextField();
        TeltextField.setFont(new Font("楷体", Font.PLAIN, 18));
        TeltextField.setBounds(230, 386, 130, 30);
        add(TeltextField);
        TeltextField.setColumns(10);

        JButton sureButton = new JButton("确认修改");
        sureButton.setFont(new Font("楷体", Font.PLAIN, 18));
        sureButton.setBounds(450, 450, 113, 30);
        add(sureButton);

        JComboBox<String> SexcomboBox = new JComboBox<>();
        SexcomboBox.setModel(new DefaultComboBoxModel<>(new String[]{"\u7537", "\u5973"}));
        SexcomboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        SexcomboBox.setBounds(230, 246, 130, 30);
        add(SexcomboBox);

        JLabel label_3 = new JLabel("\u90AE\u7BB1\uFF1A");
        label_3.setFont(new Font("楷体", Font.PLAIN, 18));
        label_3.setBounds(165, 456, 66, 30);
        add(label_3);

        emailTextField = new JTextField();
        emailTextField.setFont(new Font("楷体", Font.PLAIN, 18));
        emailTextField.setBounds(230, 456, 150, 30);
        add(emailTextField);
        emailTextField.setColumns(10);

        //显示管理员的原来信息
        String[] data = HandleAdm.showManager(Mno);
        MnotextField.setText(Mno);
        NametextField.setText(data[1]);
        SexcomboBox.setSelectedItem(data[2]);
        AgetextField.setText(data[3]);
        TeltextField.setText(data[4]);
        emailTextField.setText(data[5]);

        sureButton.addActionListener(e -> {
            int n = JOptionPane.showConfirmDialog(main,
                    "确认修改?", "确认对话框", JOptionPane.YES_NO_OPTION);
            if(n == JOptionPane.YES_OPTION) {
                //获取输入的信息
                String Mno1 = MnotextField.getText();
                String name = NametextField.getText();
                String sex = (String) SexcomboBox.getSelectedItem();
                String age = AgetextField.getText();
                String tel = TeltextField.getText();
                String email = emailTextField.getText();
                boolean result = HandleAdm.UpdateManager(Mno1, name, sex, age, email, tel);
                if (result) {
                    DialogUtil.showMessage("提示信息", "修改信息成功！");
                } else {
                    DialogUtil.showMessage("提示信息", "修改信息失败，请检查是否有误！");
                }
            }
        });
    }
}
