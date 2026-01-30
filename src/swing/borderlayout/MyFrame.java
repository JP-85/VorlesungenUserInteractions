package swing.borderlayout;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame() {

        super("BorderLayout");

        setLayout(new BorderLayout(5,5));

        addComponents();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        pack();
        setLocationRelativeTo(null);

        setVisible(true);

    }

    private void addComponents() {
        add(new JButton("NORTH"), BorderLayout.PAGE_START);
        add(new JButton("CENTER"));
        add(new JButton("SOUTH"), BorderLayout.PAGE_END);
        add(new JButton("WEST"), BorderLayout.WEST);
        add(new JButton("EAST"), BorderLayout.EAST);
    }

}
