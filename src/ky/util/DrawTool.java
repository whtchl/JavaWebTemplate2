 package ky.util;
 
 import java.awt.BasicStroke;
 import java.awt.Color;
 import java.awt.Font;
 import java.awt.geom.Ellipse2D.Double;
 import java.text.SimpleDateFormat;
 import java.util.Date;
 import org.jfree.chart.ChartFactory;
 import org.jfree.chart.JFreeChart;
 import org.jfree.chart.axis.CategoryAxis;
 import org.jfree.chart.axis.NumberAxis;
 import org.jfree.chart.axis.ValueAxis;
 import org.jfree.chart.labels.ItemLabelAnchor;
 import org.jfree.chart.labels.ItemLabelPosition;
 import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
 import org.jfree.chart.plot.CategoryPlot;
 import org.jfree.chart.plot.PlotOrientation;
 import org.jfree.chart.renderer.category.BarRenderer3D;
 import org.jfree.chart.renderer.category.CategoryItemRenderer;
 import org.jfree.chart.renderer.category.LineAndShapeRenderer;
 import org.jfree.chart.title.LegendTitle;
 import org.jfree.chart.title.TextTitle;
 import org.jfree.data.category.DefaultCategoryDataset;
 import org.jfree.ui.HorizontalAlignment;
 import org.jfree.ui.RectangleEdge;
 import org.jfree.ui.TextAnchor;
 
 public class DrawTool
 {
   public static JFreeChart drawBarChart(String title, String Xname, String Yname, DefaultCategoryDataset dataset)
   {
     JFreeChart chart = ChartFactory.createBarChart3D(title, Xname, Yname, 
       dataset, PlotOrientation.VERTICAL, true, false, false);
 
     TextTitle textTitle = chart.getTitle();
     textTitle.setFont(new Font("黑体", 0, 20));
 
     CategoryPlot categoryplot = (CategoryPlot)chart.getPlot();
     categoryplot.getRenderer().setSeriesPaint(0, new Color(10, 230, 230));
     NumberAxis numberaxis = (NumberAxis)categoryplot.getRangeAxis();
     CategoryAxis domainAxis = categoryplot.getDomainAxis();
 
     domainAxis.setTickLabelFont(new Font("黑体", 0, 10));
 
     domainAxis.setLabelFont(new Font("黑体", 0, 16));
 
     numberaxis.setTickLabelFont(new Font("sans-serif", 0, 14));
 
     numberaxis.setLabelFont(new Font("黑体", 0, 16));
 
     if (chart.getLegend() != null) {
       chart.getLegend().setItemFont(new Font("宋体", 0, 16));
     }
 
     BarRenderer3D renderer = (BarRenderer3D)categoryplot.getRenderer();
     renderer
       .setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
     renderer.setSeriesItemLabelsVisible(0, true);
 
     renderer.setBasePositiveItemLabelPosition(
       new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
     renderer.setItemLabelAnchorOffset(10.0D);
     return chart;
   }
 
   public static JFreeChart drawLineChart(String title, String Xname, String Yname, DefaultCategoryDataset dataset)
   {
     JFreeChart chart = ChartFactory.createLineChart(title, Xname, Yname, 
       dataset, PlotOrientation.VERTICAL, true, true, false);
 
     chart.getLegend().setItemFont(new Font("黑体", 0, 10));
 
     TextTitle textTitle = chart.getTitle();
     textTitle.setFont(new Font("黑色", 0, 16));
 
     chart.addSubtitle(new TextTitle("按月份"));
 
     TextTitle tt = new TextTitle("日期:" + 
       new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
 
     tt.setFont(new Font("黑体", 0, 12));
 
     tt.setPosition(RectangleEdge.BOTTOM);
 
     tt.setHorizontalAlignment(HorizontalAlignment.RIGHT);
 
     chart.addSubtitle(tt);
 
     CategoryPlot categoryplot = chart.getCategoryPlot();
 
     categoryplot.getDomainAxis().setTickLabelFont(new Font("黑体", 0, 10));
 
     categoryplot.getDomainAxis().setLabelFont(
       new Font("黑体", 0, 10));
 
     categoryplot.getRangeAxis().setLabelFont(new Font("黑体", 0, 10));
 
     LineAndShapeRenderer lasp = (LineAndShapeRenderer)categoryplot
       .getRenderer();
 
     lasp.setBaseShapesVisible(true);
 
     lasp.setDrawOutlines(true);
 
     lasp.setUseFillPaint(true);
 
     lasp.setBaseFillPaint(Color.yellow);
 
     lasp.setSeriesStroke(0, new BasicStroke(3.0F));
     lasp.setSeriesOutlineStroke(0, new BasicStroke(2.0F));
 
     lasp.setSeriesShape(0, new java.awt.geom.Ellipse2D.Double(-5D, -5D,
					10D, 10D));
     return chart;
   }
 }

/* Location:           C:\Users\Administrator\Desktop\ky_pms\WEB-INF\classes\
 * Qualified Name:     ky.util.DrawTool
 * JD-Core Version:    0.6.0
 */