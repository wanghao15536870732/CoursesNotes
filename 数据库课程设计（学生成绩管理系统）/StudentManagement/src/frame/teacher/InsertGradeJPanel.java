package frame.teacher;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import frame.admin.BaseJPanel;
import handle.HandleSQL;
import util.ClassUtil;
import util.DialogUtil;

public class InsertGradeJPanel extends BaseJPanel {

	private JComboBox<String>  snoComboBox;//输入学号
	private JTextField sNameField;//输入姓名
	private JComboBox<String> courseComboBox;//选择课程
	private JTextField gradeField ;//输入成绩
	private JButton btn_enter ;//确认按钮
	private JComboBox<String> deptComboBox,majorComboBox,classComboBox;
	private String Tno;


	public InsertGradeJPanel(String Tno) {
		super("录入成绩");
		this.Tno = Tno;
		setLayout(null);
		initComponent();//界面设计
		addEventHandler();//事件处理
		setVisible(true);
		deptComboBox.addItemListener(e -> {
			switch (e.getStateChange()){
				case ItemEvent.SELECTED:
					majorComboBox.setModel(new DefaultComboBoxModel<>(
							ClassUtil.getMajorByDept(e.getItem().toString())
					));
					classComboBox.setModel(new DefaultComboBoxModel<>(
							ClassUtil.getClassByMajor(majorComboBox.getSelectedItem().toString())
					));
					break;
			}
		});

		majorComboBox.addItemListener(e -> {
			switch (e.getStateChange()){
				case ItemEvent.SELECTED:
					classComboBox.setModel(new DefaultComboBoxModel<>(
							ClassUtil.getClassByMajor(e.getItem().toString())
					));
					break;
			}
		});

		classComboBox.addItemListener(e -> {
			switch (e.getStateChange()){
				case ItemEvent.SELECTED:
					snoComboBox.setModel(new DefaultComboBoxModel<>(
							ClassUtil.getSnoByCLno(e.getItem().toString())
					));
					break;
			}
		});
		snoComboBox.addItemListener(e -> {
			switch (e.getStateChange()){
				case ItemEvent.SELECTED:
					sNameField.setText(HandleSQL.getStuName(e.getItem().toString()));
					break;
			}
		});
	}

	public void initComponent() {//界面设计
		JLabel snoLabel = new JLabel("学号:");
		snoLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		snoLabel.setBounds(99, 190, 50, 25);
		add(snoLabel);

		JLabel snamelabel = new JLabel("姓名:");
		snamelabel.setFont(new Font("楷体", Font.PLAIN, 20));
		snamelabel.setBounds(498, 190, 55, 25);
		add(snamelabel);

		sNameField = new JTextField();
		sNameField.setFont(new Font("楷体", Font.PLAIN, 20));
		sNameField.setBounds(552, 187, 138, 30);
		add(sNameField);
		sNameField.setColumns(10);

		JLabel cnamelabel = new JLabel("课程:");
		cnamelabel.setFont(new Font("楷体", Font.PLAIN, 20));
		cnamelabel.setBounds(99, 260, 50, 25);
		add(cnamelabel);

		JLabel gradelabel = new JLabel("成绩:");
		gradelabel.setFont(new Font("楷体", Font.PLAIN, 20));
		gradelabel.setBounds(500, 260, 55, 25);
		add(gradelabel);

		gradeField = new JTextField();
		gradeField.setFont(new Font("楷体", Font.PLAIN, 20));
		gradeField.setBounds(555, 260, 138, 30);
		add(gradeField);
		gradeField.setColumns(10);

		courseComboBox = new JComboBox<String>();
		courseComboBox.setFont(new Font("楷体", Font.PLAIN, 20));
		String[] allCourseName = HandleSQL.getAllTeachingCourse(Tno);
		DefaultComboBoxModel courseModel = new DefaultComboBoxModel (allCourseName);
		courseComboBox.setModel(courseModel);
		courseComboBox.setBounds(153, 260, 185, 30);
		add(courseComboBox);

		btn_enter = new JButton("确认");
		btn_enter.setForeground(Color.WHITE);
		btn_enter.setBackground(new Color(0,192,255));
		btn_enter.setFont(new Font("楷体", Font.PLAIN, 20));
		btn_enter.setBounds(354, 340, 113, 32);
		add(btn_enter);

		deptComboBox = new JComboBox<>();
		deptComboBox.setBounds(165, 116, 146, 30);
		deptComboBox.setFont(new Font("楷体", Font.PLAIN, 20));
		deptComboBox.setModel(new DefaultComboBoxModel<>(ClassUtil.getAllDept()));
		add(deptComboBox);

		majorComboBox = new JComboBox<>();
		majorComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
		majorComboBox.setBounds(420, 116, 170, 30);
		majorComboBox.setModel(new DefaultComboBoxModel<>(
				ClassUtil.getMajorByDept(deptComboBox.getSelectedItem().toString())));
		add(majorComboBox);

		classComboBox = new JComboBox<>();
		classComboBox.setModel(new DefaultComboBoxModel<>(ClassUtil.getAllClass()));
		classComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
		classComboBox.setBounds(712, 116, 133, 30);
		classComboBox.setModel(new DefaultComboBoxModel<>(
				ClassUtil.getClassByMajor(majorComboBox.getSelectedItem().toString())));
		add(classComboBox);

		snoComboBox = new JComboBox<>();
		snoComboBox.setFont(new Font("楷体", Font.PLAIN, 20));
		snoComboBox.setBounds(153, 188, 185, 30);
		snoComboBox.setModel(new DefaultComboBoxModel<>(ClassUtil.getSnoByCLno(classComboBox.getSelectedItem().toString())));
		add(snoComboBox);

		JLabel classLabel = new JLabel("班级:");
		classLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		classLabel.setBounds(648, 112, 63, 34);
		add(classLabel);

		JLabel lblNewLabel = new JLabel("学院：");
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		lblNewLabel.setBounds(99, 116, 63, 30);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("专业：");
		lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(349, 114, 63, 30);
		add(lblNewLabel_1);
	}

	public void addEventHandler() {//事件处理
		btn_enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String Cname = courseComboBox.getSelectedItem().toString();//获取选择的课程名
				String Sno = snoComboBox.getSelectedItem().toString();//获取学号
				String Cno = HandleSQL.getCno(Cname);//根据课程名得到对应的课程号
				if(sNameField.getText().equals("")){
					DialogUtil.showWarning("提示信息","未选择学生！");
				}else if (gradeField.getText().equals("")){
					DialogUtil.showWarning("提示信息","请先输入成绩！");
				}else {
					Double Grade = Double.parseDouble(gradeField.getText());//获取成绩分数
					boolean result=HandleSQL.InsertMessage(Sno, Cno, Grade);//录入成绩
					if (result){
						DialogUtil.showMessage("提示消息","录入成绩成功！");
					}else {
						DialogUtil.showMessage("提示信消息","录入成绩失败,请重新操作！");
					}
					validate();
				}
			}
		});
	}
}
