package src;

import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.FontMetrics;

public class graph extends JPanel{

    int[] averageGenerations;
    float[] averageTime;
    int[] valueOfN;


    public void displayGraph() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(400, 400);
        window.setContentPane(this);
        //window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public graph(int[] averageGenerations, float[] averageTime, int[] valueOfN) {
        this.averageGenerations = averageGenerations;
        this.averageTime = averageTime;
        this.valueOfN = valueOfN;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();
        int padding = 50;

        int maxLength = valueOfN[valueOfN.length - 1];
        int minLength = valueOfN[0];

        int maxGenerations = 0;
        for (int h: averageGenerations) {
            if (h > maxGenerations) {
                maxGenerations = h;
            }
        }

        float maxTime = 0;
        for (float h2: averageTime) {
            if (h2 > maxTime) {
                maxTime = h2;
            }
        }

        //draw axis
        g2.setColor(Color.DARK_GRAY);
        g2.drawLine(padding, height - padding, width - padding, height - padding);
        g2.drawLine(padding, padding, padding, height - padding);

        //draw line for generations
        g2.setColor(Color.BLUE);
        for (int i = 0; i < valueOfN.length - 1; i++) {
            int x1 = padding + ((valueOfN[i] - minLength) * (width - 2 * padding) / (maxLength - minLength));
            int y1 = height - padding - (averageGenerations[i] * (height - 2 * padding) / maxGenerations);
            int x2 = padding + ((valueOfN[i+1] - minLength) * (width - 2 * padding) / (maxLength - minLength));
            int y2 = height - padding - (averageGenerations[i+1] * (height - 2 * padding) / maxGenerations);
            g2.drawLine(x1, y1, x2, y2);
        }

        //draw line for time
        g2.setColor(Color.RED);
        for (int i2 = 0; i2 < valueOfN.length - 1; i2++) {
            int x1 = padding + ((valueOfN[i2] - minLength) * (width - 2 * padding) / (maxLength - minLength));
            int y1 = (int) (height - padding - (averageTime[i2] * (height - 2 * padding) / maxTime));
            int x2 = padding + ((valueOfN[i2+1] - minLength) * (width - 2 * padding) / (maxLength - minLength));
            int y2 = (int) (height - padding - (averageTime[i2+1] * (height - 2 * padding) / maxTime));
            g2.drawLine(x1, y1, x2, y2);
        }

        //draw axis labels
        g2.setColor(Color.BLACK);
        g2.drawString("Length n", (width / 2) - 30, height - 15);
        g2.setColor(Color.BLUE);
        g2.drawString("Generations", 10, padding - 10);
        g2.setColor(Color.RED);
        g2.drawString("Time (ms)", 10, padding - 25);

        g2.setColor(Color.BLACK);

        //draw x axis scale
        for (int i3  = 0; i3 < valueOfN.length; i3++) {
            int x = padding + (valueOfN[i3] - minLength) * (width - 2 * padding) / (maxLength - minLength);
            int y = height - padding;
            g2.drawLine(x, y, x, y + 5);
            g2.drawString(String.valueOf(valueOfN[i3]), x - 5, y + 20);
        }

        //draw y axis scale
        int numOfY = 5;
        FontMetrics fm = g2.getFontMetrics();
        for (int i4 = 0; i4 <= numOfY; i4++) {
            int value = i4 * (maxGenerations / numOfY);
            int y = height - padding - (value * (height - 2 * padding) / maxGenerations);
            g2.drawLine(padding - 5, y, padding, y);
            g2.drawString(String.valueOf(value), (padding - 8) - fm.stringWidth(String.valueOf(value)), y + 5);
        }

    }
}
