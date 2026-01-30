package awt.awt_135_137;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends Frame {

    public MyFrame() {
        super(" GridBagLayout Demo ");
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        Button button1 = new Button("Ok");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(button1, c);
        Button button2 = new Button(" Open ");
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(button2, c);

        Button button3 = new Button(" Close ");
        c.gridx = 1;
        c.gridy = 1;
        add(button3, c);

        pack();
        setLocationRelativeTo(null);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }

        });

        setVisible(true);
    }
}