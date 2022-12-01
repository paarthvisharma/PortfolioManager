package view.gui;

import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class implements the JPanel interface and contains the methods to help display
 * performance graph menu.
 */
public class PerformanceGraph extends JPanel {

  private int[] coordinates = {100, 100};
  private Graphics2D g1;
  private Graphics g;
  private int width;
  private int height;
  private int mar = 80;

  private int[] xyCoordinates;
  private String[] dates;
  private String[] dollarAmounts;

  /**
   * Constructor for the class to set up the initial view.
   */
  public PerformanceGraph(int[] xyCoordinates, String[] dates, String[] dollarAmounts) {
    this.xyCoordinates = xyCoordinates;
    this.dates = dates;
    this.dollarAmounts = dollarAmounts;
  }

  /**
   * Method to paint the component.
   *
   * @param g the <code>Graphics</code> object to protect.
   */
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.g = g;
    draw();
  }

  /**
   * Method to draw the plot.
   */
  public void draw() {
    int xGrid = xyCoordinates.length;
    int yGrid = 10;
    g1 = (Graphics2D) g;
    g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    width = getWidth();
    height = getHeight();
    g1.draw(new Line2D.Double(mar, mar, mar, height - mar));
    g1.draw(new Line2D.Double(mar, height - mar, width - mar, height - mar));
    enableGrid(xGrid, yGrid);
    g1.setPaint(Color.BLUE);
    g1.drawString("Dollar amount", mar - 50, mar - 20);
    g1.drawString("Date", width - mar + 20, height - mar);
    int xScale;
    int yScale;
    double unitValue = Arrays.stream(xyCoordinates).max().getAsInt() / yGrid;
    xScale = (width - (2 * mar)) / xGrid;
    yScale = (height - (2 * mar)) / yGrid;
    for (int i = 0; i <= xyCoordinates.length - 2; i = i + 1) {
      int x1;
      int x2;
      int y1;
      int y2;
      x1 = mar + (i * xScale);
      x2 = mar + ((i + 1) * xScale);
      y1 = height - mar - Double.valueOf((xyCoordinates[i] / unitValue) * yScale).intValue();
      y2 = height - mar - Double.valueOf((xyCoordinates[i + 1] / unitValue) * yScale).intValue();
      g1.draw(new Line2D.Double(x1, y1, x2, y2));
      g1.fill(new Ellipse2D.Double(x1 - 2, y1 - 2, 4, 4));
      g1.fill(new Ellipse2D.Double(x2 - 2, y2 - 2, 4, 4));
    }
  }

  /**
   * Method to enable grid for the plot.
   *
   * @param x x axis.
   * @param y y axis.
   */
  public void enableGrid(int x, int y) {
    int i = mar;
    Color initial = g1.getColor();
    Color grid = new Color(0, 0, 0, 50);
    g1.setColor(grid);
    while (i <= width - mar) {
      g1.draw(new Line2D.Double(i, mar + 3, i, height - mar + 3));
      i += (width - (2 * mar)) / x;
    }
    i = mar;
    while (i <= height - mar) {
      g1.draw(new Line2D.Double(mar, i + 3, width - mar, i + 3));
      i += (height - (2 * mar)) / y;
    }
    g1.setColor(initial);
    i = mar;
    int index = 0;
    while (index < dollarAmounts.length) {
      g1.drawString(dollarAmounts[dollarAmounts.length - 1 - index], mar - 50, i + 3);
      index += 1;
      i += (height - (2 * mar)) / y;
    }
    Font font = new Font(null, Font.PLAIN, 10);
    AffineTransform affineTransform = new AffineTransform();
    affineTransform.rotate(Math.toRadians(45), 0, 0);
    Font rotatedFont = font.deriveFont(affineTransform);
    Font initialFont = g1.getFont();
    g1.setFont(rotatedFont);
    i = mar;
    index = 0;
    while (index < dates.length) {
      g1.drawString(dates[index], i, height - mar + 10);
      index += 1;
      i += (width - (2 * mar)) / x;
    }
    g1.setFont(initialFont);
  }

  private int getMax() {
    int max = -Integer.MAX_VALUE;
    for (int i = 0; i < coordinates.length; i++) {
      if (coordinates[i] > max) {
        max = coordinates[i];
      }

    }
    return max;
  }

  /**
   * Method to plot thr graph.
   */
  public void plot() {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.add(new PerformanceGraph(xyCoordinates, dates, dollarAmounts));
    frame.setSize(800, 800);
    frame.setVisible(true);
  }
}
