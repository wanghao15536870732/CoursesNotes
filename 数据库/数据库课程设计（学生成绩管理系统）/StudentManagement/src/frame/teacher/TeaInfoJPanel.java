package frame.teacher;

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import bean.Teacher;
import frame.admin.BaseJPanel;
import handle.HandleSQL;

public class TeaInfoJPanel extends BaseJPanel {
	private String Tno;
	public TeaInfoJPanel(String Tno) {
		super("基本信息");
		this.Tno = Tno;
		setLayout(null);
		initComponent();//界面设计
	}

	public void initComponent() {//界面设计

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

		JLabel tNoLabel_1 = new JLabel(" ");
		tNoLabel_1.setFont(new Font("楷体", Font.PLAIN, 18));
		tNoLabel_1.setBounds(120, 207, 110, 26);
		add(tNoLabel_1);

		JLabel tnamelabel_1 = new JLabel(" ");
		tnamelabel_1.setFont(new Font("楷体", Font.PLAIN, 18));
		tnamelabel_1.setBounds(120, 118, 86, 26);
		add(tnamelabel_1);

		JLabel tsexlabel_1 = new JLabel(" ");
		tsexlabel_1.setFont(new Font("楷体", Font.PLAIN, 18));
		tsexlabel_1.setBounds(344, 118, 86, 26);
		add(tsexlabel_1);

		JLabel tagelabel_1 = new JLabel(" ");
		tagelabel_1.setFont(new Font("楷体", Font.PLAIN, 18));
		tagelabel_1.setBounds(582, 118, 86, 26);
		add(tagelabel_1);

		JLabel tdeptlabel_1 = new JLabel(" ");
		tdeptlabel_1.setFont(new Font("楷体", Font.PLAIN, 18));
		tdeptlabel_1.setBounds(344, 208, 110, 26);
		add(tdeptlabel_1);

		JLabel tdegreelabel_1 = new JLabel(" ");
		tdegreelabel_1.setFont(new Font("楷体", Font.PLAIN, 18));
		tdegreelabel_1.setBounds(582, 208, 86, 26);
		add(tdegreelabel_1);

		JLabel ttitlelabel_1 = new JLabel(" ");
		ttitlelabel_1.setFont(new Font("楷体", Font.PLAIN, 18));
		ttitlelabel_1.setBounds(125, 318, 86, 26);
		add(ttitlelabel_1);

		JLabel tdutylabel_1 = new JLabel(" ");
		tdutylabel_1.setFont(new Font("楷体", Font.PLAIN, 18));
		tdutylabel_1.setBounds(345, 318, 110, 26);
		add(tdutylabel_1);

		JLabel ttellabel_1 = new JLabel(" ");
		ttellabel_1.setFont(new Font("楷体", Font.PLAIN, 18));
		ttellabel_1.setBounds(610, 318, 110, 26);
		add(ttellabel_1);

		JLabel temaillabel_1 = new JLabel(" ");
		temaillabel_1.setFont(new Font("楷体", Font.PLAIN, 18));
		temaillabel_1.setBounds(125, 424, 180, 26);
		add(temaillabel_1);

		Teacher teacher = HandleSQL.getTeaInfoBySQL(Tno);
		tNoLabel_1.setText(teacher.getTno());
		tsexlabel_1.setText(teacher.getTsex());
		tagelabel_1.setText(teacher.getTage());
		tnamelabel_1.setText(teacher.getTname());
		tdeptlabel_1.setText(teacher.getTdept());
		tdegreelabel_1.setText(teacher.getTdegree());
		ttitlelabel_1.setText(teacher.getTtitle());
		tdutylabel_1.setText(teacher.getTduty());
		ttellabel_1.setText(teacher.getTtel());
		temaillabel_1.setText(teacher.getTemail());
	}
}
