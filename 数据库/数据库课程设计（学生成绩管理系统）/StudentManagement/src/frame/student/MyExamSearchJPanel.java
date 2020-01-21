package frame.student;

import frame.admin.BaseJPanel;
import util.ExamUtil;
import javax.swing.*;
import java.awt.*;

public class MyExamSearchJPanel extends BaseJPanel {
    private JTable table;
    private JButton searchBtn;

    public MyExamSearchJPanel(String sno) {
        super("查询考试");
        setLayout(null);
        initView();
        searchBtn.addActionListener(e -> {
            Object data[][] = ExamUtil.getMyExam(sno);
            Object name[] = {"班号","课程号","课程名","考试地点","开始时间","结束时间"};
            remove(table);
            table = new JTable(data,name);
            setTableStyle(table);
            validate();
        });
    }

    private void setTableStyle(JTable table){
        table.setRowHeight(20);//设置表格的高度
        table.getTableHeader().setFont(new Font("楷体",Font.PLAIN, 18));//表头字体
        table.getTableHeader().setBackground(Color.GRAY);//表头背景色
        table.getTableHeader().setForeground(Color.WHITE);//表头前景色
        table.setBounds(50, 212, 950, 377);
        table.setFont(new Font("楷体", Font.PLAIN, 18));
        table.getColumnModel().getColumn(0).setPreferredWidth(10);
        table.getColumnModel().getColumn(1).setPreferredWidth(10);
        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(50, 212, 950, 377);
        jScrollPane.setViewportView(table);
        add(jScrollPane);
    }

    private void initView(){
        table = new JTable();
        table.setBounds(50, 212, 950, 377);
        add(table);
        searchBtn = new JButton("查询");
        searchBtn.setFont(new Font("楷体", Font.PLAIN, 18));
        searchBtn.setBounds(91, 148, 113, 33);
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setBackground(new Color(0, 191, 255));
        add(searchBtn);
    }
}
