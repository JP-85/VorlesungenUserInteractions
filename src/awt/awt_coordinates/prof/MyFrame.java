package awt.awt_coordinates.prof;

import java.awt.*;

public class MyFrame extends Frame {

    public MyFrame () {
        super("Diagramm");

        setSize(350, 200);

        add(new Diagram());

        setLocationRelativeTo(null);
        addWindowListener(new WindowClosingAdapter(this));

        setVisible(true);
    }
}
