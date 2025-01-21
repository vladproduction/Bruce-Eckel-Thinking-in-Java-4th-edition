package com.vladproduction.concurrency.threads_execution_viewer_gui.thread_bar_timing_colors;

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

    private void drawChart(Graphics g) {
        int width = getWidth() / ThreadsConstants.THREAD_COUNT; // Width of each column
        int maxHeight = getHeight() - 50; // Height available for drawing bars (leaving space for labels)
        Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.ORANGE, Color.MAGENTA}; // Different colors for each column

        // Calculate total working time
        totalWorkingTime = calculateTotalWorkingTime();

        // Draw the total working time at the top
        g.setFont(new Font("Arial", Font.BOLD, 16));
        g.drawString("Total Working Time: " + totalWorkingTime + " ms", getWidth() / 4, 30);

        for (int i = 0; i < ThreadsConstants.THREAD_COUNT; i++) {
            g.setColor(colors[i % colors.length]);
            // Normalize execution time for display
            int height = (int) ((executionTimes[i] / 1000.0) * maxHeight / 10); // Adjust scale factor as needed
            height = Math.min(height, maxHeight); // Cap to panel height

            int x = i * width;
            int y = maxHeight - height;

            // Draw the bar
            g.fillRect(x, y, width - 2, height); // Create the bar
            g.setColor(Color.BLACK);
            g.drawRect(x, y, width - 2, height); // Draw border

            // Draw execution time at the bottom
            g.drawString(executionTimes[i] + " ms", x + (width / 4), maxHeight + 15); // Adjust x and y positions for label
        }
    }

    private long calculateTotalWorkingTime() {
        long total = 0;
        for (long time : executionTimes) {
            total += time;
        }
        return total;
    }
}
