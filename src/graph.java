package src;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.FontMetrics;

//task nine
public class graph extends JPanel{

    int[] averageGenerations;
    float[] averageTime;
    int[] valueOfN;

    //set up window
    public void displayGraph() {
        JFrame window = new JFrame("Graph");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(600, 500);
        window.setContentPane(this);
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    //get needed arrays from base class
    public graph(int[] averageGenerations, float[] averageTime, int[] valueOfN) {
        this.averageGenerations = averageGenerations;
        this.averageTime = averageTime;
        this.valueOfN = valueOfN;
    }

    //draw the graph
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //padding = the offset.
        //graphWidth = the windowWidth of the graph, graphHeight = the windowHeight of the graph
        //xRange = the range of values for n

        //int x1 = padding + ((valueOfN[i] - minLength) * (centreWidth) / (xRange));
        //offsets the x cord (padding), then sets x data to start from 0 (valueOfN[i] - minLength), then scales to graph
        // size (* graphWidth), then divides to be a fraction of the total range (/ xRange)

        //int y1 = (windowHeight - padding) - (averageGenerations[i] * (graphHeight) / maxGenerations);
        //offsets the y cord (windowHeight - padding), then sets data (averageGenerations[i, ]then scales to graph size
        // (* graphHeight), then divides to be a fraction of total generations (/ maxgenerations)


        int windowWidth = getWidth();
        int windowHeight = getHeight();
        int padding = 50;
        int maxLength = valueOfN[valueOfN.length - 1];
        int minLength = valueOfN[0];
        int graphWidth = windowWidth - (2 * padding);
        int graphHeight = windowHeight - ( 2 * padding);
        int xRange = maxLength - minLength;

        //find max generations
        int maxGenerations = 0;
        for (int h: averageGenerations) {
            if (h > maxGenerations) {
                maxGenerations = h;
            }
        }

        //find max time
        float maxTime = 0;
        for (float h2: averageTime) {
            if (h2 > maxTime) {
                maxTime = h2;
            }
        }

        //draw axis
        g2.setColor(Color.DARK_GRAY);
        g2.drawLine(padding, windowHeight - padding, windowWidth - padding, windowHeight - padding);
        g2.drawLine(padding, padding, padding, windowHeight - padding);

        //draw line for generations
        g2.setColor(Color.BLUE);
        for (int i = 0; i < valueOfN.length - 1; i++) {
            int x1 = padding + ((valueOfN[i] - minLength) * (graphWidth) / (xRange));
            int y1 = (windowHeight - padding) - (averageGenerations[i] * (graphHeight) / maxGenerations);
            int x2 = padding + ((valueOfN[i+1] - minLength) * (graphWidth) / (xRange));
            int y2 = (windowHeight - padding) - (averageGenerations[i+1] * (graphHeight) / maxGenerations);
            g2.drawLine(x1, y1, x2, y2);
        }

        //draw line for time
        g2.setColor(Color.RED);
        for (int i2 = 0; i2 < valueOfN.length - 1; i2++) {
            int x1 = padding + ((valueOfN[i2] - minLength) * (graphWidth) / (xRange));
            int y1 = (int) ((windowHeight - padding) - (averageTime[i2] * (graphHeight) / maxTime));
            int x2 = padding + ((valueOfN[i2+1] - minLength) * (graphWidth) / (xRange));
            int y2 = (int) ((windowHeight - padding) - (averageTime[i2+1] * (graphHeight) / maxTime));
            g2.drawLine(x1, y1, x2, y2);
        }

        //draw axis labels
        g2.setColor(Color.BLACK);
        g2.drawString("Length n", (windowWidth / 2) - 30, windowHeight - 15);
        g2.setColor(Color.BLUE);
        g2.drawString("Generations", 10, padding - 10);
        g2.setColor(Color.RED);
        g2.drawString("Time (ms)", 10, padding - 25);

        g2.setColor(Color.BLACK);

        //draw x axis scale
        for (int i3  = 0; i3 < valueOfN.length; i3++) {
            int x = padding + (valueOfN[i3] - minLength) * (graphWidth) / (xRange);
            int y = windowHeight - padding;
            g2.drawLine(x, y, x, y + 5);
            g2.drawString(String.valueOf(valueOfN[i3]), x - 5, y + 20);
        }

        //draw y axis scale
        int numOfY = 5;
        FontMetrics fm = g2.getFontMetrics();
        for (int i5 = 0; i5 <= numOfY; i5++) {
            int value = i5 * (maxGenerations / numOfY);
            int y = windowHeight - padding - (value * (graphHeight) / maxGenerations);
            g2.drawLine(padding - 5, y, padding, y);
            g2.drawString(String.valueOf(value), (padding - 8) - fm.stringWidth(String.valueOf(value)), y + 5);
        }

    }
}
