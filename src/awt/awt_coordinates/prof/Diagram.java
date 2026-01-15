package awt.awt_coordinates.prof;

import java.awt.*;

public class Diagram extends Component {
    private final int PADDING = 15;

    @Override
    public void paint(Graphics g) {

        g.drawLine(PADDING, PADDING,
                PADDING, getHeight() - PADDING);
        g.drawLine(PADDING - 5, getHeight() - PADDING - 5,
                getWidth() - PADDING, getHeight() - PADDING - 5);


        g.fillPolygon(new int[]{PADDING, PADDING - 5, PADDING + 5},
                new int[]{PADDING - 2, PADDING + 4, PADDING + 4},
                3);
        g.fillPolygon(new int[]{getWidth()- PADDING + 2, getWidth() - PADDING - 5, getWidth() - PADDING - 5},
                new int[]{getHeight() - PADDING - 5, getHeight() - PADDING - 10, getHeight() - PADDING},
                3);
    }
}
