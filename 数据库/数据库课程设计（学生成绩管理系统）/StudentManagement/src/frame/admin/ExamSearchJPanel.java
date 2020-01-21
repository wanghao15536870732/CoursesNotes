package frame.admin;
import java.awt.Color;
import javax.swing.JScrollPane;
import util.ClassUtil;
import util.ExamUtil;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ItemEvent;
import javax.swing.JTextField;

public class ExamSearchJPanel extends BaseJPanel {

    private JTable table;
    private JTextField SubjectTextField;
    private JScrollPane pane = new JScrollPane();
    private JComboBox<String> deptComboBox,majorComboBox,classComboBox;
    private JComboBox<String> yearComboBox,termComboBox;
    private JButton selectButton;

    public ExamSearchJPanel() {
        super("查询考试");
        setLayout(null);
        initView();
        deptComboBox.addItemListener(e -> {
            switch (e.getStateChange()){
                case ItemEvent.SELECTED:
                    majorComboBox.setModel(new DefaultComboBoxModel<>(
                            ClassUtil.getMajorByDept(e.getItem().toString())
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

        selectButton.addActionListener(e -> {
            remove(pane);
            String eYear = yearComboBox.getSelectedItem().toString();
            String eTerm = termComboBox.getSelectedItem().toString();
            String eSubject = SubjectTextField.getText();
            String eClass = classComboBox.getSelectedItem().toString();
            Object name[] = {"考试编号", "考试班号", "专业", "课程号", "课程名称", "考试地点", "开始时间", "结束时间"};
            remove(table);
            //查询某个班级的考试安排
            if (eSubject.equals("")) {
                Object[][] data = ExamUtil.getExamByClass(eClass,eYear,eTerm);
                table = new JTable(data, name);
                setTableStyle(table);
                JScrollPane scrollPanel = new JScrollPane(table);
                scrollPanel.setViewportView(table);
                scrollPanel.setBounds(24, 274, 990, 284);
                pane = scrollPanel;
                add(scrollPanel);
                validate();
            } else {//查询某个科目的考试安排
                Object[][] data = ExamUtil.getExamBySubject(eSubject,eClass);
                table = new JTable(data, name);
                setTableStyle(table);
                JScrollPane scrollPanel = new JScrollPane(table);
                scrollPanel.setViewportView(table);
                scrollPanel.setBounds(24, 274, 990, 284);
                scrollPanel.setFont(new Font("楷体", Font.PLAIN, 20));
                pane = scrollPanel;
                add(scrollPanel);
                validate();
            }
        });
    }

    private void setTableStyle(JTable table) {
        table.setFont(new Font("楷体", Font.PLAIN, 18));
        table.setBounds(24, 274, 990, 284);
        table.setRowHeight(20);
        table.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 18));
        table.getTableHeader().setBackground(Color.gray);
        table.getTableHeader().setForeground(Color.white);
        table.getColumnModel().getColumn(0).setPreferredWidth(15);
        table.getColumnModel().getColumn(1).setPreferredWidth(15);
        table.getColumnModel().getColumn(2).setPreferredWidth(80);
        table.getColumnModel().getColumn(3).setPreferredWidth(15);
        table.getColumnModel().getColumn(4).setPreferredWidth(80);
        table.getColumnModel().getColumn(5).setPreferredWidth(60);
        table.getColumnModel().getColumn(6).setPreferredWidth(80);
    }

    private void initView(){
        JLabel label = new JLabel("学年：");
        label.setFont(new Font("楷体", Font.PLAIN, 18));
        label.setBounds(128, 89, 54, 30);
        add(label);

        yearComboBox = new JComboBox<String>();
        yearComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        yearComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"2018-2019", "2019-2020"}));
        yearComboBox.setBounds(202, 89, 150, 30);
        add(yearComboBox);

        JLabel label_1 = new JLabel("学期");
        label_1.setFont(new Font("楷体", Font.PLAIN, 18));
        label_1.setBounds(419, 89, 66, 30);
        add(label_1);

        termComboBox = new JComboBox<>();
        termComboBox.setBounds(489, 89, 150, 30);
        termComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"第一学期", "第二学期"}));
        termComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        add(termComboBox);

        table = new JTable();
        table.setFont(new Font("楷体", Font.PLAIN, 18));
        table.setBounds(24, 274, 990, 284);
        add(table);

        selectButton = new JButton("查询");
        selectButton.setFont(new Font("楷体", Font.PLAIN, 18));
        selectButton.setBounds(755, 209, 113, 33);
        add(selectButton);

        JLabel label_2 = new JLabel("\u79D1\u76EE\uFF1A");
        label_2.setFont(new Font("楷体", Font.PLAIN, 18));
        label_2.setBounds(128, 207, 54, 30);
        add(label_2);

        JLabel label_3 = new JLabel("\u73ED\u7EA7\uFF1A");
        label_3.setFont(new Font("楷体", Font.PLAIN, 18));
        label_3.setBounds(706, 150, 66, 30);
        add(label_3);

        deptComboBox = new JComboBox<String>();
        deptComboBox.setBounds(202, 150, 150, 30);
        deptComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        deptComboBox.setModel(new DefaultComboBoxModel<>(ClassUtil.getAllDept()));
        add(deptComboBox);

        majorComboBox = new JComboBox<String>();
        majorComboBox.setBounds(489, 150, 170, 30);
        majorComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        majorComboBox.setModel(new DefaultComboBoxModel<>(
                ClassUtil.getMajorByDept(deptComboBox.getSelectedItem().toString())));
        add(majorComboBox);

        classComboBox = new JComboBox<String>();
        classComboBox.setModel(new DefaultComboBoxModel<>(ClassUtil.getAllClass()));
        classComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        classComboBox.setBounds(776, 150, 150, 30);
        classComboBox.setModel(new DefaultComboBoxModel<>(
                ClassUtil.getClassByMajor(majorComboBox.getSelectedItem().toString())));
        add(classComboBox);

        SubjectTextField = new JTextField();
        SubjectTextField.setFont(new Font("楷体", Font.PLAIN, 18));
        SubjectTextField.setBounds(202, 207, 150, 30);
        add(SubjectTextField);
        SubjectTextField.setColumns(10);

        JLabel lblNewLabel = new JLabel("\u5B66\u9662\uFF1A");
        lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel.setBounds(128, 150, 54, 30);
        add(lblNewLabel);
        JLabel lblNewLabel_1 = new JLabel("专业：");
        lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_1.setBounds(419, 150, 66, 30);
        add(lblNewLabel_1);
    }
}
