package frame.admin;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import handle.HandleTea;
import util.ClassUtil;
import util.DBHelper;
import util.DialogUtil;
import java.awt.event.ItemEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeaManageJPanel extends BaseJPanel {

    private JTextField textField;
    private JTable table;
    private JButton searchButton,changeButton,deleteButton,initializeButton;
    private JComboBox<String> deptComboBox;
    private JComboBox<String> teacherComboBox;
    private JTextField textField_1,textField_2,textField_3,textField_4,textField_5,textField_6,textField_7;
    private JButton sureButton;
    private JScrollPane pane = new JScrollPane();

    public TeaManageJPanel() {
        super("教师管理");
        setLayout(null);
        initView();

        //////查询教师
        searchButton.addActionListener(e -> {
            if (textField.getText().equals("")){
                DialogUtil.showWarning("提示信息","请先输入教师号！");
            }else {
                //获取输入的信息
                String Tno = textField.getText();
                Object name[] = {"教师号", "姓名", "性别", "年龄", "学院", "学历", "职称", "职务", "联系方式", "邮箱"};
                remove(table);
                remove(pane);
                Object data[][] = HandleTea.SelectTeacher(Tno);
                table = new JTable(data, name);
                setTableStyle(table);
                JScrollPane scrollPanel = new JScrollPane(table);
                scrollPanel.setViewportView(table);
                scrollPanel.setBounds(55, 260, 900, 300);
                pane = scrollPanel;
                add(scrollPanel);
                validate();
            }
            sureButton.setEnabled(true);
            deleteButton.setEnabled(true);
            changeButton.setEnabled(true);
        });

        changeButton.addActionListener(e -> {
            int index = table.getSelectedRow();
            System.out.println(index);
            if (index == -1){
                DialogUtil.showMessage("提示信息","未选择老师，无法进行修改！");
            }else {
                int row = table.getSelectedRow();
                String tNo = table.getValueAt(row, 0).toString();
                String tName = table.getValueAt(row, 1).toString();
                String tSex = table.getValueAt(row, 2).toString();
                String tAge = table.getValueAt(row, 3).toString();
                String tDept = table.getValueAt(row, 4).toString();
                String tTel =  table.getValueAt(row, 8).toString();
                String tEmail = table.getValueAt(row, 9).toString();
                textField_1.setText(tNo);
                textField_2.setText(tName);
                textField_3.setText(tSex);
                textField_4.setText(tAge);
                textField_5.setText(tDept);
                textField_6.setText(tTel);
                textField_7.setText(tEmail);
                textField_1.setEnabled(false);
                textField_5.setEnabled(false);
                sureButton.setEnabled(true);
                deleteButton.setEnabled(false);
                changeButton.setEnabled(false);
            }
        });

        /////修改教师
        sureButton.addActionListener(e -> {
            String tNo = textField_1.getText();
            String tName = textField_2.getText();
            String tSex = textField_3.getText();
            String tAge = textField_4.getText();
            String tDept = textField_5.getText();
            String tTel = textField_6.getText();
            String tEmail = textField_7.getText();
            boolean result = HandleTea.UpdateTeacher(tNo, tName, tSex, tAge, tDept, tTel, tEmail);
            if (result) {
                DialogUtil.showMessage("提示信息", "修改信息成功！");
                sureButton.setEnabled(false);
                deleteButton.setEnabled(false);
                changeButton.setEnabled(false);
            } else {
                DialogUtil.showMessage("提示信息", "修改信息失败，请检查是否有误！");
            }
        });

        ////删除教师
        deleteButton.addActionListener(e -> {
            int index = table.getSelectedRow();
            if (index == -1){
                DialogUtil.showMessage("提示信息","未选择老师，无法进行删除！");
            }else {
                int row = table.getSelectedRow();
                String Tno = (String) table.getValueAt(row, 0);
                boolean result = HandleTea.DeleteTeacher(Tno);
                if (result) {
                    DialogUtil.showMessage("提示信息", "删除信息成功！");
                    sureButton.setEnabled(false);
                    deleteButton.setEnabled(false);
                    changeButton.setEnabled(false);
                } else {
                    DialogUtil.showMessage("提示信息", "删除信息失败，请检查是否有误！");
                }
            }
        });

        //////密码初始化为123456
        initializeButton.addActionListener(e -> {
            int index = table.getSelectedRow();
            if (index == -1){
                DialogUtil.showMessage("提示信息","未选择老师，无法进行初始化！");
            }else {
                String Tno = textField.getText();
                boolean result = HandleTea.InitializeTeacher(Tno);
                if (result) {
                    DialogUtil.showMessage("提示信息", "初始化密码成功！");
                } else {
                    DialogUtil.showMessage("提示信息", "初始化密码失败，请检查是否有误！");
                }
            }

        });

        deptComboBox.addItemListener(e -> {
            switch (e.getStateChange()){
                case ItemEvent.SELECTED:
                    teacherComboBox.setModel(new
                            DefaultComboBoxModel<>(getTeaByDept(e.getItem().toString(),
                            "select Tname from teacher where Tdept=?;")));
                    break;
            }
        });
        teacherComboBox.addItemListener(e -> {
            switch (e.getStateChange()){
                case ItemEvent.SELECTED:
                    textField.setText(getTeaByDept(teacherComboBox.getSelectedItem().toString(),
                            "select Tno from teacher where Tname=?;")[0]);
            }
        });
    }

    private void setTableStyle(JTable table){
        table.setFont(new Font("楷体", Font.PLAIN, 18));
        table.setBounds(55, 260, 900, 300);
        table.setRowHeight(20);
        table.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 18));
        table.getTableHeader().setBackground(Color.gray);
        table.getTableHeader().setForeground(Color.white);
        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(40);
        table.getColumnModel().getColumn(2).setPreferredWidth(40);
        table.getColumnModel().getColumn(3).setPreferredWidth(40);
        table.getColumnModel().getColumn(4).setPreferredWidth(100);
        table.getColumnModel().getColumn(5).setPreferredWidth(40);
        table.getColumnModel().getColumn(6).setPreferredWidth(50);
        table.getColumnModel().getColumn(7).setPreferredWidth(90);
        table.getColumnModel().getColumn(8).setPreferredWidth(90);
        table.getColumnModel().getColumn(9).setPreferredWidth(140);
        table.addColumn(new TableColumn());
        table.getColumnModel().getColumn(10).setHeaderValue("选择");
        table.getColumnModel().getColumn(10).setCellRenderer(renderer);
        table.getColumnModel().getColumn(10).setPreferredWidth(40);

    }

    private void initView(){
        JLabel label = new JLabel("\u6559\u5E08\u53F7\uFF1A");
        label.setFont(new Font("楷体", Font.PLAIN, 20));
        label.setBounds(508, 121, 80, 30);
        add(label);

        textField = new JTextField();
        textField.setFont(new Font("楷体", Font.PLAIN, 18));
        textField.setBounds(764, 122, 127, 30);
        add(textField);
        textField.setColumns(10);

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

        initializeButton = new JButton("密码初始化");
        initializeButton.setFont(new Font("楷体", Font.PLAIN, 20));
        initializeButton.setBounds(766, 194, 140, 32);
        add(initializeButton);

        table = new JTable();
        table.setBounds(55, 260, 925, 300);
        add(table);

        JLabel lblNewLabel = new JLabel("\u5B66\u9662\uFF1A");
        lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel.setBounds(228, 121, 72, 30);
        add(lblNewLabel);

        deptComboBox = new JComboBox<String>();
        deptComboBox.setBounds(290, 123, 145, 30);
        deptComboBox.setFont(new Font("楷体", Font.PLAIN, 20));
        deptComboBox.setModel(new DefaultComboBoxModel<>(ClassUtil.getAllDept()));
        add(deptComboBox);

        teacherComboBox = new JComboBox<>();
        teacherComboBox.setBounds(591, 123, 140, 30);
        teacherComboBox.setFont(new Font("楷体", Font.PLAIN, 20));
        teacherComboBox.setModel(new DefaultComboBoxModel<>(getTeaByDept(deptComboBox.getSelectedItem().toString(),
                "select Tname from teacher where Tdept=?;")));
        add(teacherComboBox);

        textField.setText(getTeaByDept(teacherComboBox.getSelectedItem().toString(),
                "select Tno from teacher where Tname=?;")[0]);

        textField_1 = new JTextField();
        textField_1.setFont(new Font("楷体", Font.PLAIN, 18));
        textField_1.setBounds(65, 591, 72, 30);
        add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setFont(new Font("楷体", Font.PLAIN, 18));
        textField_2.setBounds(151, 591, 72, 30);
        add(textField_2);
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setFont(new Font("楷体", Font.PLAIN, 18));
        textField_3.setBounds(237, 591, 72, 30);
        add(textField_3);
        textField_3.setColumns(10);

        textField_4 = new JTextField();
        textField_4.setFont(new Font("楷体", Font.PLAIN, 18));
        textField_4.setBounds(319, 591, 86, 30);
        add(textField_4);
        textField_4.setColumns(10);

        textField_5 = new JTextField();
        textField_5.setFont(new Font("楷体", Font.PLAIN, 18));
        textField_5.setBounds(419, 591, 113, 30);
        add(textField_5);
        textField_5.setColumns(10);

        textField_6 = new JTextField();
        textField_6.setFont(new Font("楷体", Font.PLAIN, 18));
        textField_6.setBounds(546, 591, 127, 30);
        add(textField_6);
        textField_6.setColumns(10);

        textField_7 = new JTextField();
        textField_7.setFont(new Font("楷体", Font.PLAIN, 18));
        textField_7.setBounds(698, 591, 150, 30);
        add(textField_7);
        textField_7.setColumns(10);

        sureButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
        sureButton.setFont(new Font("楷体", Font.PLAIN, 18));
        sureButton.setBounds(880, 589, 113, 33);
        sureButton.setEnabled(false);
        add(sureButton);

        deleteButton.setEnabled(false);
        changeButton.setEnabled(false);
    }

    TableCellRenderer renderer = new TableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            // 创建用于返回的渲染组件
            JCheckBox ck = new JCheckBox();
            // 使具有焦点的行对应的复选框选中
            ck.setSelected(isSelected);
            // 设置单选box.setSelected(hasFocus);
            // 使复选框在单元格内居中显示
            ck.setHorizontalAlignment((int) 0.5f);
            return ck;
        }
    };

    private String[] getTeaByDept(String deptName,String sql){
        int index = 0;
        try{
            PreparedStatement statement = DBHelper.getConnect().prepareStatement(sql);
            statement.setString(1,deptName);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                index ++;
            }
            String[] result = new String[index];
            resultSet = statement.executeQuery();
            index = 0;
            while (resultSet.next()){
                result[index] = resultSet.getString(1);
                index ++;
            }
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[0];
    }
}
