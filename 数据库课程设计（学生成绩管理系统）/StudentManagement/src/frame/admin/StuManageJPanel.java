package frame.admin;

import handle.HandleStu;
import util.ClassUtil;
import util.DBHelper;
import util.DialogUtil;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StuManageJPanel extends BaseJPanel {
    private JComboBox<String> deptComboBox,majorComboBox,classComboBox;
    private JTable table;
    private JTextField textField;
    private JButton searchButton,deleteButton,changeButton;
    private JScrollPane pane = new JScrollPane();
    private static String deleteSQL = "DELETE FROM Student WHERE Sno=?;";
    private JLabel lblNewLabel_3;
    private JTextField textField_1;
    private JLabel lblNewLabel_4;
    private JTextField textField_2;
    private JLabel lblNewLabel_5;
    private JTextField textField_3;
    private JButton btnNewButton;
    private String oldSno;

    public StuManageJPanel() {
        super("学生管理");
        setLayout(null);
        initView();
        deptComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.SELECTED:
                        majorComboBox.setModel(new DefaultComboBoxModel<>(
                                ClassUtil.getMajorByDept(e.getItem().toString())
                        ));
                        break;
                }
            }
        });

        majorComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.SELECTED:
                        classComboBox.setModel(new DefaultComboBoxModel<>(
                                ClassUtil.getClassByMajor(e.getItem().toString())
                        ));
                        break;
                }
            }
        });
        searchButton.addActionListener(e -> {
            updateGrade();
        });

        /////删除学生
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int rowIndex = table.getSelectedRow();
                    System.out.println(rowIndex);
                    if (rowIndex == -1){
                        DialogUtil.showMessage("提示信息","还没有选择学生！");
                    }else {
                        String Sno = table.getValueAt(rowIndex,0).toString();
                        PreparedStatement statement = DBHelper.getConnect().prepareStatement(deleteSQL);
                        statement.setString(1,Sno);
                        int result = statement.executeUpdate();
                        if (result > 0){
                            DialogUtil.showMessage("提示信息","删除成功！");
                        }else {
                            DialogUtil.showMessage("提示信息","删除失败！");
                        }
                        updateGrade();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        changeButton.addActionListener(e -> {
            int selectRow = table.getSelectedRow();
            if (selectRow == -1){
                DialogUtil.showWarning("提示信息","未选中信息！");
            }else {
                oldSno = table.getValueAt(selectRow,0).toString();
                textField_1.setText(oldSno);
                textField_2.setText(table.getValueAt(selectRow,1).toString());
                textField_3.setText(table.getValueAt(selectRow,5).toString());
                btnNewButton.setEnabled(true);
                textField_2.setEnabled(true);
                textField_3.setEnabled(true);
            }
        });

        /////修改学生
        btnNewButton.addActionListener(e -> {
            try{
                PreparedStatement statement = DBHelper.getConnect()
                        .prepareStatement("update student set Spassword=?,Sclass=? where Sno=?;");
                statement.setString(1,textField_2.getText());
                statement.setString(2,textField_3.getText());
                statement.setString(3,textField_1.getText());
                int result = statement.executeUpdate();
                if (result > 0){
                    DialogUtil.showWarning("提示信息","修改成功！");
                    textField_1.setText("");
                    textField_2.setText("");
                    textField_3.setText("");
                    btnNewButton.setEnabled(false);
                    textField_2.setEnabled(false);
                    textField_3.setEnabled(false);
                    updateGrade();
                }else {
                    DialogUtil.showWarning("提示信息","修改失败！");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    //////查询学生
    private void updateGrade(){
        remove(pane);
        String sClass = classComboBox.getSelectedItem().toString();
        String Sno = textField.getText();
        Object name[] = {"学号","密码","姓名", "性别", "年龄", "班级", "电话","邮箱"};
        remove(table);
        //查询某个班级的成绩
        if (Sno.equals("")) {
            Object data[][] = HandleStu.getAllStudent(sClass);
            table = new JTable(data, name);

        }else {
            Object data[][] = HandleStu.getStudentBySno(Sno);
            table = new JTable(data, name);
        }
        table.addColumn(new TableColumn());
        table.getColumnModel().getColumn(8).setHeaderValue("选择");
        table.getColumnModel().getColumn(8).setCellRenderer((table, value, isSelected, hasFocus, row, column) -> {
            // 创建用于返回的渲染组件
            JCheckBox ck = new JCheckBox();
            // 使具有焦点的行对应的复选框选中
            ck.setSelected(isSelected);
            // 设置单选box.setSelected(hasFocus);
            // 使复选框在单元格内居中显示
            ck.setHorizontalAlignment((int) 0.5f);
            return ck;
        });
        JScrollPane scrollPanel = getTableStyle(table);
        pane = scrollPanel;
        add(scrollPanel);
        validate();
    }

    private void initView(){
        deptComboBox = new JComboBox<>();
        deptComboBox.setBounds(165, 116, 146, 30);
        deptComboBox.setFont(new Font("楷体", Font.PLAIN, 20));
        deptComboBox.setModel(new DefaultComboBoxModel<>(ClassUtil.getAllDept()));
        add(deptComboBox);

        majorComboBox = new JComboBox<>();
        majorComboBox.setFont(new Font("楷体", Font.PLAIN, 20));
        majorComboBox.setBounds(420, 116, 190, 30);
        majorComboBox.setModel(new DefaultComboBoxModel<>(
                ClassUtil.getMajorByDept(deptComboBox.getSelectedItem().toString())));
        add(majorComboBox);

        classComboBox = new JComboBox<>();
        classComboBox.setModel(new DefaultComboBoxModel<>(ClassUtil.getAllClass()));
        classComboBox.setFont(new Font("楷体", Font.PLAIN, 20));
        classComboBox.setBounds(712, 114, 133, 30);
        classComboBox.setModel(new DefaultComboBoxModel<>(
                ClassUtil.getClassByMajor(majorComboBox.getSelectedItem().toString())));
        add(classComboBox);

        table = new JTable();
        table.setBounds(55, 266, 936, 300);
        add(table);

        searchButton = new JButton("查询");
        searchButton.setFont(new Font("楷体", Font.PLAIN, 20));
        searchButton.setBounds(335, 194, 113, 30);
        add(searchButton);

        deleteButton = new JButton("删除");
        deleteButton.setFont(new Font("楷体", Font.PLAIN, 20));
        deleteButton.setBounds(480, 194, 113, 30);
        add(deleteButton);

        changeButton = new JButton("修改");
        changeButton.setFont(new Font("楷体", Font.PLAIN, 20));
        changeButton.setBounds(623, 194, 113, 30);
        add(changeButton);

        textField = new JTextField();
        textField.setBounds(175, 194, 132, 30);
        textField.setFont(new Font("楷体", Font.PLAIN, 20));
        add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel = new JLabel("学号：");
        lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel.setBounds(100, 200, 72, 18);
        add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("学院：");
        lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(99, 116, 63, 30);
        add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("专业：");
        lblNewLabel_2.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_2.setBounds(349, 116, 63, 30);
        add(lblNewLabel_2);

        JLabel classLabel = new JLabel("班级:");
        classLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        classLabel.setBounds(648, 112, 63, 34);
        add(classLabel);

        lblNewLabel_3 = new JLabel("\u5B66\u53F7\uFF1A");
        lblNewLabel_3.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_3.setBounds(69, 615, 72, 25);
        add(lblNewLabel_3);

        textField_1 = new JTextField();
        textField_1.setBounds(124, 615, 113, 28);
        textField_1.setFont(new Font("楷体", Font.PLAIN, 18));
        add(textField_1);
        textField_1.setColumns(10);

        lblNewLabel_4 = new JLabel("\u5BC6\u7801\uFF1A");
        lblNewLabel_4.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_4.setBounds(268, 615, 72, 25);
        add(lblNewLabel_4);

        textField_2 = new JTextField();
        textField_2.setBounds(326, 615, 122, 28);
        textField_2.setFont(new Font("楷体", Font.PLAIN, 18));
        add(textField_2);
        textField_2.setColumns(10);

        lblNewLabel_5 = new JLabel("\u73ED\u7EA7\uFF1A");
        lblNewLabel_5.setFont(new Font("楷体", Font.PLAIN, 18));
        lblNewLabel_5.setBounds(467, 615, 72, 25);
        add(lblNewLabel_5);

        textField_3 = new JTextField();
        textField_3.setBounds(524, 615, 122, 29);
        textField_3.setFont(new Font("楷体", Font.PLAIN, 18));
        add(textField_3);
        textField_3.setColumns(10);

        btnNewButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
        btnNewButton.setFont(new Font("楷体", Font.PLAIN, 18));
        btnNewButton.setBounds(854, 616, 113, 30);
        add(btnNewButton);

        btnNewButton.setEnabled(false);
        textField_1.setEnabled(false);
        textField_2.setEnabled(false);
        textField_3.setEnabled(false);
    }

    private JScrollPane getTableStyle(JTable table){
        table.setFont(new Font("楷体", Font.PLAIN, 18));
        table.setBounds(55, 266, 936, 300);
        table.setRowHeight(20);
        table.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 18));
        table.getTableHeader().setBackground(Color.gray);
        table.getTableHeader().setForeground(Color.white);
        table.getColumnModel().getColumn(0).setPreferredWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(30);
        table.getColumnModel().getColumn(2).setPreferredWidth(30);
        table.getColumnModel().getColumn(3).setPreferredWidth(30);
        table.getColumnModel().getColumn(4).setPreferredWidth(30);
        table.getColumnModel().getColumn(5).setPreferredWidth(60);
        table.getColumnModel().getColumn(6).setPreferredWidth(80);
        table.getColumnModel().getColumn(7).setPreferredWidth(160);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        JScrollPane scrollPanel = new JScrollPane(table);
        scrollPanel.setViewportView(table);
        scrollPanel.setBounds(55, 266, 936, 300);
        scrollPanel.setFont(new Font("楷体", Font.PLAIN, 20));
        return scrollPanel;
    }
}
