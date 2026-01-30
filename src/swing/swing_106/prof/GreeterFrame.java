package swing.swing_106.prof;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GreeterFrame extends JFrame {
    private JButton b;

    public GreeterFrame() {
        super("Greeter");

        addComponents();
        retgisterEventHandlers();

        pack();
//        setSize(250, 100);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
    }

    private void addComponents() {
        b = new JButton("Greet me");
        add(b);
    }

    private void retgisterEventHandlers() {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("click");
            }
        });
    }
}
