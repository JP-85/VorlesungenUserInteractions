package swing.swing_coordinates.prof;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame () {
        super("Diagramm");

        setSize(350, 200);

        add(new Diagram());

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
    }
}
