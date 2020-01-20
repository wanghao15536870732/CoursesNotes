package frame.teacher;

import java.awt.Color;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import frame.admin.BaseJPanel;
import handle.HandleSQL;
import util.DialogUtil;

public class UpdateGradeJPanel extends BaseJPanel {

	private JTextField snoField;//输入学号
	private JTextField snameField;//输入姓名
	private JComboBox<String> coursecomboBox;//选择课程
	private JTextField gradeField ;//输入成绩
	private JButton btn_update ;//修改按钮
	/**
	 * Create the panel.
	 */
	public UpdateGradeJPanel() {
		super("修改成绩");
		setLayout(null);
		initComponent();//界面设计
		addEventHandler();//事件处理
		setVisible(true);
	}

	public void initComponent() {//界面设计
		JLabel snolabel = new JLabel("学号:");
		snolabel.setFont(new Font("楷体", Font.PLAIN, 20));
		snolabel.setBounds(97, 122, 50, 18);
		add(snolabel);

		snoField = new JTextField();
		snoField.setFont(new Font("楷体", Font.PLAIN, 20));
		snoField.setBounds(151, 118, 185, 30);
		add(snoField);
		snoField.setColumns(10);

		JLabel snamelabel = new JLabel("姓名:");
		snamelabel.setFont(new Font("楷体", Font.PLAIN, 20));
		snamelabel.setBounds(498, 122, 55, 18);
		add(snamelabel);

		snameField = new JTextField();
		snameField.setFont(new Font("楷体", Font.PLAIN, 20));
		snameField.setBounds(552, 117, 138, 30);
		add(snameField);
		snameField.setColumns(10);

		JLabel cnamelabel = new JLabel("课程:");
		cnamelabel.setFont(new Font("楷体", Font.PLAIN, 20));
		cnamelabel.setBounds(97, 240, 50, 18);
		add(cnamelabel);

		JLabel gradelabel = new JLabel("成绩:");
		gradelabel.setFont(new Font("楷体", Font.PLAIN, 20));
		gradelabel.setBounds(498, 240, 55, 18);
		add(gradelabel);

		gradeField = new JTextField();
		gradeField.setFont(new Font("楷体", Font.PLAIN, 20));
		gradeField.setBounds(552, 238, 138, 30);
		add(gradeField);
		gradeField.setColumns(10);

		coursecomboBox = new JComboBox<String>();
		coursecomboBox.setFont(new Font("楷体", Font.PLAIN, 20));
		String[] allCourseName = HandleSQL.getAllCourseName();
		DefaultComboBoxModel<String> courseModel = new DefaultComboBoxModel<>(allCourseName);
		coursecomboBox.setModel(courseModel);
		coursecomboBox.setBounds(151, 237, 185, 30);
		add(coursecomboBox);

		btn_update = new JButton("确认修改");
		btn_update.setForeground(Color.WHITE);
		btn_update.setBackground(new Color(0,192,255));
		btn_update.setFont(new Font("楷体", Font.PLAIN, 20));
		btn_update.setBounds(354, 333, 140, 33);
		add(btn_update);
	}

	public void addEventHandler() {//事件处理
		btn_update.addActionListener(e -> {
			String Cname = coursecomboBox.getSelectedItem().toString();
			String Sno = snoField.getText();
			String Sname = snameField.getText();
			String Cno = HandleSQL.getCno(Cname);
			if (snoField.getText().equals("")) {
				DialogUtil.showMessage("提示消息", "请输入学号！");
			} else if (snameField.getText().equals("")) {
				DialogUtil.showMessage("提示消息", "请输入姓名！");
			} else if (coursecomboBox.getSelectedItem().equals("请选择课程")) {
				DialogUtil.showMessage("提示消息", "请选择课程！");
			} else if (gradeField.getText().equals("")) {
				DialogUtil.showMessage("提示消息", "请输入成绩！");
			} else {
				Double Grade = Double.parseDouble(gradeField.getText());
				if(!Sno.equals("")) {//学号不为空,即用户输入了学号
					updategrade(Grade,Sno,Cno);//修改成绩
				}else {//学号为空,即用户没有输入学号，输入的是姓名
					Sno = HandleSQL.getSno(Sname);//根据姓名得到对应的学号
					updategrade(Grade,Sno,Cno);//修改成绩
				}
				validate();
			}
		});
	}

	public void updategrade(Double Grade,String Sno,String Cno) {//修改成绩
		boolean result=HandleSQL.UpdateGrade(Grade, Sno, Cno);
		if (result){
			DialogUtil.showMessage("提示消息","修改成绩成功！");
		}else {
			DialogUtil.showMessage("提示信消息","修改成绩失败,请重新操作！");
		}
	}

}
