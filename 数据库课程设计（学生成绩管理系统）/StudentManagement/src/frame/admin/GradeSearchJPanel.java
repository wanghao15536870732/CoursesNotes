package frame.admin;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import frame.ChartJPanel;
import util.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class GradeSearchJPanel extends BaseJPanel {

    private JTextField snoTextField;
    private JTable table;
    private JButton selectButton,deleteButton,changeButton,chartButton,exportButton;
    private JComboBox<String> yearComboBox,termComboBox;
    private JComboBox<String> deptComboBox,majorComboBox,classComboBox;
    private JScrollPane pane = new JScrollPane();
    private JTextField textField;
    private JLabel snoLabel,snoNameLabel,cnoLabel,cnoNameLabel,creditLabel;
    private JButton sureButton;

    public GradeSearchJPanel(JFrame main) {
        super("管理学生成绩");
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
            updateGrade();
        });

        changeButton.addActionListener(e -> {
            int selectRow = table.getSelectedRow();
            if (selectRow == -1){
                DialogUtil.showWarning("提示信息","还未选中信息！");
            }else {
                snoLabel.setText(table.getValueAt(selectRow,0).toString());
                snoNameLabel.setText(table.getValueAt(selectRow,1).toString());
                cnoLabel.setText(table.getValueAt(selectRow,2).toString());
                cnoNameLabel.setText(table.getValueAt(selectRow,3).toString());
                textField.setText(table.getValueAt(selectRow,4).toString());
                creditLabel.setText(table.getValueAt(selectRow,5).toString());
                sureButton.setEnabled(true);
                textField.setEnabled(true);
            }
        });
        deleteButton.addActionListener(e -> {
            int selectRow = table.getSelectedRow();
            if(selectRow == -1){
                DialogUtil.showWarning("提示信息","还未选中成绩");
            }else {
                String Sno = table.getValueAt(selectRow,0).toString();
                String Cno = table.getValueAt(selectRow,2).toString();
                try {
                    PreparedStatement statement = DBHelper.getConnect()
                            .prepareStatement("delete from csgrade where Sno=? and Cno=?;");
                    statement.setString(1,Sno);
                    statement.setString(2,Cno);
                    int result = statement.executeUpdate();
                    if(result > 0){
                        updateGrade();
                        DialogUtil.showWarning("提示信息","删除成功！");
                    }else {
                        DialogUtil.showWarning("提示信息","删除失败！");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        sureButton.addActionListener(e -> {
            try{
                PreparedStatement statement = DBHelper.getConnect()
                        .prepareStatement("update csgrade set grade=? where Sno=? and Cno=?;");
                statement.setString(1,textField.getText());
                statement.setString(2,snoLabel.getText());
                statement.setString(3,cnoLabel.getText());
                int result = statement.executeUpdate();
                if (result > 0){
                    updateGrade();
                    DialogUtil.showWarning("提示信息","修改成功！");
                    snoLabel.setText("");
                    snoNameLabel.setText("");
                    cnoLabel.setText("");
                    cnoNameLabel.setText("");
                    creditLabel.setText("");
                    textField.setText("");
                    textField.setEnabled(false);
                    sureButton.setEnabled(false);
                }else {
                    DialogUtil.showWarning("提示信息","修改失败！");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        chartButton.addActionListener(e -> {
            String cYear = yearComboBox.getSelectedItem().toString();
            String cTerm = termComboBox.getSelectedItem().toString();
            String cClass =  classComboBox.getSelectedItem().toString();
            ChartJFrame jFrame = new ChartJFrame("./src/pictures/class.png", cYear,cTerm,cClass);
        });

        exportButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileUtil.tableToFile(main,table);
            }
        });
    }

    private void updateGrade(){
        //获取输入的信息
        String cYear = yearComboBox.getSelectedItem().toString();
        String cTerm = termComboBox.getSelectedItem().toString();
        String sClass =  classComboBox.getSelectedItem().toString();
        String Sno = snoTextField.getText();
        Object name[] = {"学号", "姓名", "课程号", "课程名", "成绩", "学分"};
        remove(table);
        remove(pane);
        //查询某个班级的成绩
        if (Sno.equals("")) {
            Object data[][] = GradeUtil.getGradeByClass(sClass,cYear,cTerm);
            table = new JTable(data, name);
            table.addColumn(new TableColumn());
            table.getColumnModel().getColumn(6).setHeaderValue("选择");
            table.getColumnModel().getColumn(6).setCellRenderer(renderer);
            JScrollPane scrollPanel = getTableStyle(table);
            pane = scrollPanel;
            add(scrollPanel);
            validate();
        } else {
            Object[][] data = GradeUtil.getGradeBySno(Sno, cYear, cTerm);
            table = new JTable(data, name);
            table.addColumn(new TableColumn());
            table.getColumnModel().getColumn(6).setHeaderValue("选择");
            table.getColumnModel().getColumn(6).setCellRenderer(renderer);

            JScrollPane scrollPanel = getTableStyle(table);
            pane = scrollPanel;
            add(scrollPanel);
            validate();
        }
    }
    private static TableCellRenderer renderer = new TableCellRenderer() {
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

    private void initView(){
        JLabel classLabel = new JLabel("班级:");
        classLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        classLabel.setBounds(648, 112, 63, 34);
        add(classLabel);

        JLabel SnoLabel = new JLabel("学号：");
        SnoLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        SnoLabel.setBounds(99, 178, 60, 27);
        add(SnoLabel);

        snoTextField = new JTextField();
        snoTextField.setBounds(165, 177, 122, 28);
        snoTextField.setFont(new Font("楷体", Font.PLAIN, 20));
        add(snoTextField);
        snoTextField.setColumns(10);

        JLabel yearLabel = new JLabel("学年：");
        yearLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        yearLabel.setBounds(356, 56, 63, 34);
        add(yearLabel);

        yearComboBox = new JComboBox<String>();
        yearComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        yearComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"2018-2019", "2019-2020"}));
        yearComboBox.setBounds(420, 58, 133, 30);
        add(yearComboBox);

        JLabel termLabel = new JLabel("学期：");
        termLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        termLabel.setBounds(606, 56, 73, 34);
        add(termLabel);

        termComboBox = new JComboBox<String>();
        termComboBox.setModel(new DefaultComboBoxModel<>(new String[]{"第一学期", "第二学期"}));
        termComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        termComboBox.setBounds(680, 58, 133, 30);
        add(termComboBox);

        chartButton = new JButton("班级情况");
        chartButton.setFont(new Font("楷体", Font.PLAIN, 20));
        chartButton.setBounds(330, 176, 140, 30);
        add(chartButton);

        selectButton = new JButton("查询");
        selectButton.setFont(new Font("楷体", Font.PLAIN, 20));
        selectButton.setBounds(493, 176, 113, 30);
        add(selectButton);

        changeButton = new JButton("修改");
        changeButton.setFont(new Font("楷体", Font.PLAIN, 20));
        changeButton.setBounds(627, 176, 113, 30);
        add(changeButton);

        deleteButton = new JButton("删除");
        deleteButton.setFont(new Font("楷体", Font.PLAIN, 18));
        deleteButton.setBounds(764, 176, 113, 30);
        add(deleteButton);

        exportButton = new JButton("导出");
        exportButton.setFont(new Font("楷体", Font.PLAIN, 20));
        exportButton.setBounds(900, 176, 113, 30);
        add(exportButton);

        table = new JTable();
        table.setFont(new Font("楷体", Font.PLAIN, 20));
        table.setBounds(53, 221, 919, 322);
        add(table);

        JLabel lblNewLabel = new JLabel("\u5B66\u9662\uFF1A");
        lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel.setBounds(99, 116, 63, 30);
        add(lblNewLabel);

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
        classComboBox.setBounds(712, 114, 133, 30);
        classComboBox.setModel(new DefaultComboBoxModel<>(
                ClassUtil.getClassByMajor(majorComboBox.getSelectedItem().toString())));
        add(classComboBox);

        JLabel lblNewLabel_1 = new JLabel("\u4E13\u4E1A\uFF1A");
        lblNewLabel_1.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(349, 114, 63, 30);
        add(lblNewLabel_1);

        snoLabel = new JLabel("");
        snoLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        snoLabel.setBounds(70,600,90, 18);
        add(snoLabel);

        snoNameLabel = new JLabel("");
        snoNameLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        snoNameLabel.setBounds(220,600,90, 18);
        add(snoNameLabel);

        cnoLabel = new JLabel("");
        cnoLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        cnoLabel.setBounds(320,600,130, 18);
        add(cnoLabel);

        cnoNameLabel = new JLabel("");
        cnoNameLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        cnoNameLabel.setBounds(470,600,130, 18);
        add(cnoNameLabel);

        textField = new JTextField();
        textField.setFont(new Font("楷体", Font.PLAIN, 18));
        textField.setBounds(640,595,100, 30);
        textField.setEnabled(false);
        add(textField);

        creditLabel = new JLabel("");
        creditLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        creditLabel.setBounds(800,600,130, 18);
        add(creditLabel);

        sureButton = new JButton("确认修改");
        sureButton.setFont(new Font("楷体", Font.PLAIN, 18));
        sureButton.setBounds(870,595,113, 30);
        sureButton.setEnabled(false);
        add(sureButton);
    }
    private JScrollPane getTableStyle(JTable table){
        table.setFont(new Font("楷体", Font.PLAIN, 18));
        table.setBounds(53, 221, 919, 350);
        table.setRowHeight(20);
        table.getTableHeader().setFont(new Font("楷体", Font.PLAIN, 18));
        table.getTableHeader().setBackground(Color.gray);
        table.getTableHeader().setForeground(Color.white);
        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getColumnModel().getColumn(1).setPreferredWidth(40);
        table.getColumnModel().getColumn(2).setPreferredWidth(70);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(50);
        table.getColumnModel().getColumn(6).setPreferredWidth(30);
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, r);
        JScrollPane scrollPanel = new JScrollPane(table);
        scrollPanel.setViewportView(table);
        scrollPanel.setBounds(53, 221, 919, 350);
        scrollPanel.setFont(new Font("楷体", Font.PLAIN, 20));
        return scrollPanel;
    }
}
