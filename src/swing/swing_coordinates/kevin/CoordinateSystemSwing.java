package swing.swing_coordinates.kevin;

import javax.swing.*;
import java.awt.*;

public class CoordinateSystemSwing {

    public static void main(String[] args) {

        JFrame frameCoordinateSystem = new JFrame("Coordinate System");
        frameCoordinateSystem.setSize(600, 500);

        JPanel canvasAxes = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int margin = 10;    // Seitenabstand
                int arrowSize = 5;  // Größe der Pfeilspitzen

                int width = getWidth();
                int height = getHeight();

                int xAxisY = height - margin;
                int yAxisX = margin;

                // drawLine(int x1, int y1, int x2, int y2)
                // Draws a line, using the current color, between the points (x1, y1) and (x2, y2) in this graphics context's coordinate system.

                // x-axis
                g.drawLine(margin, xAxisY, width - margin, xAxisY);

                // y-axis
                g.drawLine(yAxisX, margin, yAxisX, height - margin);

                // x-axis arrowhead
                g.drawLine(
                        width - margin, xAxisY,
                        width - margin - arrowSize, xAxisY - arrowSize
                );
                g.drawLine(
                        width - margin, xAxisY,
                        width - margin - arrowSize, xAxisY + arrowSize
                );

                // y-axis arrowhead
                g.drawLine(
                        yAxisX, margin,
                        yAxisX - arrowSize, margin + arrowSize
                );
                g.drawLine(
                        yAxisX, margin,
                        yAxisX + arrowSize, margin + arrowSize
                );
            }
        };

        frameCoordinateSystem.add(canvasAxes);

        frameCoordinateSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frameCoordinateSystem.setVisible(true);
    }
}
