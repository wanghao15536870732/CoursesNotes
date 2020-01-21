package frame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import util.GradeUtil;

import javax.swing.*;
import java.awt.*;

/**
 * 图标展示
 */
public class ChartJPanel extends JPanel {

    private String sno,year,term;
    private DefaultCategoryDataset dataset;

    public ChartJPanel(String sno,String year,String term){
        this.sno = sno;
        this.year = year;
        this.term = term;
        dataset = new DefaultCategoryDataset();
        setDataSet(dataset);
        JFreeChart chart = ChartFactory.createBarChart("成绩查询","科目",
                "成绩/学分/学时", dataset);
        // 从这里开始
        CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象
        BarRenderer3D barRender = new BarRenderer3D();
        //展示柱状图数值
        barRender.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        barRender.setBaseItemLabelsVisible(true);
        barRender.setBasePositiveItemLabelPosition(new ItemLabelPosition(
                ItemLabelAnchor.OUTSIDE1, TextAnchor.BASELINE_CENTER));
        //最短的BAR长度，避免数值太小而显示不出
        barRender.setMinimumBarLength(0.5);
        // 设置柱形图上的文字偏离值
        barRender.setItemLabelAnchorOffset(10D);
        barRender.setBaseItemLabelFont(new Font("楷体",Font.PLAIN,14));
        plot.setRenderer(0,barRender);
        CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
        domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
        ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
        rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("楷体", Font.BOLD, 20));// 设置标题字体
        ChartPanel chartPanel = new ChartPanel(chart, true);

        add(chartPanel);
        setVisible(true);
    }

    private void setDataSet(DefaultCategoryDataset dataset) {
        Object data[][] = GradeUtil.getGradeToChart(sno,year,term);
        for (int i = 0; i < data.length; i++) {
            dataset.addValue(Double.parseDouble(data[i][1].toString()),"成绩",data[i][0].toString());
            dataset.addValue(Double.parseDouble(data[i][2].toString()),"学分",data[i][0].toString());
            dataset.addValue(Double.parseDouble(data[i][3].toString()),"学时",data[i][0].toString());
        }
    }
}
