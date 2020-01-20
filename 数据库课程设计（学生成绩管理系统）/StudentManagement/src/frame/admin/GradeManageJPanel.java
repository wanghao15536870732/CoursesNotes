package data;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import frame.admin.BaseJPanel;
import handle.HandleAdm;
import util.DialogUtil;
import util.GradeUtil;

public class GradeManageJPanel extends BaseJPanel {

    public GradeManageJPanel() {
        super("成绩录入管理");
        setLayout(null);

        JLabel lblNewLabel = new JLabel("\u6210\u7EE9\u5F55\u5165\u529F\u80FD");
        lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 25));
        lblNewLabel.setBounds(112, 170, 158, 37);
        add(lblNewLabel);

        JRadioButton openRadioButton = new JRadioButton("\u6253\u5F00");
        openRadioButton.setBackground(new Color(176, 196, 222));
        openRadioButton.setFont(new Font("楷体", Font.PLAIN, 25));
        openRadioButton.setBounds(340, 177, 90, 27);
        add(openRadioButton);

        JRadioButton closeRadioButton = new JRadioButton("\u5173\u95ED");
        closeRadioButton.setBackground(new Color(176, 196, 222));
        closeRadioButton.setFont(new Font("楷体", Font.PLAIN, 25));
        closeRadioButton.setBounds(484, 177, 90, 27);
        add(closeRadioButton);

        JButton SureButton = new JButton("\u786E\u5B9A");
        SureButton.setFont(new Font("楷体", Font.PLAIN, 20));
        SureButton.setBounds(314, 358, 113, 27);
        add(SureButton);

        ButtonGroup group = new ButtonGroup();
        group.add(openRadioButton);
        group.add(closeRadioButton);

        boolean result = GradeUtil.checkGradeSystem();
        if(result){
            openRadioButton.setSelected(true);
        }else{
            closeRadioButton.setSelected(true);
        }

        SureButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (openRadioButton.isSelected()) {
                    boolean result = HandleAdm.trueMGrade();
                    if (result) {
                        DialogUtil.showMessage("提示信息", "成绩录入功能开启成功！");
                    } else {
                        DialogUtil.showMessage("提示信息", "成绩录入功能开启失败，请检查是否有误！");
                    }
                } else {
                    boolean result = HandleAdm.falseMGrade();
                    if (result) {
                        DialogUtil.showMessage("提示信息", "成绩录入功能关闭成功！");
                    } else {
                        DialogUtil.showMessage("提示信息", "成绩录入功能关闭失败，请检查是否有误！");
                    }
                }
            }
        });
    }
}
