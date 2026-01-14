package swing.swing_coordinates.self;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static final String WINDOW_TITLE = "Coordinates";

    public MainFrame(int size, int granularity) {
        super(WINDOW_TITLE);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Panel, das zeichnet
        setContentPane(new CoordinatePanel(size, granularity));

        pack();                  // nimmt PreferredSize vom Panel
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Inneres Panel für die Zeichnung
    private static class CoordinatePanel extends JPanel {
        private final int size;
        private final int granularity;

        private static final int OFFSET = 20;
        private static final double UPPER_MARGIN = 0.80;
        private static final double LOWER_MARGIN = 0.20;
        private static final double ARROW_LENGTH = 0.05;

        CoordinatePanel(int size, int granularity) {
            this.size = size;
            this.granularity = granularity;
            setBackground(Color.WHITE);
            setPreferredSize(new Dimension(size, size));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            drawGrid(g);
            drawAxes(g);
        }

        private void drawAxes(Graphics g) {
            int start = (int) Math.floor(size * LOWER_MARGIN);
            int end = (int) Math.floor(size * UPPER_MARGIN);

            int arrowLength = (int) Math.floor(size * ARROW_LENGTH);
            int arrowOffset = (int) Math.floor(arrowLength * 0.5);

            g.setColor(Color.BLACK);

            // X-Achse
            g.drawLine(start, end, end, end);
            // Y-Achse
            g.drawLine(start, start + OFFSET, start, end);

            // X-Pfeil
            g.drawLine(end - arrowLength, end + arrowOffset, end, end);
            g.drawLine(end - arrowLength, end - arrowOffset, end, end);

            // Y-Pfeil
            g.drawLine(start - arrowOffset, start + OFFSET + arrowLength, start, start + OFFSET);
            g.drawLine(start + arrowOffset, start + OFFSET + arrowLength, start, start + OFFSET);
        }

        private void drawGrid(Graphics g) {
            int start = (int) Math.floor(size * LOWER_MARGIN);
            int end = (int) Math.floor(size * UPPER_MARGIN);

            g.setColor(new Color(200, 200, 200));

            int usable = end - (start + OFFSET);
            int gridStep = (int) Math.floor((double) usable / granularity);

            if (gridStep <= 0) return; // Schutz bei zu kleiner Fläche / großer Granularity

            // horizontal
            for (int y = start + OFFSET; y <= end; y += gridStep) {
                g.drawLine(start, y, end, y);
            }

            // vertikal
            for (int x = start + OFFSET; x <= end; x += gridStep) {
                g.drawLine(x, start + OFFSET, x, end);
            }
        }
    }
}
