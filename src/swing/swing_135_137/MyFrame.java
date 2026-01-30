package swing.swing_135_137;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {

    public MyFrame() {
        super(" GridBagLayout Demo ");
        setLayout(new GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();

        JButton button1 = new JButton("Ok");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        add(button1, c);
        JButton button2 = new JButton(" Open ");
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        add(button2, c);

        JButton button3 = new JButton(" Close ");
        c.gridx = 1;
        c.gridy = 1;
        add(button3, c);

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
    }
}
