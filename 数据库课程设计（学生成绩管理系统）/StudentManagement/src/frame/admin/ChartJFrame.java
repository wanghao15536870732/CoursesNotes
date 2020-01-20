package frame.admin;

import frame.BaseJFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import sun.awt.image.ToolkitImage;
import util.GradeUtil;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class ChartJFrame extends JFrame {
    private static String cYear,cTerm,cClass;

    public ChartJFrame(String srcPath, String cYear, String cTerm, String cClass) {
        this.cClass = cClass;
        this.cYear = cYear;
        this.cTerm = cTerm;
        //设置数据源
        PieDataset mDataset = GetDataset();
        //建立图表
        JFreeChart mChart = ChartFactory.createPieChart3D(cClass + "班级课程成绩情况",
                mDataset, true, true, false);
        //设置图表标题
        mChart.setTitle(new TextTitle(cClass + "班级课程成绩情况",
                new Font("黑体",Font.CENTER_BASELINE, 20)));
        //设置Legend字体
        mChart.getLegend().setItemFont(new Font("楷体", Font.ROMAN_BASELINE, 16));

        PiePlot3D mPiePlot = (PiePlot3D)mChart.getPlot();
        // 图片中显示百分比:自定义方式，{0} 表示选项， {1} 表示数值， {2} 表示所占比例 ,小数点后两位
        mPiePlot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})",
                NumberFormat.getNumberInstance(), new DecimalFormat("0.00%")));
        // 底部图例显示百分比:自定义方式， {0} 表示选项， {1} 表示数值， {2} 表示所占比例
        mPiePlot.setLegendLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})"));
        //设置饼图标签字体
        mPiePlot.setLabelFont(new Font("楷体", Font.PLAIN, 16));
        //内置对象显示图表
        ChartFrame mChartFrame = new ChartFrame("班级课程成绩情况图示", mChart);
        mChartFrame.pack();
        int x = (int) this.getToolkit().getScreenSize().getWidth();
        int y = (int) this.getToolkit().getScreenSize().getHeight();
        mChartFrame.setSize(1000,600);
        mChartFrame.setLocation(x/2 - 1000/2,y/2 - 600/2);
        mChartFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(srcPath));
        mChartFrame.setVisible(true);
    }

    public static PieDataset GetDataset() {
        DefaultPieDataset mDataset = new DefaultPieDataset();
        String course[] = GradeUtil.getGradeDate(cYear,cTerm,cClass);
        for (int i = 0;i < course.length;i ++){
            mDataset.setValue(" "+ course[i] + "均分", new Double(GradeUtil.getGradeChartByClass(course[i],cYear,cTerm,cClass)));
        }
        return mDataset;
    }
}
