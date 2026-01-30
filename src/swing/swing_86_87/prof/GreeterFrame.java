package swing.swing_86_87.prof;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GreeterFrame extends JFrame {

    private static final String WINDOW_TITLE = "Greeter";

    public GreeterFrame() {
        super(WINDOW_TITLE);

        addComponents();

        setSize(250, 80);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);

    }

    private void addComponents() {
        JButton button = new JButton("Greet");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello World.");
            }
        });

        add(button);
    }

}
