package frame.teacher;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import bean.Teacher;
import frame.admin.BaseJPanel;
import gui.LoginGUI;
import handle.HandleSQL;
import util.DialogUtil;

import java.awt.Color;

public class ChangePassJPanel extends BaseJPanel implements KeyListener {

	private String Tno;//教师号
	private JFrame main;
	private JPasswordField pwdField_new;//输入新密码框
	private JPasswordField pwdField_confirm;//确认密码框
	private JPasswordField oldPwdField;//旧密码框
	private JButton btn_update;//修改按钮

	public ChangePassJPanel(String Tno,JFrame main) {
		super("修改密码");
		this.Tno = Tno;
		this.main = main;
		setLayout(null);
		initComponent();//界面设计
		addEventHandler();//事件处理
		setVisible(true);
	}

	private void initComponent() {//界面设计
		JLabel oldPassLabel = new JLabel("请输入旧密码:");
		oldPassLabel.setFont(new Font("楷体", Font.PLAIN, 18));
		oldPassLabel.setBounds(102, 150, 117, 25);
		add(oldPassLabel);

		oldPwdField = new JPasswordField();
		oldPwdField.setEchoChar('*');
		oldPwdField.setFont(new Font("楷体", Font.PLAIN, 18));
		oldPwdField.setBounds(233, 150, 148, 30);
		add(oldPwdField);
		oldPwdField.setColumns(10);

		JLabel label = new JLabel("请输入新密码:");
		label.setFont(new Font("楷体", Font.PLAIN, 18));
		label.setBounds(102, 204, 117, 25);
		add(label);

		pwdField_new = new JPasswordField();
		pwdField_new.setEchoChar('*');
		pwdField_new.setFont(new Font("楷体", Font.PLAIN, 18));
		pwdField_new.setBounds(233, 200, 148, 30);
		add(pwdField_new);
		pwdField_new.setColumns(10);

		JLabel label_1 = new JLabel("请确认新密码:");
		label_1.setFont(new Font("楷体", Font.PLAIN, 18));
		label_1.setBounds(102, 260, 118, 25);
		add(label_1);

		pwdField_confirm = new JPasswordField();
		pwdField_confirm.setEchoChar('*');
		pwdField_confirm.setFont(new Font("楷体", Font.PLAIN, 18));
		pwdField_confirm.setBounds(233, 254, 148, 30);
		add(pwdField_confirm);
		pwdField_confirm.setColumns(10);

		btn_update = new JButton("确认修改");
		btn_update.setBackground(new Color(0, 192, 255));
		btn_update.setForeground(Color.WHITE);
		btn_update.setFont(new Font("楷体", Font.PLAIN, 20));
		btn_update.setBounds(203, 320, 142, 30);
		add(btn_update);
	}

	public void addEventHandler() {//事件处理
		//响应修改按钮
		btn_update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handle_update(main);
			}
		});
		//响应键盘事件
		pwdField_new.addKeyListener(this);
		pwdField_confirm.addKeyListener(this);
		btn_update.addKeyListener(this);
	}

	private void handle_update(JFrame main) {
		String oldPwd = oldPwdField.getText(); //获取旧密码
		String newPwd = pwdField_new.getText(); //获取新密码
		String confirmPwd = String.valueOf(pwdField_confirm.getPassword()); //获取确认新密码
		Teacher teacher = HandleSQL.getTeaInfoBySQL(Tno);
		if(!oldPwd.equals(teacher.getTpassword())){
			DialogUtil.showWarning("警告消息", "原密码有误！请重新输入！");
			oldPwdField.setText("");
		}else if (oldPwd.equals("")){
			DialogUtil.showWarning("警告消息", "请先输入原密码！");
		}else if(newPwd.equals("")) {
			DialogUtil.showWarning("警告消息", "请输入新密码!");
		}else if(confirmPwd.equals("")) {
			DialogUtil.showWarning("警告消息", "请确认新密码!");
		}else if(!newPwd.equals(confirmPwd)) {
			DialogUtil.showWarning("警告消息", "两次输入密码不一致，请重新输入密码!");
		}else {
			boolean result = HandleSQL.Setpwd(newPwd, Tno);
			if(result){
				int n = JOptionPane.showConfirmDialog(this,
						"需重新登录以应用更改，确认退出？", "更新密码成功", JOptionPane.YES_NO_OPTION);
				if(n == JOptionPane.YES_OPTION) {
					main.dispose();
					LoginGUI logingui = new LoginGUI();//返回登录界面
					logingui.setVisible(true);
				}
			} else {
				DialogUtil.showMessage("提示消息", "修改密码失败,请重新操作!");
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyChar() == '\n')
			handle_update(main);
	}
}
