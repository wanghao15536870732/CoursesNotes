package frame.student;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTable;
import frame.admin.BaseJPanel;
import handle.HandleSQL;
import util.DBHelper;
import util.DialogUtil;

public class CourseSearchJPanel extends BaseJPanel {
    private String Sno;
    private JTable courseTable;//课程表格
    private JComboBox<String> comBox_year;//选择学年
    private JComboBox<String> comBox_term;//选择学期
    private JButton btn_query;//查询按钮
    private JScrollPane pane = new JScrollPane();

    public CourseSearchJPanel(String Sno){
        super("查询课程");
        this.Sno = Sno ;
        setLayout(null);
        initComponent(); //界面设计
        setVisible(true);
        addEventHandler(); //事件处理
    }

    private void initComponent(){
        JLabel lb_year = new JLabel("学年:");
        lb_year.setFont(new Font("楷体", Font.PLAIN, 20));
        lb_year.setBounds(172, 120, 55, 18);
        add(lb_year);

        comBox_year = new JComboBox<String>();
        comBox_year.setFont(new Font("楷体", Font.PLAIN, 18));
        DefaultComboBoxModel<String> year_model =
                new DefaultComboBoxModel<>(new String[] {"","2017-2018", "2018-2019", "2019-2020"});
        comBox_year.setModel(year_model);
        comBox_year.setBounds(225, 115, 149, 30);
        add(comBox_year);

        JLabel lb_term = new JLabel("学期:");
        lb_term.setFont(new Font("楷体", Font.PLAIN, 20));
        lb_term.setBounds(442, 120, 105, 18);
        add(lb_term);

        comBox_term = new JComboBox<>();
        DefaultComboBoxModel<String> term_model = new DefaultComboBoxModel<>(new String[] {"","第一学期", "第二学期"});
        comBox_term.setModel(term_model);
        comBox_term.setFont(new Font("楷体", Font.PLAIN, 18));
        comBox_term.setBounds(500, 115, 115, 30);
        add(comBox_term);

        btn_query = new JButton("查询");
        btn_query.setForeground(Color.WHITE);
        btn_query.setBackground(new Color(0, 191, 255));
        btn_query.setFont(new Font("楷体", Font.PLAIN, 20));
        btn_query.setBounds(715, 112, 88, 35);
        add(btn_query);

        courseTable = new JTable();
        courseTable.setBounds(20, 180, 970, 400);
        add(courseTable);
    }

    public void addEventHandler() {
        btn_query.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comBox_year.getSelectedIndex() == 0){
                    DialogUtil.showMessage("提示信息","请先选择学年！");
                }else if (comBox_term.getSelectedIndex() == 0){
                    DialogUtil.showMessage("提示信息","请选择学期！");
                }else {
                    String cYear = comBox_year.getSelectedItem().toString();
                    String cTerm = comBox_term.getSelectedItem().toString();
                    handle_query(Sno,cYear,cTerm);
                }
            }
        });
    }

    void handle_query(String Sno,String Cyear,String Cterm) {
        HandleSQL handlesql =new HandleSQL(DBHelper.getConnect());
        Object data[][] = handlesql.getCosInfoBySQL(Sno,Cyear, Cterm);
        Object name[] = {"课程名","上课周数","上课地点","上课时间1","上课时间2","教师","学时","学分","属性"};
        remove(courseTable);
        remove(pane);
        courseTable = new JTable(data,name);
        courseTable.setBounds(20, 180, 970, 400);
        courseTable.setFont(new Font("楷体", Font.PLAIN, 18));

        //设置表格每一列的宽度
        courseTable.getColumnModel().getColumn(0).setPreferredWidth(135);
        courseTable.getColumnModel().getColumn(1).setPreferredWidth(120);
        courseTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        courseTable.getColumnModel().getColumn(3).setPreferredWidth(120);
        courseTable.getColumnModel().getColumn(4).setPreferredWidth(145);
        courseTable.getColumnModel().getColumn(5).setPreferredWidth(40);
        courseTable.getColumnModel().getColumn(6).setPreferredWidth(10);
        courseTable.getColumnModel().getColumn(7).setPreferredWidth(10);
        courseTable.getColumnModel().getColumn(8).setPreferredWidth(15);

        courseTable.setRowHeight(20);//设置表格的宽度
        courseTable.getTableHeader().setFont(new Font("楷体",Font.PLAIN, 18));//表头字体
        courseTable.getTableHeader().setBackground(Color.GRAY);//表头背景色
        courseTable.getTableHeader().setForeground(Color.WHITE);//表头前景色

        JScrollPane jsp = new JScrollPane(courseTable);
        jsp.setBounds(20, 180, 970, 400);
        jsp.setViewportView(courseTable);
        pane = jsp;
        add(jsp);
        remove(courseTable);
        validate();
    }
}
