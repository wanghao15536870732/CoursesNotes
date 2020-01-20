package frame.student;
import frame.ChartJPanel;
import frame.admin.BaseJPanel;
import util.DialogUtil;
import util.FileUtil;
import util.GradeUtil;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 查询成绩界面
 */
public class GradeSearchJPanel extends BaseJPanel {

    private JTable table;
    private JButton searchByButton;  //查询按钮
    private JButton chartGradeButton;  //图标显示按钮
    private JLabel yearLabel;  //text 学年
    private JLabel termLabel;  //text 学期
    private JComboBox<String> yearBox; //  //选择 学年
    private JComboBox<String> termBox;  //选择学期
    private DefaultComboBoxModel<String> termModel; //选择学年String数组
    private DefaultComboBoxModel<String> yearModel;  //选择学期String数组
    private Object data[][];
    private JButton tableToFileBtn;

    GradeSearchJPanel(String sno, JFrame main) {
        super("查询成绩");
        setBounds(100, 100, 1000, 556);
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);
        initView();

        searchByButton.addActionListener(e -> {
            if (yearBox.getSelectedIndex() == 0){
                DialogUtil.showMessage("提示信息","请先选择学年！");
            }else if(termBox.getSelectedIndex() == 0){
                DialogUtil.showMessage("提示信息","请先选择学期！");
            }else {
                String year = yearBox.getSelectedItem().toString();
                String term = termBox.getSelectedItem().toString();
                Object[][] data = GradeUtil.getGradeBySno(sno,year,term);
                Object[] name = {"学号","姓名","课程号","课程名","学分","成绩"};
                remove(table);
                table = new JTable(data,name);
                table.setFont(new Font("楷体", Font.PLAIN, 18));
                table.setBounds(50, 150, 950, 200);
                setTableStyle(table);
                validate();
            }
        });

        chartGradeButton = new JButton("图表显示");
        chartGradeButton.setFont(new Font("楷体", Font.PLAIN, 18));
        chartGradeButton.setBounds(680, 144, 113, 30);
        add(chartGradeButton);
        chartGradeButton.addActionListener(e -> {
            String year = yearBox.getSelectedItem().toString();
            String term = termBox.getSelectedItem().toString();
            ChartJPanel chart = new ChartJPanel(sno,year,term);
            chart.setBounds(80,200,820,1000);
            add(chart);
            revalidate();
        });
        tableToFileBtn = new JButton("导出文件");
        tableToFileBtn.setFont(new Font("楷体", Font.PLAIN, 18));
        tableToFileBtn.setBounds(840, 144, 113, 30);
        add(tableToFileBtn);
        tableToFileBtn.addActionListener(e -> FileUtil.tableToFile(main,table));
    }

    private void setTableStyle(JTable table){
        //设置表格每一列的宽度
        table.setRowHeight(20);//设置表格的高度
        table.getTableHeader().setFont(new Font("楷体",Font.PLAIN, 18));//表头字体
        table.getTableHeader().setBackground(Color.GRAY);//表头背景色
        table.getTableHeader().setForeground(Color.WHITE);//表头前景色

        JScrollPane jScrollPane = new JScrollPane(table);
        jScrollPane.setBounds(50, 210, 950, 200);
        jScrollPane.setViewportView(table);
        add(jScrollPane);
    }

    private void initView(){
        yearBox = new JComboBox<>();
        yearBox.setFont(new Font("楷体", Font.PLAIN, 18));
        yearModel = new DefaultComboBoxModel<>(
                new String[] {"","2017-2018", "2018-2019", "2019-2020"}
        );
        yearBox.setModel(yearModel);
        yearBox.setBounds(142, 150, 131, 30);
        add(yearBox);

        yearLabel = new JLabel("学年：");
        yearLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        yearLabel.setBounds(67, 148, 72, 30);
        add(yearLabel);

        termLabel = new JLabel("学期：");
        termLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        termLabel.setBounds(320, 150, 72, 18);
        add(termLabel);

        termBox = new JComboBox<String>();
        termBox.setFont(new Font("楷体", Font.PLAIN, 18));
        termModel = new DefaultComboBoxModel<>(new String[] {"","第一学期", "第二学期"});
        termBox.setModel(termModel);
        termBox.setBounds(393, 145, 109, 30);
        add(termBox);

        searchByButton = new JButton("查询");
        searchByButton.setFont(new Font("楷体", Font.PLAIN, 18));
        searchByButton.setBounds(540, 144, 113, 30);
        add(searchByButton);
        table = new JTable();
        table.setBounds(50, 210, 950, 200);
        add(table);
    }
}
