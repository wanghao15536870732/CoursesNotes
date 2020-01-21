package frame.teacher;

import java.awt.Color;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import frame.admin.BaseJPanel;
import handle.HandleSQL;
import util.DialogUtil;

public class SelectExamInfoJPanel extends BaseJPanel {
	private String Tno;//教师号
	private JComboBox<String> comboBox_course;//选择课程
	private JTable examTable;//考试信息表
	private JButton btn_select;//查询按钮

	public SelectExamInfoJPanel(String Tno) {
		super("查询考试");
		this.Tno = Tno;
		setLayout(null);
		initComponent();
		addEventHandler();
		setVisible(true);
	}

	public void initComponent() {
		examTable = new JTable();
		examTable.setBounds(70, 150, 900, 450);
		add(examTable);
		btn_select = new JButton("查询");
		btn_select.setForeground(Color.WHITE);
		btn_select.setBackground(new Color(0,192,255));
		btn_select.setFont(new Font("楷体", Font.PLAIN, 20));
		btn_select.setBounds(580, 100, 113, 32);
		add(btn_select);

		comboBox_course = new JComboBox<>();
		String[] courseList = HandleSQL.getAllTeachingCourse(Tno);
		DefaultComboBoxModel<String> courseModel = new DefaultComboBoxModel<>(courseList);
		comboBox_course.setModel(courseModel);
		comboBox_course.setFont(new Font("楷体", Font.PLAIN, 20));
		comboBox_course.setBounds(117, 100, 200, 33);
		add(comboBox_course);
	}

	void addEventHandler(){ //事件处理
		btn_select.addActionListener(e -> {
			if (comboBox_course.getSelectedIndex() == 0){
				DialogUtil.showWarning("提示信息","请选择课程！");
			}else {
				String selectedCourse = comboBox_course.getSelectedItem().toString();//获取选择的课程
				String selectCno = HandleSQL.getCno(selectedCourse);//根据课程名得到对应的课程号
				Object[][] data = HandleSQL.getExamMessage(selectCno);
				Object[] name = {"课程名","考试地点","考试开始时间","考试结束时间","考试班级"};
				remove(examTable);
				examTable = new JTable(data,name);
				examTable.setFont(new Font("楷体", Font.PLAIN, 18));
				examTable.setBounds(70, 150, 900, 450);
				examTable.setRowHeight(20);
				examTable.getTableHeader().setFont(new Font("楷体",Font.PLAIN, 18));
				examTable.getTableHeader().setBackground(Color.gray);
				examTable.getTableHeader().setForeground(Color.white);

				setColumnWidth(examTable);//设置表格每一列的宽度

				JScrollPane scrollPanel = new JScrollPane(examTable);
				scrollPanel.setViewportView(examTable);
				scrollPanel.setBounds(70, 150, 900, 450);
				add(scrollPanel);
				validate();
				remove(examTable);
			}
		});
	}

	//设置表格每一列的宽度
	public void setColumnWidth(JTable table) {
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
	}
}
