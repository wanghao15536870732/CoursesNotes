package frame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class ChartFrame extends JFrame {

    public static String title = "查询支出";
    private static final int width = 600;
    private static final int height = 500;

    ChartFrame(){
        setTitle(title);
        int x = (int) this.getToolkit().getScreenSize().getWidth(); //获得屏幕宽度
        int y = (int) this.getToolkit().getScreenSize().getHeight(); //获得屏幕高度
        this.setSize(width, height);
        this.setLocation(x/2 - width/2, y/2 - height/2);

        JFreeChart chart = ChartFactory.createBarChart("支出统计","日期","支出（元）",
                getDataSet());
        // 从这里开始
        CategoryPlot plot = chart.getCategoryPlot();// 获取图表区域对象
        CategoryAxis domainAxis = plot.getDomainAxis(); // 水平底部列表
        domainAxis.setLabelFont(new Font("黑体", Font.BOLD, 14)); // 水平底部标题
        domainAxis.setTickLabelFont(new Font("宋体", Font.BOLD, 12)); // 垂直标题
        ValueAxis rangeAxis = plot.getRangeAxis();// 获取柱状
        rangeAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
        chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));// 设置标题字体

        ChartPanel chartPanel = new ChartPanel(chart, true);
        this.add(chartPanel);
        this.setVisible(true);
    }
    private static CategoryDataset getDataSet() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(10, "林钊", "2019-11-26");
        dataset.addValue(40, "何峰", "2019-11-26");
        dataset.addValue(20, "王浩", "2019-11-26");
        dataset.addValue(5, "高颖", "2019-11-26");

        dataset.addValue(9, "林钊", "2019-11-27");
        dataset.addValue(26, "何峰", "2019-11-27");
        dataset.addValue(14, "王浩", "2019-11-27");
        dataset.addValue(22, "高颖", "2019-11-27");

        dataset.addValue(34, "林钊", "2019-11-27");
        dataset.addValue(12, "何峰", "2019-11-27");
        dataset.addValue(23, "王浩", "2019-11-27");
        dataset.addValue(50, "高颖", "2019-11-27");
        return dataset;
    }

}
