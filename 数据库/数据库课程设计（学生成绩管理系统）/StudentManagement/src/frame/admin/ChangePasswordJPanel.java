package frame.admin;

import java.awt.Color;
import java.sql.Connection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import frame.BaseJFrame;
import gui.LoginGUI;
import handle.HandleAdm;
import util.DBHelper;
import util.DialogUtil;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangePasswordJPanel extends BaseJPanel {

    private JPasswordField newPasswordField;
    private JPasswordField surePasswordField;
    private JPasswordField oldPasswordField;

    public ChangePasswordJPanel(String Mno,JFrame main) {
        super("修改密码");
        setLayout(null);
        JLabel label = new JLabel("旧密码：");
        label.setFont(new Font("楷体", Font.PLAIN, 20));
        label.setBounds(120, 140, 90, 30);
        add(label);

        JLabel lblNewLabel = new JLabel("新密码：");
        lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel.setBounds(120, 220, 90, 30);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("新密码：");
        lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(130, 300, 80, 30);
        add(lblNewLabel_1);

        oldPasswordField = new JPasswordField();
        oldPasswordField.setBounds(230, 140, 120, 30);
        add(oldPasswordField);

        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(230, 220, 120, 30);
        add(newPasswordField);

        surePasswordField = new JPasswordField();
        surePasswordField.setBounds(230, 300, 120, 30);
        add(surePasswordField);

        JButton SureButton = new JButton("确认修改");
        SureButton.setFont(new Font("楷体", Font.PLAIN, 20));
        SureButton.setBounds(190, 400, 130, 33);
        add(SureButton);

        SureButton.addActionListener(e -> {
            char[] password1 = oldPasswordField.getPassword();
            String oldPassword = new String(password1);
            char[] password2 = newPasswordField.getPassword();
            String newPassword = new String(password2);
            char[] password3 = surePasswordField.getPassword();
            String surePassword = new String(password3);
            String password = HandleAdm.OldPasswordManager(Mno);
            //判断旧密码是否正确，管理员号在登录时获得
            if (!oldPassword.equalsIgnoreCase(password)) {
                DialogUtil.showMessage("提示信息", "旧密码错误！");
            } else {
                if (!newPassword.equalsIgnoreCase(surePassword)) {
                    DialogUtil.showMessage("提示信息", "两次输入的新密码不同！");
                } else {
                    boolean result = HandleAdm.SetPasswordManager("M01", newPassword);
                    if (!result) {
                        DialogUtil.showMessage("提示信息", "修改失败，请检查信息！");
                    } else {
                        int n = JOptionPane.showConfirmDialog(main,
                                "需重新登录以应用更改，确认退出？", "更新密码成功", JOptionPane.YES_NO_OPTION);
                        if(n == JOptionPane.YES_OPTION) {
                            main.dispose();
                            LoginGUI logingui = new LoginGUI();//返回登录界面
                            logingui.setVisible(true);
                        }
                    }
                }
            }
        });
    }
}
