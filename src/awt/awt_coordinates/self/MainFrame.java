package awt.awt_coordinates.self;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Graphics;

public class MainFrame extends Frame {

    private static final String WINDOW_TITLE = "Coordinates";
    int size;
    int granularity;

    final int OFFSET = 20;
    final double UPPER_MARGIN = 0.80;
    final double LOWER_MARGIN = 0.20;
    final double ARROW_LENGTH = 0.05;

    public MainFrame(int size, int granularity) {
        super(WINDOW_TITLE);

        this.size = size;
        this.granularity = granularity;

        setSize(this.size, this.size);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });

         setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        drawGrid(g);
        drawAxes(g);
    }

    private void drawAxes(Graphics g) {

        int start = (int) Math.floor(this.size * LOWER_MARGIN);
        int end = (int) Math.floor(this.size * UPPER_MARGIN);

        int arrowLengt = (int) Math.floor(this.size * ARROW_LENGTH);
        int arrowOffset = (int) Math.floor(arrowLengt * 0.5);

        g.setColor(Color.BLACK);

        // X-Achse
        g.drawLine(start, end, end, end);
        // Y-Achse
        g.drawLine(start, start + OFFSET, start, end);

        // X-Pfeil
        g.drawLine(end - arrowLengt, end + arrowOffset, end , end);
        g.drawLine(end - arrowLengt, end - arrowOffset, end, end);
        // Y-Pfeil
        g.drawLine(start - arrowOffset, start + OFFSET + arrowLengt, start, start + OFFSET);
        g.drawLine(start + arrowOffset, start + OFFSET + arrowLengt, start, start + OFFSET);

    }

    private void drawGrid(Graphics g) {
        int start = (int) Math.floor(this.size * LOWER_MARGIN);
        int end = (int) Math.floor(this.size * UPPER_MARGIN);

        g.setColor(new Color(200, 200, 200));

        int gridStep = (int) Math.floor((double) (end - (start + OFFSET)) / granularity);

        // horizontal
        for (int i = start + OFFSET; i <= end; i += gridStep) {
            System.out.println(i);
            g.drawLine(start, i, end, i);
        }

        // vertikal
        for (int i = start + OFFSET; i <= end; i += gridStep) {
            System.out.println(i);
            g.drawLine(i, start + OFFSET, i, end);
        }

    }
}
