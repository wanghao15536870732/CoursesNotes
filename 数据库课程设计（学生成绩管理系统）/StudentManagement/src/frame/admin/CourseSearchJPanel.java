package frame.admin;
import util.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CourseSearchJPanel extends BaseJPanel {
    private JTable table;
    private JButton btnNewButton,tableToFileBtn,deleteBtn,changeButton;
    private JComboBox<String> deptBox,majorBox,arrComboBox;
    private JScrollPane pane = new JScrollPane();
    private boolean arr = true;

    public CourseSearchJPanel(JFrame main) {
        super("查询课程");
        setLayout(null);
        initView();
        deptBox.addItemListener(e -> {
            switch (e.getStateChange()){
                case ItemEvent.SELECTED:
                    majorBox.setModel(new DefaultComboBoxModel<>(
                            ClassUtil.getMajorByDept(e.getItem().toString())
                    ));
                    break;
                default:
                    break;
            }
        });
        tableToFileBtn.addActionListener(e -> FileUtil.tableToFile(main,table));
        arrComboBox.addItemListener(e -> {
            switch (e.getStateChange()){
                case ItemEvent.SELECTED:
                    if(e.getItem().toString().equals("已安排")){
                        arr = true;
                    }else if(e.getItem().toString().equals("未安排")) {
                        arr = false;
                    }
            }
        });
        changeButton.addActionListener(e -> {
            int rowIndex = table.getSelectedRow();
            if(rowIndex == -1){
                DialogUtil.showWarning("提示信息","请先选中课程！");
            }else {
                lblNewLabel_4.setText(table.getValueAt(rowIndex,0).toString());
                textField.setText(table.getValueAt(rowIndex,1).toString());
                textField_1.setText(table.getValueAt(rowIndex,2).toString());
                textField_2.setText(table.getValueAt(rowIndex,3).toString());
                textField_3.setText(table.getValueAt(rowIndex,4).toString());
                btnNewButton_1.setEnabled(true);
                textField.setEnabled(true);
                textField_1.setEnabled(true);
                textField_2.setEnabled(true);
                textField_3.setEnabled(true);
            }
        });

        btnNewButton_1.addActionListener(e -> {/////修改课程
            try {
                PreparedStatement statement = DBHelper.getConnect().prepareStatement("update course set Cname=?, " +
                        "Cperiod=?,Ccredit=?,Cattribute=? where Cno = ?;");
                statement.setString(1, textField.getText());
                statement.setString(2,textField_1.getText());
                statement.setString(3,textField_2.getText());
                statement.setString(4,textField_3.getText());
                statement.setString(5,lblNewLabel_4.getText());
                int result = statement.executeUpdate();
                if (result > 0){
                    updateCourse();
                    DialogUtil.showMessage("提示信息","更新成功！");
                    btnNewButton_1.setEnabled(false);
                    textField.setText("");
                    textField_1.setText("");
                    textField_2.setText("");
                    textField_3.setText("");
                    textField.setEnabled(false);
                    textField_1.setEnabled(false);
                    textField_2.setEnabled(false);
                    textField_3.setEnabled(false);
                }else {
                    DialogUtil.showWarning("提示信息","更新失败！");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        deleteBtn.addActionListener(e -> {
            int rowIndex = table.getSelectedRow();
            if (rowIndex == -1){
                DialogUtil.showMessage("提示信息","未有选中课程！");
            }else {
                boolean result = false;
                String Cno = table.getValueAt(rowIndex,0).toString();
                String Cname = table.getValueAt(rowIndex,1).toString();
                int Cperiod = Integer.parseInt(table.getValueAt(rowIndex,2).toString());
                float Ccredit = Float.parseFloat(table.getValueAt(rowIndex,3).toString());
                String Cattribute = table.getValueAt(rowIndex,4).toString();
                if (arr){
                    result = CourseUtil.deleteCourse(Cno,true);/////删除课程
                }else {
                    result = CourseUtil.deleteCourse(Cno,false);/////删除课程
                }
                if (result){
                    updateCourse();
                    DialogUtil.showMessage("提示信息","删除成功！");
                }else {
                    DialogUtil.showMessage("提示信息","删除失败！");
                }
            }
        });
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCourse();
            }
        });
    }

    private void updateCourse(){////查询课程
        remove(pane);
        if (deptBox.getSelectedItem().toString().equals("") && majorBox.getSelectedItem().toString().equals("")) {
            Object data[][] = CourseUtil.getCourses(arr);
            Object name[] = {"课程号","课程名称","课程学时","课程学分","课程属性"};
            remove(table);
            table = new JTable(data,name);
            table.addColumn(new TableColumn());
            table.getColumnModel().getColumn(5).setHeaderValue("选择");
            table.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
            table.getColumnModel().getColumn(1).setPreferredWidth(150);
            setTableStyle(table);
            JScrollPane jScrollPane = new JScrollPane(table);
            jScrollPane.setBounds(68, 205, 900, 350);
            jScrollPane.setViewportView(table);
            pane = jScrollPane;
            remove(table);
            add(jScrollPane);
            revalidate();
        }else if (majorBox.getSelectedItem().toString().equals("")){
            Object data[][] = CourseUtil.getCourseByDept(deptBox.getSelectedItem().toString(),arr);
            Object name[] = {"课程号","课程名称","课程学时","课程学分","课程属性","所属学院","学年","学期"};
            remove(table);
            table = new JTable(data,name);
            setTableStyle(table);
            table.addColumn(new TableColumn());
            table.getColumnModel().getColumn(8).setHeaderValue("选择");
            table.getColumnModel().getColumn(8).setCellRenderer(cellRenderer);
            table.getColumnModel().getColumn(1).setPreferredWidth(130);
            table.getColumnModel().getColumn(2).setPreferredWidth(40);
            table.getColumnModel().getColumn(3).setPreferredWidth(40);
            table.getColumnModel().getColumn(4).setPreferredWidth(40);
            table.getColumnModel().getColumn(5).setPreferredWidth(70);
            table.getColumnModel().getColumn(6).setPreferredWidth(50);
            table.getColumnModel().getColumn(7).setPreferredWidth(50);
            table.getColumnModel().getColumn(7).setPreferredWidth(30);
            JScrollPane jScrollPane = new JScrollPane(table);
            jScrollPane.setBounds(68, 205, 900, 350);
            jScrollPane.setViewportView(table);
            pane = jScrollPane;
            remove(table);
            add(jScrollPane);
            revalidate();
        }else if(deptBox.getSelectedItem().toString().equals("")){
            DialogUtil.showWarning("提示信息","请先选择学院！");
        }else {
            Object data[][] = CourseUtil.getCourseByMajor(deptBox.getSelectedItem().toString(),
                    majorBox.getSelectedItem().toString(),arr);
            Object name[] = {"课程号","课程名称","课程学时","课程学分","课程属性","所属专业","学年","学期"};
            remove(table);
            table = new JTable(data,name);
            table.addColumn(new TableColumn());
            table.getColumnModel().getColumn(8).setHeaderValue("选择");
            table.getColumnModel().getColumn(8).setCellRenderer(cellRenderer);
            setTableStyle(table);
            table.getColumnModel().getColumn(1).setPreferredWidth(110);
            table.getColumnModel().getColumn(2).setPreferredWidth(40);
            table.getColumnModel().getColumn(3).setPreferredWidth(40);
            table.getColumnModel().getColumn(4).setPreferredWidth(40);
            table.getColumnModel().getColumn(5).setPreferredWidth(120);
            table.getColumnModel().getColumn(6).setPreferredWidth(50);
            table.getColumnModel().getColumn(7).setPreferredWidth(50);
            table.getColumnModel().getColumn(8).setPreferredWidth(30);
            JScrollPane jScrollPane = new JScrollPane(table);
            jScrollPane.setBounds(68, 205, 900, 350);
            jScrollPane.setViewportView(table);
            pane = jScrollPane;
            remove(table);
            add(jScrollPane);
            revalidate();
        }
    }

    private static TableCellRenderer cellRenderer = new TableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            JCheckBox ck = new JCheckBox();
            // 使具有焦点的行对应的复选框选中
            ck.setSelected(isSelected);
            // 设置单选box.setSelected(hasFocus);
            // 使复选框在单元格内居中显示
            ck.setHorizontalAlignment((int) 0.5f);
            return ck;
        }
    };
    private JLabel lblNewLabel_4;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JButton btnNewButton_1;

    private void initView(){
        table = new JTable();
        setTableStyle(table);
        add(table);

        deptBox = new JComboBox<String>();
        deptBox.setModel(new DefaultComboBoxModel<>(new String[] {"","大数据学院", "经济管理学院"}));
        deptBox.setFont(new Font("楷体", Font.PLAIN, 20));
        deptBox.setBounds(170, 131, 162, 29);
        add(deptBox);

        majorBox = new JComboBox<String>();
        majorBox.setFont(new Font("楷体", Font.PLAIN, 20));
        majorBox.setModel(new DefaultComboBoxModel<>(
                new String[] {"","计算机科学与技术", "网络工程", "物联网工程", "数据科学与大数据技术"}));
        majorBox.setBounds(437, 132, 200, 30);
        add(majorBox);

        JLabel lblNewLabel_1 = new JLabel("学院：");
        lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(107, 130, 60, 30);
        add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("专业：");
        lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(371, 136, 72, 18);
        add(lblNewLabel_2);

        btnNewButton = new JButton("查询");
        btnNewButton.setFont(new Font("楷体", Font.PLAIN, 18));
        btnNewButton.setBounds(674, 64, 113, 30);
        add(btnNewButton);

        deleteBtn = new JButton("删除");
        deleteBtn.setFont(new Font("楷体", Font.PLAIN, 18));
        deleteBtn.setBounds(674, 131, 113, 30);
        add(deleteBtn);

        tableToFileBtn = new JButton("导出");
        tableToFileBtn.setFont(new Font("楷体", Font.PLAIN, 18));
        tableToFileBtn.setBounds(814, 131, 113, 30);
        add(tableToFileBtn);

        changeButton = new JButton("修改");
        changeButton.setFont(new Font("楷体", Font.PLAIN, 18));
        changeButton.setBounds(814, 64, 113, 30);
        add(changeButton);

        JLabel lblNewLabel_3 = new JLabel("安排情况：");
        lblNewLabel_3.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_3.setBounds(400, 63, 100, 30);
        add(lblNewLabel_3);

        arrComboBox = new JComboBox<String>();
        arrComboBox.setFont(new Font("楷体", Font.PLAIN, 20));
        arrComboBox.setModel(new DefaultComboBoxModel<>(new String[] {"已安排", "未安排"}));
        arrComboBox.setBounds(505, 63, 100, 30);
        add(arrComboBox);

        lblNewLabel_4 = new JLabel(" ");
        lblNewLabel_4.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_4.setBounds(79, 584, 89, 18);
        add(lblNewLabel_4);

        textField = new JTextField();
        textField.setFont(new Font("楷体", Font.PLAIN, 18));
        textField.setBounds(201, 581, 169, 28);
        add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("楷体", Font.PLAIN, 18));
        textField_1.setBounds(400, 581, 113, 28);
        add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("楷体", Font.PLAIN, 18));
        textField_2.setBounds(553, 581, 113, 28);
        add(textField_2);
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setFont(new Font("楷体", Font.PLAIN, 18));
        textField_3.setBounds(711, 581, 113, 28);
        add(textField_3);
        textField_3.setColumns(10);

        btnNewButton_1 = new JButton("确认修改");
        btnNewButton_1.setFont(new Font("楷体", Font.PLAIN, 18));
        btnNewButton_1.setBounds(879, 577, 113, 33);
        btnNewButton_1.setEnabled(false);
        textField.setEnabled(false);
        textField_1.setEnabled(false);
        textField_2.setEnabled(false);
        textField_3.setEnabled(false);
        add(btnNewButton_1);
    }

    private void setTableStyle(JTable table){
        table.setRowHeight(20);//设置表格的高度
        table.setBounds(68, 205, 900, 350);
        table.setFont(new Font("楷体",Font.PLAIN, 18));
        table.getTableHeader().setFont(new Font("楷体",Font.PLAIN, 18));//表头字体
        table.getTableHeader().setBackground(Color.GRAY);//表头背景色
        table.getTableHeader().setForeground(Color.WHITE);//表头前景色
    }
}
