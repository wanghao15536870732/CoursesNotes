package frame.student;

import bean.Student;
import frame.admin.BaseJPanel;
import gui.LoginGUI;
import handle.HandleStu;
import util.DialogUtil;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 修改密码
 */
public class ChangePasswordJPanel extends BaseJPanel {
    private JPasswordField oldPass;
    private JPasswordField newPass;
    private JPasswordField newSurePass;
    private JButton btnNewButton;

    public ChangePasswordJPanel(String Sno,JFrame main) {
        super("修改密码");
        setLayout(null);
        initView();
        Student student = HandleStu.getStuInfo(Sno);
        btnNewButton.addActionListener(e -> {
            if (oldPass.getText().equals("")){
                DialogUtil.showWarning("提示信息","请输入原密码!");
            }else if(!oldPass.getText().equals(student.getSpassword())){
                DialogUtil.showWarning("提示信息","原密码错误，请重新输入!");
            }else if(newPass.getText().equals("")) {
                DialogUtil.showWarning("提示信息","请输入新密码！");
            }else if(newSurePass.getText().equals("")) {
                DialogUtil.showWarning("提示信息","请输入确认密码！");
            }else if(oldPass.getText().equals(newPass.getText())){
                DialogUtil.showWarning("提示信息","新密码不能与原密码相同！");
            }else if(!newPass.getText().equals(newSurePass.getText())){
                DialogUtil.showWarning("提示信息","两次输入密码不一致！");
            }else {
                boolean result = HandleStu.updateStuPass(Sno,newPass.getText());
                if (result){
                    int n = JOptionPane.showConfirmDialog(this,
                            "需重新登录以应用更改，确认退出？", "更新密码成功", JOptionPane.YES_NO_OPTION);
                    if(n == JOptionPane.YES_OPTION) {
                        main.dispose();
                        LoginGUI logingui = new LoginGUI();//返回登录界面
                        logingui.setVisible(true);
                    }
                }else {
                    DialogUtil.showWarning("提示信息","更新密码失败！");
                }
            }
        });
    }

    public void initView(){
        JLabel lblNewLabel_1 = new JLabel("原密码：");
        lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(90, 161, 72, 18);
        add(lblNewLabel_1);

        oldPass = new JPasswordField();
        oldPass.setFont(new Font("宋体", Font.PLAIN, 18));
        oldPass.setBounds(163, 155, 135, 32);
        add(oldPass);
        oldPass.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("新密码：");
        lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_2.setBounds(90, 235, 72, 18);
        add(lblNewLabel_2);

        newPass = new JPasswordField();
        newPass.setFont(new Font("宋体", Font.PLAIN, 18));
        newPass.setBounds(163, 230, 135, 32);
        newPass.setColumns(10);
        add(newPass);

        JLabel lblNewLabel_3 = new JLabel("确认密码：");
        lblNewLabel_3.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_3.setBounds(72, 311, 90, 18);
        add(lblNewLabel_3);

        newSurePass = new JPasswordField();
        newSurePass.setFont(new Font("宋体", Font.PLAIN, 18));
        newSurePass.setBounds(163, 306, 135, 32);
        add(newSurePass);

        btnNewButton = new JButton("确认修改");
        btnNewButton.setFont(new Font("楷体", Font.PLAIN, 18));
        btnNewButton.setBounds(150, 430, 113, 30);
        add(btnNewButton);
    }
}
