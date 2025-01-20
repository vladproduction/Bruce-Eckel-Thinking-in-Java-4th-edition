package com.vladproduction.basic_threading.threads_execution_viewer.thread_bar_top_scaling;

import javax.swing.*;
import java.awt.*;

public class ChartPanel extends JPanel {
    private long[] executionTimes;
    private long totalWorkingTime;

    public ChartPanel(long[] executionTimes) {
        this.executionTimes = executionTimes;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawChart(g);
    }
    
    public void updateChart() {
        // This method will adjust the heights of the columns dynamically based on their values
        // Without cutting them off, we'll use the current maximum height for scaling.
        repaint();
    }

    private void drawChart(Graphics g) {
        int width = getWidth() / ThreadConstants.THREAD_COUNT; // Width of each column
        int maxHeight = getHeight() - 50; // Height available for drawing bars (leaving space for labels)
        
        // Calculate total working time
        totalWorkingTime = calculateTotalWorkingTime();
        
        // Draw the total working time at the top
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Total Working Time: " + totalWorkingTime + " ms", getWidth() / 4, 30);

        // Determine the maximum execution time for coloring and scaling
        long maxTime = getMaxExecutionTime();
        
        for (int i = 0; i < ThreadConstants.THREAD_COUNT; i++) {
            // Set color based on execution time
            Color barColor = (executionTimes[i] == maxTime) ? Color.RED : Color.GRAY;
            g.setColor(barColor);

            // Ensure we maintain the ratio across the column heights
            int height = (int) ((executionTimes[i] / (double) maxTime) * (maxHeight - 30)); // Scale height relative to maxTime
            height = Math.min(height, maxHeight); // Cap to panel height

            int x = i * width;
            int y = maxHeight - height;  // Draw from the bottom

            // Draw the bar
            g.fillRect(x, y, width - 2, height); // Create the bar
            g.setColor(Color.BLACK);
            g.drawRect(x, y, width - 2, height); // Draw border

            // Draw execution time at the bottom
            g.drawString(executionTimes[i] + " ms", x + (width / 4), maxHeight + 15); // Draw the execution time
        }
    }

    private long getMaxExecutionTime() {
        long max = 0;
        for (long time : executionTimes) {
            if (time > max) {
                max = time;
            }
        }
        return max;
    }

    private long calculateTotalWorkingTime() {
        long total = 0;
        for (long time : executionTimes) {
            total += time;
        }
        return total;
    }
}
