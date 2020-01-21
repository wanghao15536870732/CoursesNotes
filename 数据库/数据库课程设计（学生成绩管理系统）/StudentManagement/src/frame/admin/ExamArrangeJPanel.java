package frame.admin;

import com.eltima.components.ui.DatePicker;
import util.ClassUtil;
import util.DialogUtil;
import util.ExamUtil;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ExamArrangeJPanel extends BaseJPanel {
    private JTextField enoTextField;
    private JTextField ePositionField;
    private JTextField eSubjectField;
    private JComboBox<String> deptComboBox,majorComboBox, classComboBox;
    private JComboBox<String> courseComboBox;
    private JButton cancelButton,sureButton;
    private JButton beginTimeButton,endTimeButton;
    private DatePicker datePickerBegin;
    private DatePicker datePickerEnd;

    public ExamArrangeJPanel() {
        super("安排考试");
        setLayout(null);
        initView();
        deptComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                majorComboBox.setModel(new DefaultComboBoxModel<>(
                        ClassUtil.getMajorByDept(e.getItem().toString())
                ));
            }
        });

        majorComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                classComboBox.setModel(new DefaultComboBoxModel<>(
                        ClassUtil.getClassByMajor(e.getItem().toString())
                ));
            }
        });
        courseComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                eSubjectField.setText(ClassUtil.getCourseByCname(e.getItem().toString()));
            }
        });
        cancelButton.addActionListener(e -> {

        });

        sureButton.addActionListener(e -> {
            String Eno = enoTextField.getText();
            String ECLno = classComboBox.getSelectedItem().toString();
            String ECno = ClassUtil.getCourseByCname(courseComboBox.getSelectedItem().toString());
            String EPosition = ePositionField.getText();
            if(Eno.equals("") || ECLno.equals("") || ECno.equals("") || EPosition.equals("")){
                DialogUtil.showWarning("提示信息","请完善信息");
            }else {
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date beginTime = null;
                Date endTime = null;
                try {
                    Timestamp sqlDate = new Timestamp(format.parse(datePickerBegin.getText()).getTime());
                    beginTime = new Date(sqlDate.getTime());
                    sqlDate = new Timestamp(format.parse(datePickerEnd.getText()).getTime());
                    endTime = new Date(sqlDate.getTime());
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                boolean result = ExamUtil.InsertExam(Eno,ECLno,ECno,EPosition,beginTime,endTime);
                if (result){
                    DialogUtil.showMessage("提示信息","添加成功！");
                }else {
                    DialogUtil.showMessage("提示信息","添加失败！");
                }
            }
        });
    }
    private void initView(){
        JLabel eNoLabel = new JLabel("考试编号：");
        eNoLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        eNoLabel.setBounds(66, 118, 96, 30);
        add(eNoLabel);

        enoTextField = new JTextField();
        enoTextField.setFont(new Font("楷体", Font.PLAIN, 18));
        enoTextField.setBounds(160, 119, 166, 30);
        add(enoTextField);
        enoTextField.setColumns(10);

        JLabel eTimeLabel = new JLabel("考试时间：");
        eTimeLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        eTimeLabel.setBounds(66, 266, 96, 30);
        add(eTimeLabel);

        JLabel ePositionLabel = new JLabel("考试地点：");
        ePositionLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        ePositionLabel.setBounds(66, 404, 96, 30);
        add(ePositionLabel);

        ePositionField = new JTextField();
        ePositionField.setFont(new Font("楷体", Font.PLAIN, 18));
        ePositionField.setBounds(160, 405, 166, 30);
        add(ePositionField);
        ePositionField.setColumns(10);

        JLabel eSubjectLabel = new JLabel("考试科目：");
        eSubjectLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        eSubjectLabel.setBounds(66, 190, 96, 30);
        add(eSubjectLabel);

        courseComboBox = new JComboBox<>();
        courseComboBox.setBounds(160, 192, 180, 30);
        courseComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        courseComboBox.setModel(new DefaultComboBoxModel<>(ClassUtil.getAllArrCourse()));
        add(courseComboBox);

        eSubjectField = new JTextField();
        eSubjectField.setFont(new Font("楷体", Font.PLAIN, 18));
        eSubjectField.setBounds(350, 191, 166, 30);
        add(eSubjectField);
        eSubjectField.setEnabled(false);
        eSubjectField.setColumns(10);

        JLabel eClassLabel = new JLabel("考试班级：");
        eClassLabel.setFont(new Font("楷体", Font.PLAIN, 18));
        eClassLabel.setBounds(400, 118, 91, 30);
        add(eClassLabel);

        cancelButton = new JButton("取消");
        cancelButton.setFont(new Font("楷体", Font.PLAIN, 18));
        cancelButton.setBounds(228, 483, 113, 27);
        add(cancelButton);

        sureButton = new JButton("确定");
        sureButton.setFont(new Font("楷体", Font.PLAIN, 18));
        sureButton.setBounds(418, 483, 113, 27);
        add(sureButton);

        deptComboBox = new JComboBox<String>();
        deptComboBox.setBounds(505, 120, 135, 30);
        deptComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        deptComboBox.setModel(new DefaultComboBoxModel<>(ClassUtil.getAllDept()));
        add(deptComboBox);

        majorComboBox = new JComboBox<String>();
        majorComboBox.setBounds(664, 120, 220, 30);
        majorComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        majorComboBox.setModel(new DefaultComboBoxModel<>(
                ClassUtil.getMajorByDept(deptComboBox.getSelectedItem().toString())));
        add(majorComboBox);

        classComboBox = new JComboBox<String>();
        classComboBox.setModel(new DefaultComboBoxModel<>(ClassUtil.getAllClass()));
        classComboBox.setFont(new Font("楷体", Font.PLAIN, 18));
        classComboBox.setBounds(910, 120, 125, 30);
        classComboBox.setModel(new DefaultComboBoxModel<>(
                ClassUtil.getClassByMajor(majorComboBox.getSelectedItem().toString())));
        add(classComboBox);

        beginTimeButton = new JButton("（开始时间）");
        beginTimeButton.setFont(new Font("楷体", Font.PLAIN, 18));
        beginTimeButton.setBounds(351, 266, 150, 30);
        add(beginTimeButton);

        datePickerBegin = getDatePicker();
        datePickerBegin.setBounds(160, 266,180, 30);//设置起始位置
        add(datePickerBegin);

        datePickerEnd = getDatePicker();
        datePickerEnd.setBounds(160, 326,180, 30);//设置起始位置
        add(datePickerEnd);

        endTimeButton = new JButton("（结束时间）");
        endTimeButton.setFont(new Font("楷体", Font.PLAIN, 18));
        endTimeButton.setBounds(350, 328, 150, 30);
        add(endTimeButton);
    }

    private static DatePicker getDatePicker() {
        final DatePicker datePick;
        // 格式
        String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
        // 当前时间
        java.util.Date date = new java.util.Date();
        // 字体
        Font font = new Font("Times New Roman", Font.BOLD, 18);
        Dimension dimension = new Dimension(300, 30);
        int[] highLightDays = { 1, 3, 5, 7 };
        int[] disabledDays = { 4, 6, 5, 9 };
        //构造方法（初始时间，时间显示格式，字体，控件大小）
        datePick = new DatePicker(date, DefaultFormat, font, dimension);
        // 设置一个月份中需要高亮显示的日子
        datePick.setHightlightdays(highLightDays, Color.red);
        // 设置一个月份中不需要的日子，呈灰色显示
        datePick.setDisableddays(disabledDays);
        // 设置国家
        datePick.setLocale(Locale.CANADA);
        // 设置时钟面板可见
        datePick.setTimePanleVisible(true);
        return datePick;
    }
}
