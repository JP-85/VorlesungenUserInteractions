package awt.awt_86_87.prof;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GreeterFrame extends Frame {

    private static final String WINDOW_TITLE = "Greeter";

    public GreeterFrame() {
        super(WINDOW_TITLE);

        addComponents();

        setSize(250, 80);
        setLocationRelativeTo(null);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);

    }

    private void addComponents() {
        Button button = new Button("Greet");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Hello World.");
            }
        });

        add(button);
    }

}