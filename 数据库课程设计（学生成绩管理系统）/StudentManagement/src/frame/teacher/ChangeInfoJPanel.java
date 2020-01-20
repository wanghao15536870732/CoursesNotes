package frame.teacher;

import java.awt.Font;
import javax.swing.*;
import bean.Teacher;
import frame.admin.BaseJPanel;
import handle.HandleSQL;
import util.DialogUtil;

import java.awt.Color;

public class ChangeInfoJPanel extends BaseJPanel {
	private String Tno;
	private JTextField tNameField;//姓名
	private JComboBox<String> tSexComboBox;//性别
	private JTextField tAgeField; //年龄
	private JTextField textField_Tno;//教师编号
	private JTextField tDeptField; //系别
	private JTextField tDegreeField;  //学历
	private JTextField tTitleField; //职称
	private JTextField tDutyField; //职务
	private JTextField tTelField; //电话
	private JTextField tEmailField;//邮箱
	private JButton btn_update;//确认修改按钮
	private Teacher teacher;

	public ChangeInfoJPanel(String Tno) {
		super("修改信息");
		this.Tno = Tno;
		setLayout(null);
		initComponent();//界面设计
		addEventHandler();//事件处理
		setVisible(true);
	}

	private void initComponent() {//界面设计
		JLabel tNoLabel = new JLabel("编号:");
		tNoLabel.setFont(new Font("楷体", Font.PLAIN, 18));
		tNoLabel.setBounds(74, 210, 54, 18);
		add(tNoLabel);

		JLabel tNameLabel = new JLabel("姓名:");
		tNameLabel.setFont(new Font("楷体", Font.PLAIN, 18));
		tNameLabel.setBounds(70, 120, 54, 18);
		add(tNameLabel);

		JLabel tSexLabel = new JLabel("性别:");
		tSexLabel.setFont(new Font("楷体", Font.PLAIN, 18));
		tSexLabel.setBounds(293, 120, 54, 18);
		add(tSexLabel);

		JLabel tAgeLabel = new JLabel("年龄:");
		tAgeLabel.setFont(new Font("楷体", Font.PLAIN, 18));
		tAgeLabel.setBounds(528, 120, 54, 18);
		add(tAgeLabel);

		JLabel tDeptLabel = new JLabel("院系:");
		tDeptLabel.setFont(new Font("楷体", Font.PLAIN, 18));
		tDeptLabel.setBounds(293, 210, 54, 18);
		add(tDeptLabel);

		JLabel tDegreeLabel = new JLabel("学历:");
		tDegreeLabel.setFont(new Font("楷体", Font.PLAIN, 18));
		tDegreeLabel.setBounds(528, 210, 54, 18);
		add(tDegreeLabel);

		JLabel tTitleLabel = new JLabel("职称:");
		tTitleLabel.setFont(new Font("楷体", Font.PLAIN, 18));
		tTitleLabel.setBounds(74, 320, 54, 18);
		add(tTitleLabel);

		JLabel tDutyLabel = new JLabel("职务:");
		tDutyLabel.setFont(new Font("楷体", Font.PLAIN, 18));
		tDutyLabel.setBounds(293, 320, 54, 18);
		add(tDutyLabel);

		JLabel tTelLabel = new JLabel("联系方式:");
		tTelLabel.setFont(new Font("楷体", Font.PLAIN, 18));
		tTelLabel.setBounds(528, 320, 86, 18);
		add(tTelLabel);

		JLabel tEmailLabel = new JLabel("邮箱:");
		tEmailLabel.setFont(new Font("楷体", Font.PLAIN, 18));
		tEmailLabel.setBounds(74, 426, 54, 18);
		add(tEmailLabel);

		tNameField = new JTextField();
		tNameField.setFont(new Font("楷体", Font.PLAIN, 18));
		tNameField.setBounds(119, 119, 86, 26);
		add(tNameField);
		tNameField.setColumns(10);

		tSexComboBox = new JComboBox<String>();
		tSexComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
		tSexComboBox.setBounds(340, 119, 86, 26);
		tSexComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"男","女"}));
		add(tSexComboBox);

		tAgeField = new JTextField();
		tAgeField.setFont(new Font("楷体", Font.PLAIN, 18));
		tAgeField.setBounds(574, 119, 86, 26);
		add(tAgeField);
		tAgeField.setColumns(10);

		textField_Tno = new JTextField();
		textField_Tno.setFont(new Font("楷体", Font.PLAIN, 18));
		textField_Tno.setBounds(119, 209, 86, 26);
		add(textField_Tno);
		textField_Tno.setEnabled(false);
		textField_Tno.setColumns(10);

		tDeptField = new JTextField();
		tDeptField.setFont(new Font("楷体", Font.PLAIN, 18));
		tDeptField.setBounds(340, 209, 134, 26);
		add(tDeptField);
		tDeptField.setEnabled(false);
		tDeptField.setColumns(10);

		tDegreeField = new JTextField();
		tDegreeField.setFont(new Font("楷体", Font.PLAIN, 18));
		tDegreeField.setBounds(574, 209, 86, 26);
		add(tDegreeField);
		tDegreeField.setColumns(10);

		tTitleField = new JTextField();
		tTitleField.setFont(new Font("楷体", Font.PLAIN, 18));
		tTitleField.setBounds(119, 319, 86, 26);
		add(tTitleField);
		tTitleField.setEnabled(false);
		tTitleField.setColumns(10);

		tDutyField = new JTextField();
		tDutyField.setFont(new Font("楷体", Font.PLAIN, 18));
		tDutyField.setBounds(340, 319, 134, 26);
		add(tDutyField);
		tDutyField.setEnabled(false);
		tDutyField.setColumns(10);

		tTelField = new JTextField();
		tTelField.setFont(new Font("楷体", Font.PLAIN, 18));
		tTelField.setBounds(607, 319, 118, 26);
		add(tTelField);
		tTelField.setColumns(10);

		tEmailField = new JTextField();
		tEmailField.setFont(new Font("楷体", Font.PLAIN, 18));
		tEmailField.setBounds(119, 425, 187, 26);
		add(tEmailField);
		tEmailField.setColumns(10);

		setTeaInfo(Tno);//初始化教师的基本信息
		btn_update = new JButton("确认修改");
		btn_update.setBackground(new Color(0, 192, 255));
		btn_update.setForeground(Color.WHITE);
		btn_update.setFont(new Font("楷体", Font.PLAIN, 20));
		btn_update.setBounds(340, 504, 140, 32);
		add(btn_update);
	}

	public void setTeaInfo(String Tno) {//初始化教师的基本信息
		teacher = HandleSQL.getTeaInfoBySQL(Tno);
		textField_Tno.setText(teacher.getTno());
		if (teacher.getTsex().equals("男")){
			tSexComboBox.setSelectedIndex(0);
		}else {
			tSexComboBox.setSelectedIndex(1);
		}
		tAgeField.setText(teacher.getTage());
		tNameField.setText(teacher.getTname());
		tDeptField.setText(teacher.getTdept());
		tDegreeField.setText(teacher.getTdegree());
		tTitleField.setText(teacher.getTtitle());
		tDutyField.setText(teacher.getTduty());
		tTelField.setText(teacher.getTtel());
		tEmailField.setText(teacher.getTemail());
	}

	public void addEventHandler() {//事件处理
		btn_update.addActionListener(e -> {
			String newName = tNameField.getText();
			String newSex = tSexComboBox.getSelectedItem().toString();
			String newAge = tAgeField.getText();
			String newDept = tDeptField.getText();
			String newDegree = tDegreeField.getText();
			String newTitle = tTitleField.getText();
			String newDuty = tDutyField.getText();
			String newTel = tTelField.getText();
			String newEmail = tEmailField.getText();
			if (newName.equals(teacher.getTname()) && newSex.equals(teacher.getTsex())
					&& newAge.equals(teacher.getTage()) && newDept.equals(teacher.getTdept())
					&& newTel.equals(teacher.getTtel()) && newEmail.equals(teacher.getTemail())){
				DialogUtil.showWarning("提示信息","还未做任何修改！");
			}else if (newName.equals("") || newSex.equals("")
					|| newAge.equals("") || newDept.equals("")
					|| newTel.equals("") || newEmail.equals("")){
				DialogUtil.showWarning("提示信息","修改信息不能为空！");
			}else {
				//修改教师基本信息
				boolean result = HandleSQL.SetTeaInfo(newName, newSex, newAge, newDept,
						newDegree,newTitle, newDuty, newTel, newEmail, Tno);
				if(result)
					DialogUtil.showMessage("提示消息", "修改信息成功!");
				else{
					DialogUtil.showMessage("提示消息", "修改信息失败,请重新操作!");
				}
			}

		});
	}
}
