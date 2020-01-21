package frame.admin;

import handle.HandleAdm;
import javax.swing.JLabel;
import java.awt.Font;

public class AdminInfoJPanel extends BaseJPanel {

    public AdminInfoJPanel(String Mno) {
        super("查询信息");
        setLayout(null);
        JLabel MnoLabel = new JLabel("管理员号：");
        MnoLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        MnoLabel.setBounds(87, 147, 116, 30);
        add(MnoLabel);

        JLabel NameLabel = new JLabel("姓名：");
        NameLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        NameLabel.setBounds(123, 217, 80, 30);
        add(NameLabel);

        JLabel label = new JLabel("性别：");
        label.setFont(new Font("楷体", Font.PLAIN, 20));
        label.setBounds(123, 287, 80, 30);
        add(label);

        JLabel label_1 = new JLabel("年龄：");
        label_1.setFont(new Font("楷体", Font.PLAIN, 20));
        label_1.setBounds(123, 357, 80, 30);
        add(label_1);

        JLabel label_2 = new JLabel("联系方式：");
        label_2.setFont(new Font("楷体", Font.PLAIN, 20));
        label_2.setBounds(343, 287, 111, 30);
        add(label_2);

        JLabel NametextLabel = new JLabel("");
        NametextLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        NametextLabel.setBounds(185, 217, 130, 30);
        add(NametextLabel);

        JLabel SextextLabel = new JLabel("");
        SextextLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        SextextLabel.setBounds(185, 287, 130, 30);
        add(SextextLabel);

        JLabel AgetextLabel = new JLabel("");
        AgetextLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        AgetextLabel.setBounds(185, 357, 130, 30);
        add(AgetextLabel);

        JLabel TeltextLabel = new JLabel("");
        TeltextLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        TeltextLabel.setBounds(455, 287, 130, 30);
        add(TeltextLabel);

        JLabel MnotextLabel = new JLabel("");
        MnotextLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        MnotextLabel.setBounds(185, 147, 130, 30);
        add(MnotextLabel);

        JLabel lblNewLabel = new JLabel("邮箱：");
        lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        lblNewLabel.setBounds(387, 357, 80, 30);
        add(lblNewLabel);

        JLabel EmailtextLabel = new JLabel("");
        EmailtextLabel.setFont(new Font("楷体", Font.PLAIN, 20));
        EmailtextLabel.setBounds(449, 357, 200, 30);
        add(EmailtextLabel);

        //显示信息
        String data[] = HandleAdm.showManager(Mno);
        MnotextLabel.setText(data[0]);
        NametextLabel.setText(data[1]);
        SextextLabel.setText(data[2]);
        AgetextLabel.setText(data[3]);
        TeltextLabel.setText(data[4]);
        EmailtextLabel.setText(data[5]);
    }
}
