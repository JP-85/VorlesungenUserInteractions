package awt.borderlayout;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends Frame {

    public MyFrame() {

        super("BorderLayout");

        setLayout(new BorderLayout(5,5));

        addComponents();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        pack();
        setLocationRelativeTo(null);

        setVisible(true);

    }

    private void addComponents() {
        add(new Button("NORTH"), BorderLayout.PAGE_START);
        add(new Button("CENTER"));
        add(new Button("SOUTH"), BorderLayout.PAGE_END);
        add(new Button("WEST"), BorderLayout.WEST);
        add(new Button("EAST"), BorderLayout.EAST);
    }

}
