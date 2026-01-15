package awt.awt_coordinates.kevin;

import java.awt.*;
import java.awt.event.*;

public class CoordinateSystemAWT {

    public static void main(String[] args) {

        Frame frameCoordinateSystem = new Frame("Coordinate System");
        frameCoordinateSystem.setSize(600, 500);

        Canvas canvasAxes = new Canvas() {
            @Override
            public void paint(Graphics g) {
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

        frameCoordinateSystem.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frameCoordinateSystem.setVisible(true);
    }
}